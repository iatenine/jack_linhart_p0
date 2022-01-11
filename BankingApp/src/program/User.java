package program;

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
    private final String password;
    private final List<Account> accounts = new List<>();

    /**
     * Constructor to create a new user
     * @param username
     * new User's username
     * @param password
     * new User's password
     */
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    /**
     * Create a new Account owned by this User
     * @param name
     * Name of new account
     */
    public void createAccount(String name){
        Account newAccount = new Account(name);
        accounts.push(newAccount);
    }

    /**
     * Check if a password is correct
     * @param password
     * Password entered
     * @return
     * Boolean representing if User's password matches password input
     */
    public boolean checkPassword(String password){
        return Objects.deepEquals(this.password, password);
    }

    /**
     * Getter for username
     * @return
     * String representing User's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Fetch account with a specified name
     * @param name
     * Name of account to look up
     * @return
     * Account with matching name if found
     */
    public Account getAccount(String name){
        Account ret = null;
        Object[] accountArr = accounts.getItems();
        for(Object test : accountArr){
            Account a = (Account) test;
            if(Objects.equals(a.name, name)) {
                ret = a;
                break;
            }
        }
        return ret;
    }

    /**
     * Get all Accounts
     * @return
     * Full array of accounts owned by this User
     */
    public Account[] getAccounts(){
        Object[] o = accounts.getItems();
        Account[] ret = new Account[o.length];
        for (int i = 0; i < o.length; i++) {
            if(o[i] == null)
                continue;
            ret[i] = (Account) o[i];
        }
        return ret;
    }
}
