package models;

import java.text.SimpleDateFormat;

/**
 * @author Jack Linhart
 * Represents a single Transaction. Holds immutable data only (are structs a thing in Java?)
 */

public class Transaction {
    final private double amount;
    final private long date;
    final private int id;


    /**
     * Instantiates an instance of type Transaction
     * @param id
     * a_id provided by the database after a query
     * @param amount
     * The amount of the transaction. Deposits are positive, withdraws are negative
     * @param time
     * The time in milliseconds when the Transaction was created
     */

    public Transaction(int id, double amount, long time) {
        this.amount = amount;
        this.date = time;
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }
    public long getDate() {
        return date;
    }
    public int getId() { return id; }

    @Override
    public String toString() {
        SimpleDateFormat sf = new SimpleDateFormat();
        return "id: " + id +" | date=" + sf.format(date) +
                " | amount:" + amount;
    }
}
