package test.repo;

import models.User;
import org.junit.jupiter.api.Test;
import repositories.UserRepo;
import util.JDBCConnection;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepoTest {
    Connection conn = JDBCConnection.getConnection();
    UserRepo ur = new UserRepo(conn);
    User testUser = null;
    User differentUser = null;

    // Need to do all together as ids returned will be necessary
    @Test
    public void testUserCRUD(){
        try {
            int firstId;
            int secondId;

            // Create
            testUser = ur.addUser("user1", "password");
            differentUser = ur.addUser("user2", "secret");

            firstId = testUser.getId();
            secondId = differentUser.getId();

            assertNotNull(testUser);
            assertNotEquals(testUser.getId(), differentUser.getId());
            assertEquals("user1", testUser.getUsername());
            assertEquals("user2", differentUser.getUsername());

            // Read
            assertEquals(firstId, ur.getUser(firstId).getId());
            assertNotEquals(firstId, ur.getUser(secondId).getId());

            // Update
            testUser = ur.updateUser(testUser, "newpass", "password");
            assertNotNull(testUser);

            // Delete (newpass only works if updateUser was successful)
            testUser = ur.deleteUser(firstId, "newpass");
            differentUser = ur.deleteUser(secondId, "wrong");

            // Test guard clause
            assertNull(differentUser);

            differentUser = ur.deleteUser(secondId, "secret");

            assertNotNull(testUser);
            assertNotNull(differentUser);

            // Ensure the elements are indeed removed from the database
            assertNull(ur.getUser(firstId));
            assertNull(ur.getUser(secondId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
