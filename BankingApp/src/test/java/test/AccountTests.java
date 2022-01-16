package test;

import models.Account;
import models.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTests {
    static User user;
    final static String username = "testUser";
    final static int id = 45;

    static Account checking;
    static Account savings;

    // Should be able to create user
    @BeforeAll
    public static void initStuff(){
        user = new User(id, username);
        user.createAccount("checking");
        user.createAccount("savings");

        checking = user.getAccount("checking");
        savings = user.getAccount("savings");
    }

    @Test
    public void testGetBalance(){
        final double x = 5;
        checking.setBalance(x);
        assertEquals(checking.getBalance(), x);
    }

    @Test
    public void testAccountCreation(){
        Account a;
        Account[] arr;
        a = user.getAccount("checking");
        arr = user.getAccounts();

        assertNotNull(a);
        assertEquals(a.getName(), "checking");

        assertEquals(arr.length, 2);
        assertEquals("checking", arr[0].getName());
        assertEquals("savings", arr[1].getName());
    }

    @Test
    public void testDeposits(){
        // We'll get to this one
        double[] checkingDeposits = {0.1, 0.2};
        double[] savingsDeposits = {20.5, 10.25, 6.00, 4.5};
        double checkingTotal = 0.3;
        double savingsTotal = 41.25;

        checking.setBalance(0);
        savings.setBalance(0);

        for(double d : checkingDeposits){
            checking.deposit(d);
        }
        for(double d : savingsDeposits){
            savings.deposit(d);
        }

        assertEquals(savings.getBalance(), savingsTotal);
        assertEquals(checking.getBalance(), checkingTotal);

    }

    @Test
    public void testWithdraws(){
        double[] checkingWithdraws = {20, 1.5, 3.1};
        double[] savingsWithdraws = {0.1, 0.2, 4};
        double initialChecking = 22; // Should not respect 3rd withdraw
        double initialSavings = 5; // All withdraws should process
        double endingChecking = 0.5;
        double endingSavings = 0.7;

        checking.setBalance(initialChecking);
        savings.setBalance(initialSavings);

        for(double w : checkingWithdraws){
            checking.withdraw(w);
        }
        for(double w: savingsWithdraws){
            savings.withdraw(w);
        }

        assertEquals(checking.getBalance(), endingChecking);
        assertEquals(savings.getBalance(), endingSavings);
    }
}