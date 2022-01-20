package repositories;

import models.User;

import java.sql.SQLException;

public interface IUserRepo {
    // No reason to ever return all Users
    public User addUser(String username, String password) throws SQLException;
    public User getUser(int u_id) throws SQLException;
    public User getUser(String username) throws SQLException;
    public User login(String username, String password) throws SQLException;
    public User deleteUser(int u_id, String password) throws SQLException;
    public User updateUser(User change, String newPassword, String oldPassword) throws SQLException;
}
