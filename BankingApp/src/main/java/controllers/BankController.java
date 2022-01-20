package controllers;

import services.AccountService;
import services.TransactionService;
import services.UserService;

import java.sql.Connection;

public class BankController {
     Connection conn;
     UserService userService;
     AccountService accountService;
     TransactionService transactionService;

    public BankController(Connection conn) {
        this.conn = conn;
        this.userService = new UserService(conn);
        this.accountService = new AccountService(conn);
        this.transactionService = new TransactionService(conn);
    }

    public UserService getUserService() {
        return userService;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public TransactionService getTransactionService() {
        return transactionService;
    }
}
