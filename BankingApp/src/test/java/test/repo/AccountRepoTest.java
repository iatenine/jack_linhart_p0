package test.repo;

import models.Account;
import models.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repositories.AccountRepo;
import repositories.UserRepo;
import util.JDBCConnection;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountRepoTest {
    static Connection conn = JDBCConnection.getConnection();
    AccountRepo ar = new AccountRepo(conn);
    static UserRepo ur = new UserRepo(conn);
    static User u = null;
    Account testAccount = null;
    Account differentAccount = null;

    @BeforeAll
    public static void setup(){
        try {
            u = ur.addUser("testUser", "testPassword");
            assertNotNull(u);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Need to do all together as ids returned will be necessary
    @Test
    public void testAccountCRUD(){
        try {
            int firstId;
            int secondId;

            // Create
            testAccount = ar.addAccount("Test Account", u.getId());
            differentAccount = ar.addAccount("Different", u.getId());

            firstId = testAccount.getId();
            secondId = differentAccount.getId();

            assertNotNull(testAccount);
            assertNotEquals(testAccount.getId(), differentAccount.getId());
            assertEquals(0, testAccount.getBalance());
            assertEquals("Test Account", testAccount.getName());

            // Read
            assertEquals(firstId, ar.getAccount(firstId).getId());
            assertNotEquals(firstId, ar.getAccount(secondId).getId());

            // Update
            testAccount = ar.updateAccount(new Account(firstId, "new name"));

            assertNotEquals(testAccount.getName(), "Test Account");
            assertEquals("new name", testAccount.getName());

            // Delete
            testAccount = ar.deleteAccount(firstId);
            differentAccount = ar.deleteAccount(secondId);

            assertNotNull(testAccount);
            assertNotNull(differentAccount);

            // Ensure the elements are indeed removed from the database
            assertNull(ar.getAccount(firstId));
            assertNull(ar.getAccount(secondId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void cleanup() {
        try {
            u = ur.deleteUser(u.getId(), "testPassword");
            assertNotNull(u);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
