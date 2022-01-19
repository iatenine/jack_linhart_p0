package repositories;

import models.Account;
import models.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionRepo implements ITransactionRepo{

    Connection conn;

    public TransactionRepo(Connection conn){
        this.conn = conn;
    }

    @Override
    public Transaction addTransaction(Account account, double amount) throws SQLException {
        // default, account_id, amount, date
        String sql = "INSERT INTO transactions VALUES(default, ?, ?, ?) RETURNING *";
        String balanceUpdate = "UPDATE ACCOUNTS set BALANCE = (SELECT sum(amount)from " +
                "TRANSACTIONS WHERE account_id=?) " +
                "where A_ID=? RETURNING *";
        PreparedStatement ps = conn.prepareStatement(sql);
        PreparedStatement updateStatement = conn.prepareStatement(balanceUpdate);

        // Populate basic statement
        ps.setInt(1, account.getId());
        ps.setDouble(2, amount);
        ps.setLong(3, System.currentTimeMillis());

        // Update balance for user
        updateStatement.setInt(1, account.getId());
        updateStatement.setInt(2, account.getId());

        // Update transactions *first*
        Transaction t = buildTransaction(ps);
        updateStatement.executeQuery();

        return t;
    }

    @Override
    public Transaction getTransaction(int t_id) throws SQLException{
        String sql = "SELECT * FROM transactions WHERE t_id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, t_id);
        return buildTransaction(ps);
    }

    @Override
    public Transaction[] getTransactionHistory(int account_id) throws SQLException{
        int capacity = 50;
        int index = 0;
        Transaction[] history = new Transaction[capacity];
        String sql = "SELECT * FROM transactions WHERE account_id=? LIMIT ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, account_id);
        ps.setInt(2, capacity);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            history[index] = new Transaction(rs.getInt(1), rs.getDouble(3), rs.getLong(4));
            index++;
        }
        Transaction[] ret = new Transaction[index];
        System.arraycopy(history, 0, ret, 0, index);

        return ret;
    }


    private Transaction buildTransaction(PreparedStatement ps) throws SQLException {
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            return new Transaction(rs.getInt(1), rs.getDouble(2), rs.getLong(3));
        return null;
    }
}
