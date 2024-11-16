package com.sports.Lessons;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Instructor extends User {

    /*
     private String userID;
    private String password;
    private String phoneNumber;
    private String userType;
    private String firstName;
    private String lastName;
    private String email;
     */
    private String cityAvailabilities;
    private String specializationCode;
    private  int inctructorID;

    public int getInctructorID() {
        return inctructorID;
    }

    public void setInctructorID(int inctructorID) {
        this.inctructorID = inctructorID;
    }

    public String getSpecializationCode() {
        return specializationCode;
    }

    public void setSpecializationCode(String specializationCode) {
        this.specializationCode = specializationCode;
    }

    public String getCityAvailabilities() {
        return cityAvailabilities;
    }

    public void setCityAvailabilities(String cityAvailabilities) {
        this.cityAvailabilities = cityAvailabilities;
    }

    public Instructor(String userID, String password, String phoneNumber, String userType, String firstName, String lastName, String email, String cityAvailabilities, String specializationCode) {
        super(userID, password, phoneNumber, userType, firstName, lastName, email);
        setCityAvailabilities(cityAvailabilities);
        setSpecializationCode(specializationCode);
    }

    public Instructor(User user) {
        super(user);
    }

    public Instructor() {
        super();
    }

    public boolean createInstructor(){

        DatabaseUtility dbUtil = DatabaseUtility.getInstance();
        // Insert data using prepared statement
        String insertSQL = "INSERT INTO `sports`.`INSTRUCTOR` (`user_id`, `cities_availability`, `specialization_code`) VALUES (?,?,?);\n";
        Object[] params = {getUserID(),getCityAvailabilities(),getSpecializationCode()};
        dbUtil.executePreparedUpdate(insertSQL, params);
        return true;

    }

    public boolean registerLesson(int lessonID, int instructorID){
        DatabaseUtility dbUtil = DatabaseUtility.getInstance();
        // Insert data using prepared statement
        String insertSQL = "INSERT INTO `sports`.`REGISTER_OFFER` (`lesson_id`, `instuctor_id`) VALUES (?,?);\n";
        Object[] params = {lessonID, instructorID};
        dbUtil.executePreparedUpdate(insertSQL, params);

        return true;
    }


    public void loadProfile(){
        DatabaseUtility dbUtil = DatabaseUtility.getInstance();
        String querySQL = "SELECT instuctor_id, user_id, cities_availability, specialization_code\n" +
                "FROM sports.INSTRUCTOR\n" +
                "where user_id = '" + this.getUserID() + "';" ;

        ResultSet rs = dbUtil.executeQuery(querySQL);
        try {
            while (rs.next()) {
                this.inctructorID = rs.getInt("instuctor_id");

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        // Close the connection

    }


}
