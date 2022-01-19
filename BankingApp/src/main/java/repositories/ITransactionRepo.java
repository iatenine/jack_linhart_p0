package repositories;

import models.Account;
import models.Transaction;

import java.sql.SQLException;

public interface ITransactionRepo {
    Transaction addTransaction(Account account, double amount) throws SQLException;
    Transaction getTransaction(int t_id) throws SQLException;
    Transaction[] getAllAccountTransactions(int account_id) throws SQLException;

    // No DELETE/UPDATE allowed
}
