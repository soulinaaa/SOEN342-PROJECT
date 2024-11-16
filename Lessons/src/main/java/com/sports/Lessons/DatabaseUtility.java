package com.sports.Lessons;
import java.sql.*;

public class DatabaseUtility {
    // Singleton instance
    private static DatabaseUtility instance;

    // Database URL, username, and password
    private static final String JDBC_URL = "jdbc:mysql://localhost:3317/sports"; // Adjust as needed
    private static final String JDBC_USER = "admin"; // Replace with your MySQL username
    private static final String JDBC_PASSWORD = "admin123"; // Replace with your MySQL password

    // Connection object
    private Connection connection;

    // Private constructor to prevent instantiation
    private DatabaseUtility() {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.toString());
        }
    }

    // Method to get the singleton instance
    public static DatabaseUtility getInstance() {
        if (instance == null) {
            synchronized (DatabaseUtility.class) {
                if (instance == null) {
                    instance = new DatabaseUtility();
                }
            }
        }
        return instance;
    }

    // Method to execute a SELECT query
    public ResultSet executeQuery(String query) {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return resultSet;
    }

    // Method to execute an INSERT, UPDATE, or DELETE query
    public int executeUpdate(String query) {
        int rowsAffected = 0;
        try {
            Statement statement = connection.createStatement();
            rowsAffected = statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return rowsAffected;
    }

    // Method to execute prepared statements with parameters
    public int executePreparedUpdate(String query, Object[] params) {
        int rowsAffected = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Set parameters dynamically
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return rowsAffected;
    }

    // Method to close the connection
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}
