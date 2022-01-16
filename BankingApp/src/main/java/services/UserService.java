package services;

import models.User;
import repositories.UserRepo;

import java.sql.SQLException;

public class UserService {

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
    private UserRepo mr;
    public UserService(UserRepo m){
        this.mr = m;
    }

    public User addUser(String username, String password) throws SQLException {
        return mr.addUser(username, password);
    }
}
