package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import models.Account;
import util.List;

public class ListTests {
    static List<Account> list;
    String[] accountNames = {
            "checking",
            "savings",
            "HSA",
            "retirement",
            "mortgage",
            "credit",
            "IRA",
            "Money Market",
            "Stocks"
    };
    Account[] accounts = {
            new Account(accountNames[0]),
            new Account(accountNames[1]),
            new Account(accountNames[2]),
            new Account(accountNames[3]),
            new Account(accountNames[4]),
            new Account(accountNames[5]),
            new Account(accountNames[6]),
            new Account(accountNames[7]),
            new Account(accountNames[8])
    };


    @BeforeEach
    public void init(){
        list = new List<>();
    }

    @Test
    public void testPushAndPop(){
        list.add(accounts[0]);
        Account test = list.pop();
        assertNotNull(test);
    }

    @Test
    public void testGetAccountsMethod(){
        list.add(accounts[0]);
        list.add(accounts[1]);
        assertEquals(list.length, 2);

        Account[] arr = new Account[2];
        for (int i = 0; list.length > 0; i++) {
            arr[i] = list.pop();
        }
        assertNotNull(arr);
        assertEquals(arr.length, 2);
        System.out.println(arr[0]);
        System.out.println(arr[1]);

        assertNotNull(arr[0]);
        assertNotNull(arr[1]);

        assertEquals(list.length, 0);
    }

    @Test
    public void testRemoveAccount(){
        Account test;
        test = list.pop();
        assertNull(test);
        list.add(accounts[0]);
        test = list.pop();

        assertNotNull(test);
    }
}
