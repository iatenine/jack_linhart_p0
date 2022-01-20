package repositories;

import models.Account;

import java.sql.SQLException;

public interface IAccountRepo {
    Account addAccount(String name, int user_id) throws SQLException;
    Account getAccount(int a_id) throws SQLException;
    Account updateAccount(Account update) throws SQLException;
    Account deleteAccount(int a_id) throws SQLException;
}
