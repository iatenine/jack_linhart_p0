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
    // Login with credentials - testPassword()... kinda...

    // Should be able to create user
    @BeforeAll
    public static void initStuff(){
        user = new User(username, password);
        assertNotNull(user);

    }

    @Test
    public void testAccountMethods(){
        user.createAccount("checking");
        user.createAccount("savings");

        Object checking = user.getAccount("checking");
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
    }

    @Test
    public void testGetUsername(){
        assertEquals(username, user.getUsername());
    }

}
