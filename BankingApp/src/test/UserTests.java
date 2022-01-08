package test;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import program.User;

public class UserTests {
    static User user;
    final static String username = "testUser";
    final static String password = "password";

    // Should be able to create user
    @BeforeAll
    public static void initStuff(){
        user = new User(username, password);
        Assertions.assertTrue(user != null);
    }

    @Test
    public void testPassword(){
        boolean result = user.checkPassword(password);
        assertTrue(result);

        result = user.checkPassword("kjlfdsahjfkjlhakj");
        assertFalse(result);
    }



}
