package com.sports.Lessons;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Organization {
    private static final Scanner scanner = new Scanner(System.in);
    private User loggedUser;
    public Organization() {
        loggedUser = null;
    }
    
    public void simulate(){
        
    }
    
    public void run() {
        // Display splash screen
        displaySplashScreen();
        displayPublicMenu();

        // Start login process
        /*
        User loggedInUser = login();

        if (loggedInUser != null){
            System.out.println("Welcome " + loggedInUser.getFirstName() + ", your are logged as : " + loggedInUser.getUserType());

            displayMenu(loggedInUser);

        }

         */

    }



    // Display the splash screen
    private void displaySplashScreen() {
        System.out.println("**********************************");
        System.out.println("* Welcome to the Sports App!    *");
        System.out.println("**********************************\n");
    }

    // Display menu for Admin users
    public void displayPublicMenu() {
        int choice = 0;
        while (choice != 5) {
            System.out.println("====================");
            System.out.println("Public Menu:");
            System.out.println("====================");
            System.out.println("1. View Offerings");
            System.out.println("2. Login");
            System.out.println("3. Register as Instructor");
            System.out.println("4. Register as Client");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    viewPublicOfferings();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    registerAsInstructor();
                    break;
                case 4:
                    registerAsClient();
                    break;
            }
        }
    }

    private void registerAsInstructor(){
        System.out.print("Enter your first: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter your username: ");
        String usernameInstructor = scanner.nextLine();

        System.out.print("Enter email: ");
        String emailInstructor = scanner.nextLine();

        System.out.print("Enter phone num: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Create a password: ");
        String password = scanner.nextLine();

        System.out.print("Enter your specialization (e.g., Yoga, Tennis): ");
        String specialization = scanner.nextLine();

        System.out.print("Enter your city :");
        String city = scanner.nextLine();

        //String userID, String password, String phoneNumber, String userType, String firstName, String lastName, String email, String cityAvailabilities, String specializationCode

        Instructor instructor = new Instructor(usernameInstructor, password, phoneNumber, USER_TYPE.INSTRUCTOR, firstName, lastName, emailInstructor, city, specialization);
         instructor.createUser();
         instructor.createInstructor();



        //check if it already exists
        /*if (users.containsKey(usernameInstructor)) {
            System.out.println("This username already exists. Please choose another one.");
            return;
        }*/

        //add the instructor to the db
    }

    private void viewPublicOfferings(){
        //System.out.println("==> TODO: viewPublicOfferings: Not Implemented yet");

        Offering offerings = new Offering();
        offerings.viewPublicOfferings();

    }

    private void registerAsClient(){
        System.out.print("Enter your first name : ");
        String firstName = scanner.nextLine();


        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter your username: ");
        String usernameClient = scanner.nextLine();

        System.out.print("Enter email: ");
        String emailClient = scanner.nextLine();

        System.out.print("Enter phone num: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Create a password: ");
        String password = scanner.nextLine();

        System.out.print("Enter your age : ");
        int ageClient = scanner.nextInt();

        String guardianName = usernameClient;

        if(ageClient<18){
            System.out.println(("Enter guardian name: "));
            //look where it stores
            guardianName = scanner.nextLine();
            scanner.nextLine();
            //System.out.println(guardianName + "hello");
        }

        //String userID, String password, String phoneNumber, String userType, String firstName, String lastName, String email, int age, String guardianName
        Client client = new Client(usernameClient, password, phoneNumber, USER_TYPE.CLIENT, firstName, lastName, emailClient, ageClient, guardianName);
        client.createUser();
        client.createClient();


        //check if it already exists
        /*if (users.containsKey(usernameClient)) {
            System.out.println("This username already exists. Please choose another one.");
            return;
        }*/

        //add the client to the db
    }

    // Perform the login operation with retry mechanism
    public void login() {
        int attempts = 3;

        System.out.println("====================");
        System.out.println("  Login");
        System.out.println("====================");
        while (attempts > 0) {
            System.out.print("Enter User ID: ");
            String userId = scanner.nextLine();

            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            // Authenticate user
            loggedUser = authenticate(userId, password);
            //changed user =>loggedUser
            if (loggedUser == null) {
                System.out.print("\nUser Id or password invalid! try again please...");
                attempts--;
                clearScreen();
            }
            else attempts = 0;

        }

        if (loggedUser != null) displayMenu(loggedUser);
    }

    public User authenticate(String userId, String password) {

        User user = new User(userId);
        boolean authenticated = user.login(userId, password);

        return authenticated?user:null;
    }

    private void displayMenu(User user) {
        clearScreen(); // Clear the screen before displaying the menu
        String userType = user.getUserType();
        if (USER_TYPE.ADMIN .equalsIgnoreCase(userType)) {
            displayAdminMenu(user);
        } else
        if (USER_TYPE.INSTRUCTOR .equalsIgnoreCase(userType)) {
            displayInstructorMenu(user);
        } else if (USER_TYPE.CLIENT .equalsIgnoreCase(userType)) {
            displayClientMenu(user);
        }
    }

    // Display menu for Admin users
    public void displayAdminMenu(User user) {

        Administrator admin;
        if(user instanceof Administrator){
            admin=(com.sports.Lessons.Administrator) user;

        }
        else
            admin = null;


        System.out.println("====================");
        System.out.println("Admin Menu:");
        System.out.println("====================");
        System.out.println("1. View Offerings");
        System.out.println("2. Add Offering");
        System.out.println("3. Delete Offering");
        System.out.println("4. View Bookings");
        System.out.println("5. Cancel Booking");
        System.out.println("6. View Clients");
        System.out.println("7. View Instructors");
        System.out.println("8. Delete Client");
        System.out.println("9. Delete Instructor");
        System.out.println("10. Logout");

        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        switch (choice){
            case 1:
                viewPublicOfferings();
                break;
            case 2:
                addLesson();
                break;
            case 3:
                deleteOffer();
                break;
            case 4:
                viewAllBookings();
                break;
            case 5:
                cancelBookings();
                break;
            case 6:
                viewClient();
                break;
            case 7:
                viewInstructor();
                break;
            case 8:
                deleteClient();
                break;
            case 9:
                deleteInstructor();
                break;
            case 10:
                //IMPLEMENT LOGOUT
                registerAsClient();
                break;

        }


    }
    private void addLesson(){
        // Prompt for offer details
        System.out.print("Enter lesson title: ");
        String titleLesson = scanner.nextLine();

        System.out.print("Enter specialization code: ");
        String specializationLesson = scanner.nextLine();

        System.out.print("Enter group size: ");
        int groupSizeLesson = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        System.out.print("Enter location code: ");
        String locationLesson = scanner.nextLine();

        System.out.print("Enter time slot : ");
        int timeSlotLesson = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        Offering lesson = new Offering();

        lesson.createLesson(titleLesson,specializationLesson,groupSizeLesson,timeSlotLesson);

    }
    private void deleteOffer(){
        System.out.println("==> TODO: deleteOffer: Not Implemented yet");
    }
    private void viewAllBookings(){
        System.out.println("==> TODO: viewAllBookings: Not Implemented yet");
    }
    private void cancelBookings(){
        System.out.println("==> TODO: cancelBookings: Not Implemented yet");
    }
    private void viewClient(){
        System.out.println("==> TODO: viewClient: Not Implemented yet");
    }
    private void viewInstructor(){
        System.out.println("==> TODO: viewInstructor: Not Implemented yet");
    }
    private void deleteClient(){


    }
    private void deleteInstructor(){
        System.out.println("==> TODO: viewInstructor: Not Implemented yet");
    }
    // Display menu for Instructor
    private void displayInstructorMenu(User user) {
        Instructor instructor = new Instructor(user);

        instructor.loadProfile();

        System.out.println("====================");
        System.out.println("Instructor Menu:");
        System.out.println("====================");
        System.out.println("1. View Offerings (client perpective)");
        System.out.println("2. View Lesson");
        System.out.println("3. Take Lesson");
        System.out.println("4. View my Offerings");
        System.out.println("5. Cancel Offering");
        System.out.println("6. Logout");

        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        switch (choice){
            case 1:
                viewPublicOfferings();
                break;
            case 2:
                viewLessonsByAdmin();
                break;
            case 3:
                takeLesson(instructor);
                break;
            case 4:
                viewMyOffers();
                break;
            case 5:
                cancelMyLesson();
                break;


        }



    }
    private void viewLessonsByAdmin(){

        Offering offer = new Offering();
        offer.viewLesson();
    }
    private void takeLesson(Instructor instructor){

        System.out.println("Enter lesson number: ");
        int lessonID = scanner.nextInt();
        instructor.registerLesson(lessonID, instructor.getInctructorID());
    }
    private void viewMyOffers(){
        Offering offering = new Offering();
        offering.viewInstructorOfferings(loggedUser.getUserID());

    }
    private void cancelMyLesson(){
        System.out.println("==> TODO: viewInstructor: Not Implemented yet");
    }




    // Display menu for Client
    private void displayClientMenu(User user) {

       Client client = new Client(user);
       client.loadProfile();

        System.out.println("====================");
        System.out.println("Client Menu:");
        System.out.println("====================");
        System.out.println("1. View Offerings");
        System.out.println("2. Make a Booking");
        System.out.println("3. View my Bookings");
        System.out.println("4. Cancel Booking");
        System.out.println("5. Logout");

        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        switch (choice){
            case 1:
                viewPublicOfferings();
                break;
            case 2:
                makeBooking(client);
                break;
            case 3:
                viewMyBookings();
                break;
            case 4:
                cancelMyBooking();
                break;
            case 5:
                //exit to implment
                cancelMyLesson();
                break;
        }
    }

    private void makeBooking(Client client){
        System.out.println("Enter lesson number: ");
        int lessonID = scanner.nextInt();
        client.makeBooking(lessonID, client.getClientID());
    }
    private void viewMyBookings(){
        Offering offering = new Offering();
        offering.viewClientOfferings(loggedUser.getUserID());
    }
    private void cancelMyBooking(){

        System.out.println("enter ID to delete: ");
        int choice = scanner.nextInt();
    }

    private void clearScreen() {
        try
        {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {

        }
    }



}
