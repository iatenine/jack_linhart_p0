package repositories;

import models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepo implements IUserRepo {

    Connection conn;

    public UserRepo(Connection conn){
        this.conn = conn;
    }

    @Override
    public User addUser(String username, String password) throws SQLException {
        String sql = "INSERT INTO users VALUES(default, ?, ?) RETURNING *";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);

        return buildUser(ps);
    }

    @Override
    public User getUser(int u_id) throws SQLException {
        String sql = "SELECT * FROM users WHERE u_id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, u_id);

        return buildUser(ps);
    }

    @Override
    public User login(String username, String password) throws SQLException {
        String sql = "SELECT * FROM users where username ILIKE ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, username);
        User check = buildUser(ps);
        if(!checkLogin(check, password))
            return null;
        return check;
    }

    @Override
    public User deleteUser(int u_id, String password) throws SQLException {
        if(!checkLogin(u_id, password)) return null;
        String sql = "DELETE FROM users WHERE u_id=? RETURNING *";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, u_id);
        return buildUser(ps);
    }

    @Override
    public User updateUser(User change, String newPassword, String oldPassword) throws SQLException {
        if(!checkLogin(change.getId(), oldPassword)) return null;
        String sql = "UPDATE users SET pw=? WHERE u_id=? RETURNING *";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, newPassword);
        ps.setInt(2, change.getId());
        return buildUser(ps);
    }

    public boolean checkLogin(User u, String password) throws SQLException {
        return checkLogin(u.getId(), password);
    }

    private boolean checkLogin(int u_id, String password) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT pw FROM users WHERE u_id=?");
        ps.setInt(1, u_id);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            return rs.getString(1).equals(password);
        return false;
    }

    private static User buildUser(PreparedStatement ps) throws SQLException {
        ResultSet rs = ps.executeQuery();
        User u = null;
        if(rs.next())
            u = new User(rs.getInt(1), rs.getString(2));
        return u;
    }

}
