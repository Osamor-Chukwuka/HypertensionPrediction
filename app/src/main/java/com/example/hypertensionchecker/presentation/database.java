package com.example.hypertensionchecker.presentation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.sql.*;
import java.sql.DriverManager;


public class database {
    public String databases(int id, String name, int age, int blood, int cholesterol, int gender, int predict){
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hypertension", "root", "");
            System.out.println(conn);
            stmt = conn.createStatement();
            System.out.println(stmt + "Connected");

            String sql = "INSERT INTO userss (id, name, age, blood, cholesterol, gender, predict) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            // Set the values for each parameter in the query
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setInt(3, age);
            statement.setInt(4, blood);
            statement.setInt(5, cholesterol);
            statement.setInt(6, gender);
            statement.setInt(7, predict);
            int rowsInserted = statement.executeUpdate();
        }catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        catch (Exception e){
            System.out.println(conn);
        }
        return "";
    }
}
