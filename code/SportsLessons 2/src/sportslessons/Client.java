package sportslessons;

public class Client extends User {
    private int age; // Add age attribute

    // Update constructor to include age
    public Client(String userID, String password, String phoneNumber, int age) {
        super(userID, password, phoneNumber, User.USER_TYPE.CLIENT);
        this.age = age; // Set age
    }

    // Getter for age
    public int getAge() {
        return age;
    }

    // Setter for age (optional)
    public void setAge(int age) {
        this.age = age;
    }
}
