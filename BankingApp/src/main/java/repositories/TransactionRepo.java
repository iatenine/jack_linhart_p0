package repositories;

import models.Account;
import models.Transaction;
import util.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionRepo implements ITransactionRepo{

    Connection conn;

    public static void main(String[] args) {
        Connection thisConn = JDBCConnection.getConnection();
        TransactionRepo tr = new TransactionRepo(thisConn);

        try {
            tr.addTransaction(new Account(1,"checking"), 20);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TransactionRepo(Connection conn){
        this.conn = conn;
    }

    @Override
    public Transaction addTransaction(Account account, double amount) throws SQLException {
        String sql = "INSERT INTO transactions VALUES(default, ?, ?, ?) RETURNING *";
        PreparedStatement ps = conn.prepareStatement(sql);
        // Find a way to populate this properly
        return buildTransaction(ps);
    }

    @Override
    public Transaction getTransaction(int t_id) throws SQLException{
        return null;
    }

    @Override
    public Transaction[] getAllAccountTransactions(int account_id) throws SQLException{
        return new Transaction[0];
    }


    private Transaction buildTransaction(PreparedStatement ps) throws SQLException {
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            return new Transaction(rs.getInt(1), rs.getDouble(2), rs.getLong(3));
        return null;
    }
}
