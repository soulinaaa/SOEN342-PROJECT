package com.sports.Lessons;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Booking {

    private int bookingId;
    private int clientId;
    private int lessonId;

    public Booking( int clientId, int lessonId) {
        this.bookingId = bookingId;
        this.clientId = clientId;
        this.lessonId = lessonId;
    }

    // Default constructor
    public Booking() {}

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public boolean createBooking() {
        DatabaseUtility dbUtil = DatabaseUtility.getInstance();

        // SQL to insert a new booking record into the database
        String insertSQL = "INSERT INTO `sports`.`BOOKINGS` (`client_id`, `lesson_id`) VALUES (?, ?)";
        Object[] params = {getClientId(), getLessonId()};

        // Execute the insert query
        dbUtil.executePreparedUpdate(insertSQL, params);
        return true;
    }

    public void viewMyBookings(int clientID) {
        DatabaseUtility dbUtil = DatabaseUtility.getInstance();

        // SQL query to get the bookings for a specific client
        String querySQL = "SELECT b.booking_id, ls.title, ts.day, ts.start_time, ts.end_time, l.location_code " +
                "FROM sports.BOOKINGS b " +
                "INNER JOIN sports.LESSONS ls ON b.lesson_id = ls.lesson_id " +
                "INNER JOIN sports.TIME_SLOTS ts ON ls.time_slot_id = ts.time_slot_id " +
                "INNER JOIN sports.LOCATIONS l ON ts.location_code = l.location_code " +
                "WHERE b.client_id = ?";

        // Set the parameter for client_id
        Object[] params = {clientID};

        // Execute the query with the prepared statement
        ResultSet rs = dbUtil.executePreparedQuery(querySQL, params);

        // Print the header for the bookings
        System.out.println("Your Bookings");
        System.out.println("----------------------------------------------------");
        System.out.println("Booking ID   Lesson Title        Day      Time Slot       Location");
        System.out.println("----------------------------------------------------");

        try {
            // Loop through each result in the ResultSet
            while (rs.next()) {
                int bookingId = rs.getInt("booking_id");
                String title = rs.getString("title");
                String day = rs.getString("day");
                String startTime = rs.getString("start_time");
                String endTime = rs.getString("end_time");
                String locationCode = rs.getString("location_code");

                // Format the output for each booking
                System.out.printf("%-12d %-20s %-8s %-10s - %-10s %-12s%n", bookingId, title, day, startTime, endTime, locationCode);
            }
        } catch (SQLException e) {
            // Catch any SQL exceptions and print the error
            System.out.println("Error retrieving bookings: " + e.toString());
        }
    }

    public boolean cancelBooking(int bookingId) {
        DatabaseUtility dbUtil = DatabaseUtility.getInstance();

        // SQL query to delete a booking from the BOOKINGS table
        String deleteSQL = "DELETE FROM sports.BOOKINGS WHERE booking_id = ?";

        // Parameters for the prepared statement
        Object[] params = {bookingId};

        // Execute the delete query using a prepared update
        int rowsAffected = dbUtil.executePreparedUpdate(deleteSQL, params);

        // If rowsAffected is greater than 0, the booking was canceled successfully
        return rowsAffected > 0;
    }





}
