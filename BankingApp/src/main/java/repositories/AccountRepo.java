package repositories;

import models.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRepo implements IAccountRepo{

    Connection conn;

    public AccountRepo(Connection conn){
        this.conn = conn;
    }

    @Override
    public Account addAccount(String name, int user_id) throws SQLException {
        String sql = "INSERT INTO accounts VALUES(default, ?, 0) RETURNING *";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        Account newAccount = buildAccount(ps);
        if(newAccount == null) return null;

        addAuthorizedUser(user_id, newAccount.getId());
        return buildAccount(ps);
    }

    public boolean addAuthorizedUser(int user_id, int account_id) throws SQLException {
        String newAssociation = "INSERT INTO useraccounts VALUES(?, ?) RETURNING *";
        PreparedStatement ps = conn.prepareStatement(newAssociation);
        ps.setInt(1, user_id);
        ps.setInt(2, account_id);
        ResultSet rs = ps.executeQuery();
        return rs!=null;
    }

    @Override
    public Account getAccount(int a_id) throws SQLException{
        String sql = "SELECT * FROM accounts WHERE a_id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, a_id);
        return buildAccount(ps);
    }

    public Account[] getAllAccounts(int user_id) throws SQLException{
        Account[] temp = new Account[200];
        int index = 0;
        String sql = "SELECT A.a_id, A.name, A.balance FROM users U\n" +
                "join USERACCOUNTS U2 on U.U_ID=U2.U_ID\n" +
                "JOIN ACCOUNTS A on A.A_ID=U2.A_ID\n" +
                "WHERE U.U_ID=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, user_id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            temp[index] = new Account(rs.getInt(1), rs.getString(2));
            temp[index++].setBalance(rs.getDouble(3));
        }

        Account[] accounts = new Account[index];
        System.arraycopy(temp, 0, accounts, 0, index);

        return accounts;
    }

    @Override
    public Account updateAccount(Account update) throws SQLException {
        String sql = "UPDATE accounts SET name=? WHERE a_id=? RETURNING *";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, update.getName());
        ps.setInt(2, update.getId());
        return buildAccount(ps);
    }

    @Override
    public Account deleteAccount(int a_id) throws SQLException {
        String sql = "DELETE FROM accounts WHERE a_id=? RETURNING *";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, a_id);
        return buildAccount(ps);
    }

    private Account buildAccount(PreparedStatement ps) throws SQLException {
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            Account ret = new Account(rs.getInt(1), rs.getString(2));
            ret.setBalance(rs.getDouble(3));
            return ret;
        }
        return null;
    }

}
