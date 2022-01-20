package services;

import models.Account;
import models.Transaction;
import repositories.TransactionRepo;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionService implements Service{
    final private TransactionRepo tr;

    public TransactionService(Connection conn){
        this.tr = new TransactionRepo(conn);
    }

    public Transaction add(Account a, double amount) throws SQLException {
        return tr.addTransaction(a, amount);
    }

    public Transaction get(int transaction_id) throws SQLException{
        return tr.getTransaction(transaction_id);
    }

    public Transaction[] getHistory(int account_id) throws SQLException{
        return tr.getTransactionHistory(account_id);
    }
}
