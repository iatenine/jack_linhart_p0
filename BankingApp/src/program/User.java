package program;

import java.util.Objects;

public class User {
    private final String username;
    public final String password;
    private final List<Account> accounts = new List<>();

    // User needs a password
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void createAccount(String name){
        Account newAccount = new Account(this, name);
        accounts.push(newAccount);
    }

    public boolean checkPassword(String password){
        return Objects.deepEquals(this.password, password);
//        return Objects.equals(this.password, password);
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
}
