package test;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import models.Account;
import models.User;

public class UserTests {
    static User user;
    final static String username = "testUser";
    final static int id = 20;

    // Should be able to create user
    @BeforeAll
    public static void initStuff(){
        user = new User(id, username);
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
    public void testGetUsername(){
        assertEquals(username, user.getUsername());
    }

}
