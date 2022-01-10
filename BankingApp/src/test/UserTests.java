package test;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import program.Account;
import program.User;

public class UserTests {
    static User user;
    final static String username = "testUser";
    final static String password = "password";

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
    }

    @Test
    public void testAccountMethods(){
        user.createAccount("checking");
        user.createAccount("savings");

        Account checking = user.getAccount("checking");
        Account savings = user.getAccount("savings");

        assertNotNull(checking);
        assertNotNull(savings);
    }

    @Test
    public void testPassword(){
        boolean result = user.checkPassword(password);
        assertTrue(result);

        result = user.checkPassword(" " + password);
        assertFalse(result);

        System.out.println("Password test passed");
    }

}
