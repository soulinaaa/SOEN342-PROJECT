package com.sports.Lessons;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Client extends User {

    private int age;
    private String guardianName;
    private int clientID;

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public Client( String userID, String password, String phoneNumber, String userType, String firstName, String lastName, String email, int age, String guardianName) {
        super(userID, password, phoneNumber, userType, firstName, lastName, email);
        setAge(age);
        setGuardianName(guardianName);
    }

    public Client() {
        super();
    }

    public Client(User user) {
        super(user);
    }

    public boolean createClient(){

        DatabaseUtility dbUtil = DatabaseUtility.getInstance();
        // Insert data using prepared statement
        //INSERT INTO `sports`.`CLIENT` (`user_id`, `age`, `guardian_name`) VALUES (?, ?, ?)
        String insertSQL = "INSERT INTO `sports`.`CLIENT` (`user_id`, `age`, `guardian_name`) VALUES (?, ?, ?)";
        Object[] params = {getUserID(),getAge(),getGuardianName()};
        dbUtil.executePreparedUpdate(insertSQL, params);
        return true;

    }

    public boolean makeBooking(int lessonID, int clientID){
        DatabaseUtility dbUtil = DatabaseUtility.getInstance();
        // Insert data using prepared statement
        String insertSQL = "INSERT INTO `sports`.`BOOKINGS` (`lesson_id`, `client_id`) VALUES (?,?);\n";
        Object[] params = {lessonID, clientID};
        dbUtil.executePreparedUpdate(insertSQL, params);

        return true;
    }

    public void loadProfile(){
        DatabaseUtility dbUtil = DatabaseUtility.getInstance();
        String querySQL = "SELECT client_id, user_id, age, guardian_name\n" +
                "FROM sports.CLIENT\n" +
                "where user_id = '" + this.getUserID() + "';" ;

        ResultSet rs = dbUtil.executeQuery(querySQL);
        try {
            while (rs.next()) {
                this.clientID = rs.getInt("client_id");
                this.age = rs.getInt("age");
                this.guardianName = rs.getString("guardian_name");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        // Close the connection

    }

    public boolean createProfile(){

        return true;

    }
}

