package program;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<Account> accounts; //Can't use built-in Collections. Need to implement manually

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

    public ArrayList<Account> getAccounts(String password){
        if(checkPassword(password))
            return accounts;
        System.err.println("Incorrect password");
        return new ArrayList<>();
    }
}
