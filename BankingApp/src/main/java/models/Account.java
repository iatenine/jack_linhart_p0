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

    /**
     * Increase the Account's balance by deposited amount
     * @param deposit
     * Amount to deposit as represented by a double
     */
    public void deposit(double deposit){
        this.amount = getPreciseDoubleAddition(getBalance(), deposit);
    }

    /**
     * Subtracts the request from account and returns it, if available
     * @param request
     * Amount being requested by user
     * @return
     * returns either amount equal to request or 0 if the request exceeds the balance
     */
    public double withdraw(double request){
        double newAmount = getPreciseDoubleSubtraction(getBalance(), request);
        if(newAmount < 0)
            return 0;
        this.amount = newAmount;
        return request;
    }

    /**
     * Getter for name
     * @return
     * String of Account's name
     */
    public String getName() {return name;}

    /**
     * Getter for amount
     * @return
     * double representing Account's amount
     */
    public double getBalance(){
        return this.amount;
    }

    public int getId() {
        return id;
    }

    /**
     * Setter for name
     * @param name
     * new name
     */
    public void setName(String name) {this.name = name;}

    private void setId(int id) {
        this.id = id;
    }

    /**
     * Setter for amount
     * @param newValue
     * New balance
     */
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

    /**
     * Uses BigDecimalClass to add 2 doubles with better precision
     * @param num1
     * First double to add
     * @param num2
     * Second double to add
     * @return
     * Summation of 2 doubles
     */
    private double getPreciseDoubleAddition(double num1, double num2){
        BigDecimal d1 = BigDecimal.valueOf(num1);
        BigDecimal d2 = BigDecimal.valueOf(num2);
        BigDecimal dFinal = d1.add(d2);
        return dFinal.doubleValue();
    }

    /**
     * Uses BigDecimalClass to subtract 2 doubles with better precision
     * @param num1
     * Number to subtract from
     * @param num2
     * Number to subtract
     * @return
     * Difference of 2 doubles
     */
    private double getPreciseDoubleSubtraction(double num1, double num2){
        BigDecimal d1 = BigDecimal.valueOf(num1);
        BigDecimal d2 = BigDecimal.valueOf(num2);
        BigDecimal dFinal = d1.subtract(d2);
        return dFinal.doubleValue();
    }
}
