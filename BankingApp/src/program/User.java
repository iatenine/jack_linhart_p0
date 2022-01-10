package program;

import java.util.Objects;

public class User {
    private final String username;
    private final String password;
    private final List<Account> accounts = new List<>();

    // User needs a password
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void createAccount(String name){
        Account newAccount = new Account(name);
        accounts.push(newAccount);
    }

    public boolean checkPassword(String password){
        return Objects.deepEquals(this.password, password);
    }

    public String getUsername() {
        return username;
    }

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
