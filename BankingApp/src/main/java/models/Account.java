package models;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author Jack Linhart
 * Represents a single account with a name and balance
 */

public class Account {
    private int id;
    private double amount = 0; // Amount of *cents* in account
    String name;

    /**
     * Constructor
     * @param name
     * What the name of the Account will be
     */
    public Account(int id, String name){
        setId(id);
        setName(name);
    }

    public String getName() {return name;}
    public double getBalance(){
        return this.amount;
    }
    public int getId() {
        return id;
    }

    public void setName(String name) {this.name = name;}
    private void setId(int id) {
        this.id = id;
    }
    public void setBalance(double newValue){ amount = newValue; }

    /**
     * Override toString() method
     * @return
     * String representing account with the following format:
     * Account Name: $X.XX
     */
    @Override
    public String toString(){
        DecimalFormat df = new DecimalFormat("$0.00");
        return this.name + ": " + df.format(this.getBalance());
    }
}
