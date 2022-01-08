package program;

import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.UUID;

public class Account {
    private double amount = 0; // Amount of *cents* in account
    private User owner;
    private UUID id;
    String name;

    Account(User owner, String name){
        this.owner = owner;
        this.name = name;
        this.id = UUID.randomUUID();
    }

    // deposit
    public void deposit(double deposit){
        BigDecimal decimal;
        this.amount += deposit;
    }

    // withdraw
    public void withdraw(double request){
        if(request > getBalance())
            amount -= request;
    }

    // query
    public double getBalance(){
        return amount;
    }

    //override toString
    @Override
    public String toString(){
        DecimalFormat df = new DecimalFormat("$0.00");
//        System.out.println(this.amount);
        return  "Account: " + this.name + ": " + df.format(this.getBalance());
    }

}
