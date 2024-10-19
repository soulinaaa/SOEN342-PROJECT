package sportslessons;

import java.util.ArrayList;
import java.util.Scanner;

public class Organization {
    
    ArrayList<User> users;
    ArrayList<Location> locations;
    ArrayList<Lesson> lessons;
    
    Scanner scanner = new Scanner(System.in);

    public Organization() {
        users = new ArrayList<User>();
        locations = new ArrayList<Location>();
        lessons = new ArrayList<Lesson>();
        
        // Simulation data
        users.add(new Administrator("admin", "admin123", "000-000-0001"));        
        users.add(new Instructor("user1", "user1", "000-000-0002"));
        users.add(new Instructor("user2", "user2", "000-000-0003"));
        users.add(new Instructor("user3", "user3", "000-000-0004"));
    }
    
    public void run() {
        while (true) { // Loop to show the main menu continuously
            System.out.println("========================================");        
            System.out.println(" WELCOME TO SPORTS LESSONS ORGANIZATION ");        
            System.out.println("========================================");
            System.out.println("");
            System.out.println("(1) Login to the system");
            System.out.println("(2) Register");
            System.out.println("(3) Browse courses");
            System.out.println("(4) Exit");

            int menuchoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (menuchoice) {
                case 1:
                    loginLogic();
                    break;
                case 2:
                    registerLogic();
                    break;
                case 3:
                    browseCourses(); // Implement this method if you haven't
                    break;
                case 4:
                    System.out.println("Exiting the program. Thank you!");
                    return; // Exit the run method
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void loginLogic() {
        System.out.println("================= Login ===============");
        System.out.print("User ID: ");
        String userId = scanner.nextLine();
        
        User user = findUser(userId);        
        if (user == null) {
            System.out.println("User ID not found, try again...");
            return;
        }
        
        System.out.print("Password: ");
        String password = scanner.nextLine();
        if (!user.login(userId, password)) {
            System.out.println("Password is invalid, try again...");
            return;
        }
        
        System.out.println("Connected... ");
        System.out.print("Welcome " + user.getUserID());
        if (user.getUserType() == User.USER_TYPE.ADMIN) {
            System.out.println(", you are logged in as administrator.");
            showAdminMenu((Administrator) user);
        } else {
            System.out.println(", you are logged in as Instructor.");
            showInstructorMenu((Instructor) user);
        }
    }

    private void registerLogic() {
        System.out.println("==================================");
        System.out.println("Thank you for joining our community!");
        System.out.println("Will you register as a: ");
        System.out.println("(1) Client");
        System.out.println("(2) Instructor");
        System.out.println("==================================");

        int registerChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (registerChoice == 1) {
            registerClient(); // Implement this method
        } else if (registerChoice == 2) {
            registerInstructor();
        } else {
            System.out.println("Invalid choice, returning to main menu.");
        }
    }

    private void registerInstructor() {
        System.out.println("What is your username (userID): ");
        String username = scanner.nextLine();
        System.out.println("Choose a password: ");
        String password = scanner.nextLine();
        System.out.println("Enter phone number: ");
        String phone = scanner.nextLine();

        users.add(new Instructor(username, password, phone));
        System.out.println("Instructor registered successfully!");
    }

    // Implement this for client registration
    private void registerClient() {
        System.out.println("What is your username (userID): ");
        String username = scanner.nextLine();
        System.out.println("Choose a password: ");
        String password = scanner.nextLine();
        System.out.println("Enter phone number: ");
        String phone = scanner.nextLine();
        System.out.println("Enter age: ");
        int age = scanner.nextInt();
        

        users.add(new Client(username, password, phone, age));
        System.out.println("Client registered successfully!");
    }
    
    private User findUser(String userId) {                
        for (User user : users) {            
            if (user.getUserID().equals(userId)) {
                return user;                
            }
        }
        return null; // User not found
    }
    
    public void showAdminMenu(Administrator admin) {
        while (true) { // Loop for the admin menu
            System.out.println("=== Administrator Menu ===");
            System.out.println("(1) Add location");
            System.out.println("(2) Add course");
            System.out.println("(3) Logout");
            System.out.println("==================================");

            int adminChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (adminChoice) {
                case 1:
                    // Implement add location logic here
                    System.out.println("Adding location (not implemented).");
                    break;
                case 2:
                    // Implement add course logic here
                    System.out.println("Adding course (not implemented).");
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return; // Return to main menu
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
    
    public void showInstructorMenu(Instructor instructor) {
        while (true) { // Loop for the instructor menu
            System.out.println("=== Instructor Menu ===");
            System.out.println("(1) Register lesson");
            System.out.println("(2) Logout");
            System.out.println("==================================");

            int instructorChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (instructorChoice) {
                case 1:
                    // Implement register lesson logic here
                    System.out.println("Registering lesson (not implemented).");
                    break;
                case 2:
                    System.out.println("Logging out...");
                    return; // Return to main menu
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
    
    // Implement this method to browse courses
    private void browseCourses() {
        System.out.println("Browse courses feature is not implemented yet.");
    }

    public static void main(String[] args) {
        Organization organization = new Organization();
        organization.run();
    }
}
