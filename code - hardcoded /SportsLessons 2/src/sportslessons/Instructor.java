/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportslessons;

import java.util.ArrayList;


public class Instructor extends User {

    private Speciality specialization;
    private ArrayList<City> availability;

    public Instructor(String userID, String password, String phoneNumber) {
        super(userID, password, phoneNumber, User.USER_TYPE.INSTRUCTOR);
        availability = new ArrayList<City>();
    }

    public Speciality getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Speciality specialization) {
        this.specialization = specialization;
    }

    public void addCity(City city) {
        this.availability.add(city);
    }
    
    public boolean isAvailableInCity(City city){
        /*TODO: check if city is in*/
        
        return false;       /**/
    }
    
    
    
}
