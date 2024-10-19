/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportslessons;


public class Lesson {
    private String title;
    private Speciality specialization;
    private TimeSlot timeSlot;

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }
    
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Speciality getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Speciality specialization) {
        this.specialization = specialization;
    }

    public Lesson(String title, Speciality specialization) {
        this.title = title;
        this.specialization = specialization;
    }

    public Lesson(String title, Speciality specialization, TimeSlot timeSlot) {
        this.title = title;
        this.specialization = specialization;
        this.timeSlot = timeSlot;
    }
    
    
}
