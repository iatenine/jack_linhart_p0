package util;

import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlHelpers {

    public static PreparedStatement getInsertStatement(Connection conn, String tableName, String[] values){
        String sql = populateInsertString(tableName, values,  true);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < values.length; i++) {
                ps.setString(i+1, values[i]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    public static PreparedStatement getSelectStatement(Connection conn, String tableName, String[] values){
        String sql = populateSelectString(tableName, values);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    public static PreparedStatement getSelectStatement(Connection conn, String tableName, String[] values, String condition){
        String sql = populateSelectString(tableName, values, condition);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    private static String populateSelectString(String tableName, String[] values, String condition){
        return populateSelectString(tableName, values).replaceFirst(";", " WHERE " + condition);
    }

    private static String populateSelectString(String tableName, String[] values){
        // SELECT ...values FROM tableName
        String s = "SELECT ";
        for (String value: values) {
            s += value;
            s += (value == values[values.length-1]) ? "" : ", ";
        }
        s += " FROM " + tableName + ";";
        return s;
    }

    private static String populateInsertString(String tableName, String[] values, boolean provideDefault) {
        String sql = "INSERT INTO " + tableName + " VALUES(";
        if(provideDefault)
            sql += "default";
        for (int i = 0; i < values.length; i++) {
            sql += ", ?";
        }
        sql += ") RETURNING *;";
        return sql;
    }
}
