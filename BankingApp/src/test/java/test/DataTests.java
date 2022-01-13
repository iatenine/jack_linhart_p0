package test;

import data.MockDb;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import program.User;

public class DataTests {
    static MockDb db = new MockDb();

    @BeforeAll
    public static void init(){
        db.addUser(new User("bob", "password"));
        db.addUser(new User("Frank", "password"));
    }

    @Test
    public void testLogin(){
        User u = db.login("Frank", "password");
        assertNotNull(u);
        assertEquals(u.getUsername(), "Frank");

        u = db.login("nobody", "");
        assertNull(u);
    }
}
