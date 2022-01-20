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
}