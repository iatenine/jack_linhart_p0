package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import models.Account;
import models.List;

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
        list.push(accounts[0]);
        Account test = list.pop();
        assertNotNull(test);
    }

    @Test
    public void testGetAccountsMethod(){
        list.push(accounts[0]);
        list.push(accounts[1]);

        Object[] testArr = list.getItems();
        assertNotNull(testArr);
        assertEquals(testArr.length, 2);

        assertNotNull(testArr[0]);
        assertNotNull(testArr[1]);
    }

    @Test
    public void testRemoveAccount(){
        Account test;
        test = list.removeItem(0);
        assertNull(test);
        list.push(accounts[0]);
        test = list.removeItem(0);

        assertNotNull(test);
    }
}
