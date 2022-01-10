package data;

import program.List;
import program.User;

import java.util.Objects;

public class MockDb {
    final private List<User> users = new List<>();

    public void addUser(User user){
        users.push(user);
    }

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
