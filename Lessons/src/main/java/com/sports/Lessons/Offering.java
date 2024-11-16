package com.sports.Lessons;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Offering {

    public void viewPublicOfferings(){

        DatabaseUtility dbUtil = DatabaseUtility.getInstance();
        String querySQL = "SELECT ls.lesson_id, ls.title, i.instuctor_id, ls.specialization_code, ls.group_size, ls.time_slot_id, ts.`day`, ts.start_time, ts.end_time , ts.location_code, l.city_code, u.first_name, u.last_name \n" +
                "FROM sports.LESSONS ls\n" +
                "INNER JOIN TIME_SLOTS ts ON ts.time_slot_id = ls.time_slot_id \n" +
                "INNER JOIN LOCATIONS l ON l.location_code  = ts.location_code \n" +
                "INNER JOIN REGISTER_OFFER ro ON ro.lesson_id = ls.lesson_id\n" +
                "INNER JOIN INSTRUCTOR i ON i.instuctor_id  = ro.instuctor_id \n" +
                "INNER JOIN USERS u  ON u.user_id = i.user_id" ;

        ResultSet rs = dbUtil.executeQuery(querySQL);
        System.out.println("Public Offerings");
        System.out.println("----------------------------------------------------");
        try {
            while (rs.next()) {
                int lesson_id = rs.getInt("lesson_id");
                String title = rs.getString("title");
                System.out.print("Lesson: (");
                System.out.print(lesson_id);
                System.out.print(") ");
                System.out.println(title);

                System.out.print("Locationn: ");
                String location_code = rs.getString("location_code");
                System.out.println(location_code);

                String day = rs.getString("day");
                System.out.print("Day: ");
                System.out.println(day);

                String startTime = rs.getString("start_time");
                System.out.print("From: ");
                System.out.println(startTime);

                System.out.print("To: ");
                String endTime = rs.getString("end_time");
                System.out.println(endTime);

                System.out.print("Instructor : (");
                System.out.print(rs.getInt("instuctor_id"));
                System.out.print(") ");
                String name = rs.getString("first_name") + ", " + rs.getString("last_name");
                System.out.println(name);

                System.out.println("----------------------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }


    public void viewLesson(){

        DatabaseUtility dbUtil = DatabaseUtility.getInstance();
        String querySQL = "SELECT ls.title, ls.specialization_code, ls.group_size, ls.time_slot_id, ts.`day`, ts.start_time, ts.end_time , ts.location_code, l.city_code, u.first_name, u.last_name \n" +
                "FROM sports.LESSONS ls\n" +
                "INNER JOIN TIME_SLOTS ts ON ts.time_slot_id = ls.time_slot_id \n" +
                "INNER JOIN LOCATIONS l ON l.location_code  = ts.location_code" ;


        ResultSet rs = dbUtil.executeQuery(querySQL);
        System.out.println("Public Offerings");
        System.out.println("----------------------------------------------------");
        try {
            while (rs.next()) {
                int lesson_id = rs.getInt("lesson_id");
                String title = rs.getString("title");
                System.out.print("Lesson: (");
                System.out.print(lesson_id);
                System.out.print(") ");
                System.out.println(title);

                System.out.print("Locationn: ");
                String location_code = rs.getString("location_code");
                System.out.println(location_code);

                String day = rs.getString("day");
                System.out.print("Day: ");
                System.out.println(day);

                String startTime = rs.getString("start_time");
                System.out.print("From: ");
                System.out.println(startTime);

                System.out.print("To: ");
                String endTime = rs.getString("end_time");
                System.out.println(endTime);



                System.out.println("----------------------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public boolean createLesson(String title, String specialization_code, int groupSize, int timeSlotID){

        DatabaseUtility dbUtil = DatabaseUtility.getInstance();
        // Insert data using prepared statement
        //INSERT INTO `sports`.`LESSONS` (`lesson_id`, `title`, `specialization_code`, `group_size`, `time_slot_id`) VALUES (?, ?, ?, ?, ?);
        String insertSQL = "INSERT INTO `sports`.`LESSONS` ( `title`, `specialization_code`, `group_size`, `time_slot_id`) VALUES (?, ?, ?, ?)";
        Object[] params = {title, specialization_code, groupSize, timeSlotID};
        dbUtil.executePreparedUpdate(insertSQL, params);
        return true;

    }

    public void viewInstructorOfferings(String userID){

        DatabaseUtility dbUtil = DatabaseUtility.getInstance();
        String querySQL = "SELECT ls.lesson_id, ls.title, i.instuctor_id, ls.specialization_code, ls.group_size, ls.time_slot_id, ts.`day`, ts.start_time, ts.end_time , ts.location_code, l.city_code, u.first_name, u.last_name \n" +
                "FROM sports.LESSONS ls\n" +
                "INNER JOIN TIME_SLOTS ts ON ts.time_slot_id = ls.time_slot_id \n" +
                "INNER JOIN LOCATIONS l ON l.location_code  = ts.location_code \n" +
                "INNER JOIN REGISTER_OFFER ro ON ro.lesson_id = ls.lesson_id\n" +
                "INNER JOIN INSTRUCTOR i ON i.instuctor_id  = ro.instuctor_id \n" +
                "INNER JOIN USERS u  ON u.user_id = i.user_id\n" +
                "WHERE u.user_id = '" + userID + "'";

        ResultSet rs = dbUtil.executeQuery(querySQL);
        System.out.println("My Offerings");
        System.out.println("----------------------------------------------------");
        try {
            while (rs.next()) {
                int lesson_id = rs.getInt("lesson_id");
                String title = rs.getString("title");
                System.out.print("Lesson: (");
                System.out.print(lesson_id);
                System.out.print(") ");
                System.out.println(title);

                System.out.print("Locationn: ");
                String location_code = rs.getString("location_code");
                System.out.println(location_code);

                String day = rs.getString("day");
                System.out.print("Day: ");
                System.out.println(day);

                String startTime = rs.getString("start_time");
                System.out.print("From: ");
                System.out.println(startTime);

                System.out.print("To: ");
                String endTime = rs.getString("end_time");
                System.out.println(endTime);

                System.out.println("----------------------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }


    public void viewClientOfferings(String userID){

        DatabaseUtility dbUtil = DatabaseUtility.getInstance();
        String querySQL = "SELECT ls.lesson_id, ls.title, c.client_id, ls.specialization_code, ls.group_size, ls.time_slot_id, ts.`day`, ts.start_time, ts.end_time , ts.location_code, l.city_code, u.first_name, u.last_name \n" +
                "FROM sports.LESSONS ls\n" +
                "INNER JOIN TIME_SLOTS ts ON ts.time_slot_id = ls.time_slot_id \n" +
                "INNER JOIN LOCATIONS l ON l.location_code  = ts.location_code \n" +
                "INNER JOIN BOOKINGS b ON b.lesson_id = ls.lesson_id\n" +
                "INNER JOIN CLIENT c ON c.client_id  = b.client_id \n" +
                "INNER JOIN USERS u  ON u.user_id = c.user_id\n" +
                "WHERE u.user_id = '" + userID + "'";

        ResultSet rs = dbUtil.executeQuery(querySQL);
        System.out.println("My Bookings");
        System.out.println("----------------------------------------------------");
        try {
            while (rs.next()) {
                int lesson_id = rs.getInt("lesson_id");
                String title = rs.getString("title");
                System.out.print("Lesson: (");
                System.out.print(lesson_id);
                System.out.print(") ");
                System.out.println(title);

                System.out.print("Locationn: ");
                String location_code = rs.getString("location_code");
                System.out.println(location_code);

                String day = rs.getString("day");
                System.out.print("Day: ");
                System.out.println(day);

                String startTime = rs.getString("start_time");
                System.out.print("From: ");
                System.out.println(startTime);

                System.out.print("To: ");
                String endTime = rs.getString("end_time");
                System.out.println(endTime);

                System.out.println("----------------------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}
