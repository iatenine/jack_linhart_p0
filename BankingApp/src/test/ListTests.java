package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import program.Account;
import program.List;
import program.User;

public class ListTests {
    static List list;
    User user = new User("name", "pass");
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
            new Account(user, accountNames[0]),
            new Account(user, accountNames[1]),
            new Account(user, accountNames[2]),
            new Account(user, accountNames[3]),
            new Account(user, accountNames[4]),
            new Account(user, accountNames[5]),
            new Account(user, accountNames[6]),
            new Account(user, accountNames[7]),
            new Account(user, accountNames[8])
    };


    @BeforeEach
    public void init(){
        list = new List();
    }

    @Test
    public void testPushAndPop(){
        list.push(accounts[0]);
        Account test = list.pop();
        assertNotNull(test);
    }

    @Test
    public void testGetAccountMethods(){
        list.push(accounts[0]);
        list.push(accounts[1]);

        Account testAccount = list.getAccount(accountNames[1]);
        assertNotNull(testAccount);

        Account[] testArr = list.getAccounts();
        assertNotNull(testArr);
        assertEquals(testArr.length, 2);
    }

    @Test
    public void testRemoveAccount(){
        Account test;
        test = list.removeAccount(accountNames[0]);
        assertNull(test);
        list.push(accounts[0]);
        test = list.removeAccount(accountNames[0]);

        assertNotNull(test);
        assertTrue(test instanceof Account);
    }
}
