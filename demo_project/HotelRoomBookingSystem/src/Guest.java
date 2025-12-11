import java.util.Scanner;

public class Guest {
    public String guestID;
    public String firstName;
    public String lastName;

public enum paymentInfo {
    CREDIT_CARD,
    DEBIT_CARD,
    PAYPAL,
    BANK_TRANSFER }

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
        Reservation reservation = new Reservation();
        System.out.println("Please enter your reservation code: ");
        Scanner sc = new Scanner(System.in);
        reservationCode = sc.nextLine();
        sc.close();
        System.out.println("Viewing booking for guest: " + firstName + " " + lastName);
        reservation.viewDetails();
    }
    public void createBooking(searchCriteria criteria){
        Reservation reservation = new Reservation();
        reservation.updateStatus(Reservation.ReservationStatus.PENDING);



        
        System.out.println("Creating booking for guest: " + firstName + " " + lastName);
        reservation.updateStatus(Reservation.ReservationStatus.CONFIRMED);
    }
    public void cancelBooking(String reservationCode){
        System.out.println("Cancelling booking with ID: " + reservationCode + " for guest: " + firstName + " " + lastName);
    }


}
