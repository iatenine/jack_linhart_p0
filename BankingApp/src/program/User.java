package program;

import org.junit.jupiter.params.shadow.com.univocity.parsers.common.DataValidationException;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<Account> accounts = new ArrayList<>(); //Can't use built-in Collections. Need to implement manually

    // User needs a password
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void createAccount(String name){
        Account newAccount = new Account(this, name);
        accounts.add(newAccount);
    }

    public boolean checkPassword(String password){
        return this.password == password;
    }

    public String getUsername() {
        return username;
    }

    public Account getAccount(String name){
        Account ret = new Account(new User("", ""), "");
        for(Account test : accounts){
            if(test.name == name)
                ret = test;
        }
        return ret;
    }
}
