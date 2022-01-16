package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCConnection {
    /*
    * We are going to create a similar idea to what is called a Singleton
    *
    * Singleton -> Design Patttern inn which you only ever want one
    * instance of the object to EVER exist
    *
    *  - Prevent additional Object creations by privatizing our constructor
    * and creating a public method that controls when if at all a new Object is created
    *
    * */

    private static Connection conn = null;

    public static Connection getConnection(){
        // Establish conn if one doesn't exist, otherwise return current
        if(conn != null)
            return conn;

        Properties props = new Properties();
        try {
            props.load(JDBCConnection.class.getClassLoader().getResourceAsStream("connection.properties"));
            conn = DriverManager.getConnection(props.getProperty("endpoint"), props.getProperty("username"), props.getProperty("password"));
        }
        catch (SQLException | IOException e){
            e.printStackTrace();
        }

        return conn;
    }

    public static void main(String[] args) {
        Connection c = getConnection();
    }
}
