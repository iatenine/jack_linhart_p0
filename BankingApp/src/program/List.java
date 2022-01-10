package program;

// Implement Link List to hold dynamic collection of Accounts

public class List {
    private int step = 5;
    private int tail = 0;
    private Account[] accounts = new Account[step];

    // Add an account
    public void push(Account account){
        if(tail == accounts.length - 1)
            grow();
        accounts[tail] = account;
        tail++;
    }

    public Account pop(){
        if(tail > 0)
            tail--;
        return accounts[tail];
    }

    // Remove account if found
    public Account removeAccount(String name){
        Account a = null;
        for (int i = 0; i < accounts.length; i++) {
            if(accounts[i] != null){
                if(accounts[i].name != name)
                    continue;
                a = accounts[i];
                accounts[i] = null;
                break;
            }
        }
        return a;
    }

    public Account[] getAccounts(){
        int blocks = 0;
        for (Account a: accounts) {
            if(a != null)
                blocks++;
        }
        int nextIndex = 0;
        Account[] a = new Account[blocks];
        for (Account add: accounts) {
            if(add == null)
                continue;
            a[nextIndex++] = add;
        }
        return a;
    }

    public Account getAccount(String name){
        Account ret = null;
        for (Account a: accounts) {
            if(a == null)
                continue;
            if(a.name == name)
            {
                ret = a;
                break;
            }
        }
        return ret;
    }

    private void grow(){
        Account[] temp = accounts;
        accounts = new Account[temp.length + step];
        for (int i = 0; i < temp.length; i++) {
            accounts[i] = temp[i];
        }
    }
}
