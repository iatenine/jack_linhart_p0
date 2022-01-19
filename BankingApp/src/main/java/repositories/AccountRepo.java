package repositories;

import models.Account;
import util.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRepo implements IAccountRepo{

    Connection conn;

    public static void main(String[] args) {
        Connection tConn = JDBCConnection.getConnection();
        AccountRepo ar = new AccountRepo(tConn);

        try {
            Account a;
            a = ar.addAccount("Experimental");
            System.out.println(a);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public AccountRepo(Connection conn){
        this.conn = conn;
    }

    @Override
    public Account addAccount(String name) throws SQLException {
        String sql = "INSERT INTO accounts VALUES(default, ?, 0) RETURNING *";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        return buildAccount(ps);
    }

    @Override
    public Account getAccount(int a_id) throws SQLException{
        String sql = "SELECT * FROM accounts WHERE a_id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, a_id);
        return buildAccount(ps);
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
        if(rs.next())
            return new Account(rs.getInt(1), rs.getString(2));
        return null;
    }

}
