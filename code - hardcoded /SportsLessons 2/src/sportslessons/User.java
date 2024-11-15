/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportslessons;


public class User implements LoginInterface {
    
    enum USER_TYPE {ADMIN, INSTRUCTOR, CLIENT};
    
    
    private String userID;
    private String password;
    private String phoneNumber;
    private USER_TYPE userType;

    public USER_TYPE getUserType() {
        return userType;
    }

    public void setUserType(USER_TYPE userType) {
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
    switch (userId) {
        case "admin":
            return password.equals("admin123");
        case "user1":
            return password.equals("user1");
        case "user2":
            return password.equals("user2");
        case "user3":
            return password.equals("user3");
        default:
            return false;
    }
}


    public User(String userID, String password, String phoneNumber, USER_TYPE userType) {
        this.userID = userID;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
    }
    
    
}
