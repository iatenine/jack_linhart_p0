package repositories;

import models.Transaction;

public interface ITransactionRepo {
    Transaction addTransaction(double amount);
    Transaction getTransaction(int t_id);
    Transaction[] getAllAccountTransactions(int account_id);

    // No DELETE/UPDATE allowed
}
