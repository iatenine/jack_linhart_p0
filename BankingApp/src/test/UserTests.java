package test;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import program.Account;
import program.User;

public class UserTests {
    static User user;
    final static String username = "testUser";
    final static String password = "password";

    static Account checking;
    static Account savings;

    //Stories:
    // Register a new user with password - initStuff()
    // Login with credentials - testPassword()... kinda...
    // Create at least one account
    // Deposit funds using doubles
    // Withdraw funds from an account (no overdrafting allowed)
    // View the balance of my accounts with proper formatting

    // Should be able to create user
    @BeforeAll
    public static void initStuff(){
        user = new User(username, password);
        assertTrue(user != null);

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
    public void testPassword(){
        boolean result = user.checkPassword(password);
        assertTrue(result);

        result = user.checkPassword("kjlfdsahjfkjlhakj");
        assertFalse(result);

        System.out.println("Password test passed");
    }


    @Test
    public void testAccountCreation(){
        Account a;
        user.createAccount("checking");
        a = user.getAccount("checking");

        assertNotNull(a);

        System.out.println("Account creation test passed");
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
