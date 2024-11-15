/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportslessons;


public class Administrator extends User {

    public Administrator(String userID, String password, String phoneNumber) {
        super(userID, password, phoneNumber, User.USER_TYPE.ADMIN);
    }
    
}
