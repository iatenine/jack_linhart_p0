package models;

import util.List;
import util.Node;

import java.util.Objects;

/**
 * @author Jack Linhart
 * Represents a single User
 */

public class User {
    /**
     * username: A User's publicly visible username
     * password: Used to authenticate a user attempting to login. Not visible
     * accounts: List of all accounts owned by a user
     */
    private final String username;
    private final int id;
    private final List<Account> accounts = new List<>();

    /**
     * Constructor to create a new user
     * @param username
     * new User's username
     * @param id
     * User's id in the database
     */
    public User(int id, String username){
        this.username = username;
        this.id = id;
    }

    /**
     * Create a new Account owned by this User
     * @param name
     * Name of new account
     */
    public void createAccount(String name){
        // TODO: Get id from repo
        Account newAccount = new Account(2, name);
        accounts.add(newAccount);
    }

    /**
     * Getter for username
     * @return
     * String representing User's username
     */
    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    /**
     * Fetch account with a specified name
     * @param name
     * Name of account to look up
     * @return
     * Account with matching name if found
     */
    public Account getAccount(String name){
        Node<Account> a = accounts.head;
        while(!a.data.getName().equals(name) && a.data != null){
            a = a.next;
        }
        return a.data;
    }

    /**
     * Get all Accounts
     * @return
     * Full array of accounts owned by this User
     */
    public Account[] getAccounts(){
        Account [] accountArr = new Account[accounts.length];
        for (int i = 0; i < accountArr.length; i++) {
            accountArr[i] = accounts.getAtIndex(i);
        }
        return accountArr;
    }

    @Override
    public String toString() {
        return "User{\n" +
                "username='" + username + '\'' +
                ", id=" + id +
                ", accounts=" + accounts +
                "\n}";
    }
}
