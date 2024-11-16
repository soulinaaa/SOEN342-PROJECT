/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sports.Lessons;

import java.sql.ResultSet;
import java.sql.SQLException;


public class User implements LoginInterface {
    
    enum USER_TYPE {ADMIN, INSTRUCTOR, CLIENT};
    private String userID;
    private String password;
    private String phoneNumber;
    private String userType;
    private String firstName;
    private String lastName;
    private String email;

    public User() {
    }

    public User(User user) {
        this.userID = user.userID;
        this.password = user.password;
        this.phoneNumber = user.phoneNumber;
        this.userType = user.userType;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.email = user.email;
    }

    public User(String userID, String password, String phoneNumber, String userType, String firstName, String lastName, String email) {
        this.userID = userID;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    @Override
    public boolean login(String userId, String password) {
        DatabaseUtility dbUtil = DatabaseUtility.getInstance();
        this.userID = userId;
        this.userType = "";
        String querySQL = "SELECT user_id, email, phone, first_name, last_name, password, user_type_code\n" +
                "FROM sports.USERS\n" +
                "Where user_id = '" + userId + "'\n" +
                " AND password = '" + password + "' \n" ;

        ResultSet rs = dbUtil.executeQuery(querySQL);
        try {
            while (rs.next()) {
                this.userType = rs.getString("user_type_code");
                this.phoneNumber = rs.getString("phone");
                this.firstName = rs.getString("first_name");
                this.lastName = rs.getString("last_name");
                this.email = rs.getString("email");


            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        // Close the connection

        return  (!userType.isBlank());
    }

    public boolean createUser(){

        DatabaseUtility dbUtil = DatabaseUtility.getInstance();
        // Insert data using prepared statement
        String insertSQL = "INSERT INTO `sports`.`USERS` (`user_id`, `email`, `phone`, `first_name`, `last_name`, `password`, `user_type_code`) VALUES (?,?,?,?,?,?,?);\n";
        Object[] params = {getUserID(),getEmail(), getPhoneNumber(),getFirstName(),getLastName(), getPassword(),getUserType()};
        dbUtil.executePreparedUpdate(insertSQL, params);
        return true;

    }


    public User(String userID, String password, String phoneNumber, String userType) {
        this.userID = userID;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
    }

    public User(String userID) {
        this.userID = userID;
    }


}
