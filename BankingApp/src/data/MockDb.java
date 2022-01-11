package data;

import program.List;
import program.User;

/**
 * @author Provides a mock data layer until a real database is connected
 */

public class MockDb {
    /**
     * users: Dynamic List of Users
     */
    final private List<User> users = new List<>();

    /**
     * Adds user to users List
     * @param user
     * user to add
     */
    public void addUser(User user){
        users.push(user);
    }

    /**
     * Attempt to login to a User stored in the users List
     * @param username
     * username to query data layer for
     * @param password
     * password to authenticate user with given username
     * @return
     * User object matching username and password input if found in users List
     */
    public User login(String username, String password){
        Object[] allUsers = users.getItems();
        User ret = null;

        for(Object o : allUsers){
            User u = (User) o;  //Why...?
            if(u == null)
                continue;
            if(username.equals(u.getUsername()) && u.checkPassword(password))
            {
                ret = u;
                break;
            }
        }
        return ret;
    }
}
