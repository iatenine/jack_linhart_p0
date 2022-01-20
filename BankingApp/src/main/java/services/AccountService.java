package services;

import models.Account;
import repositories.AccountRepo;

import java.sql.Connection;
import java.sql.SQLException;

public class AccountService implements Service{
    final private AccountRepo ar;

    public AccountService(Connection conn){
        this.ar = new AccountRepo(conn);
    }

    public Account add(String name, int user_id) throws SQLException {
        return ar.addAccount(name, user_id);
    }

    public Account get(int account_id) throws SQLException {
        return ar.getAccount(account_id);
    }

    public Account[] getAll(int user_id) throws SQLException{
        return ar.getAllAccounts(user_id);
    }

    public boolean addAuthorizedUser(int user_id, int account_id) throws SQLException {
        return ar.addAuthorizedUser(user_id, account_id);
    }

    public Account update(Account update) throws SQLException{
        return ar.updateAccount(update);
    }

    public Account delete(int account_id) throws SQLException{
        return ar.deleteAccount(account_id);
    }
}
