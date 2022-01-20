package services;

import models.User;
import repositories.UserRepo;

import java.sql.Connection;
import java.sql.SQLException;

public class UserService implements Service {

    /*
    * Sometimes your actual goal at some point in your application
    * will be to perform something as basic as just a CRUD operation.
    * However, what we want to avoid is entangling the various
    * layers of our application and we want to maintain order in the control
    * flow of execution
    *
    * Thereby, if we need to perform a simple CRUD operation we shouldn't
    * skip layers, such as the Service Layer, but instead trivially pass through it
    * */

    /*
    Presentation -> Controller
    Controller -> Services
    Services -> Repository
    Repository -> Data
     */

    // Use dependency injection to prevent needless duplication
    final private UserRepo mr;
    public UserService(Connection conn){
        this.mr = new UserRepo(conn);
    }

    public User add(String username, String password) throws SQLException {
        return mr.addUser(username, password);
    }

    // getUser
    public User get(int user_id) throws SQLException {
        return mr.getUser(user_id);
    }

    // updateUser
    public User updateUser(User change, String oldPassword, String newPassword) throws SQLException {
        return mr.updateUser(change, oldPassword, newPassword);
    }

    public User update(User change, String password) throws SQLException {
        return updateUser(change, password, password);
    }

    // deleteUser
    public User delete(int user_id, String password) throws SQLException {
        return mr.deleteUser(user_id, password);
    }

    public User checkLogin(String username, String password) throws SQLException {
        return mr.login(username, password);
    }
}
