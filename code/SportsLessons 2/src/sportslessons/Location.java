/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportslessons;

import java.util.ArrayList;


public class Location {
    private String name;
    private City city;
    private Speciality specialization;   
    private ArrayList<TimeSlot> schedule;
    private ArrayList<Lesson> lessons;

    public Location(String name) {
        this.name = name;
        schedule = new ArrayList<TimeSlot>();
        lessons = new ArrayList<Lesson>();
    }

    public Location(String name, City city, Speciality specialization) {
        this.name = name;
        this.city = city;
        this.specialization = specialization;
        schedule = new ArrayList<TimeSlot>();
        lessons = new ArrayList<Lesson>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Speciality getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Speciality specialization) {
        this.specialization = specialization;
    }
    
    public void addTimeSlot(TimeSlot timeSlot){
        schedule.add(timeSlot);
    }
    
    /*TODO: add lesson, validate logic*/
    
    
}
