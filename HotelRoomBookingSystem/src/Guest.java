import java.util.Scanner;

public class Guest {
    public String guestID;
    public String firstName;
    public String lastName;
    public paymentInfo paymentDetails;

    public void login() {
        this.guestID = "";
        this.firstName = "";
        this.lastName = "";
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Guest ID: ");
        this.guestID = sc.nextLine();
        System.out.print("Enter First Name: ");
        this.firstName = sc.nextLine();
        System.out.print("Enter Last Name: ");
        this.lastName = sc.nextLine();
        if(this.guestID.equals("5555") &&
        this.firstName.equals("John") &&
        this.lastName.equals("Doe")) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid credentials. Login failed.");
        }   
        sc.close();     
    }

    public void viewBooking(String reservationCode){

        System.out.println("Viewing booking for guest: " + firstName + " " + lastName);
    }
    public void createBooking(searchCriteria criteria){
        System.out.println("Creating booking for guest: " + firstName + " " + lastName);
    }
    public void cancelBooking(String bookingID){
        System.out.println("Cancelling booking with ID: " + bookingID + " for guest: " + firstName + " " + lastName);
    }


}
