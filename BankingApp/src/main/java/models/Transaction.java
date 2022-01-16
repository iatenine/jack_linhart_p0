package models;

public class Transaction {
    final private double amount;
    final private long date;

    // All data for a transaction should be immutable after creation
    public Transaction(double amount) {
        this.amount = amount;
        this.date = System.currentTimeMillis();
    }

    public double getAmount() {
        return amount;
    }

    public long getDate() {
        return date;
    }
}
