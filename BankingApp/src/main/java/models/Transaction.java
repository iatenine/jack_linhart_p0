package models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    final private double amount;
    final private long date;
    final private int id;

    // All data for a transaction should be immutable after creation
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
