package repositories;

import models.Account;

public interface IAccountRepo {
    public Account addAccount(String name, double initialBalance);
    public Account getAccount(int a_id);
    public Account updateAccount(int owner_id, Account update);
    public Account deleteAccount(int owner_id, String password, int a_id);
}
