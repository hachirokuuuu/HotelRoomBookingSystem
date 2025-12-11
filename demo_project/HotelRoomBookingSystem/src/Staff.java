import java.util.List;
import java.util.Scanner;
public class Staff {
    public String staffID;
    public String username;


    public void login(Scanner sc) {
        this.staffID = "";
        this.username = "";
        System.out.print("Enter Staff ID: ");
        this.staffID = sc.nextLine();
        System.out.print("Enter Username: ");
        this.username = sc.nextLine();
        if(this.staffID.equals("1234") && this.username.equals("admin")) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid credentials. Login failed.");
        }        
    }

    public void updateRoomStatus(Room room, String newStatus, String roomNumber) {
        System.out.println("Room " + roomNumber + " status updated to " + newStatus);
    }

    public void viewReservation(String reservationCode) {
        Reservation reservation = new Reservation();
        System.out.println("Viewing reservation with code: " + reservationCode);
        reservation.viewDetails();
    }

    public void viewAllReservations() {
        // View all reservations logic here
        System.out.println("Viewing all reservations.");
        List.of(Room.isAvailable = false);
    
}
}