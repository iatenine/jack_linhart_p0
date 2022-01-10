package program;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Account {
    private double amount = 0; // Amount of *cents* in account
    String name;

    public Account(String name){
        this.name = name;
    }

    // deposit
    public void deposit(double deposit){
        this.amount = getPreciseDoubleAddition(getBalance(), deposit);
    }

    // withdraw
    public double withdraw(double request){
        double newAmount = getPreciseDoubleSubtraction(getBalance(), request);
        if(newAmount < 0)
            return 0;
        this.amount = newAmount;
        return request;
    }

    // Getter/Setters
    public String getName() {return name;}
    public double getBalance(){
        return this.amount;
    }

    public void setName(String name) {this.name = name;}
    public void setBalance(double newValue){ amount = newValue; }

    //override toString
    @Override
    public String toString(){
        DecimalFormat df = new DecimalFormat("$0.00");
        return this.name + ": " + df.format(this.getBalance());
    }

    private double getPreciseDoubleAddition(double num1, double num2){
        BigDecimal d1 = BigDecimal.valueOf(num1);
        BigDecimal d2 = BigDecimal.valueOf(num2);
        BigDecimal dFinal = d1.add(d2);
        return dFinal.doubleValue();
    }

    private double getPreciseDoubleSubtraction(double num1, double num2){
        BigDecimal d1 = BigDecimal.valueOf(num1);
        BigDecimal d2 = BigDecimal.valueOf(num2);
        BigDecimal dFinal = d1.subtract(d2);
        return dFinal.doubleValue();
    }
}
