package program;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Account {
    private double amount = 0; // Amount of *cents* in account
    private User owner;
    String name;

    Account(User owner, String name){
        this.owner = owner;
        this.name = name;
    }

    // deposit
    public void deposit(double deposit){
        BigDecimal decimal;
        this.amount += deposit;
    }

    // withdraw
    public double withdraw(double request){
        if(request > getBalance())
            return 0;
        amount -= request;
        return request;
    }

    // Getter/Setters
    public double getBalance(){
        return amount;
    }

    public void setBalance(double newValue){ amount = newValue; }

    //override toString
    @Override
    public String toString(){
        DecimalFormat df = new DecimalFormat("$0.00");
        return this.name + ": " + df.format(this.getBalance());
    }

}
