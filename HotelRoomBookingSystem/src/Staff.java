import java.util.Scanner;
public class Staff {
    public String staffID;
    public String username;


    public void login() {
        this.staffID = "";
        this.username = "";
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Staff ID: ");
        this.staffID = sc.nextLine();
        System.out.print("Enter Username: ");
        this.username = sc.nextLine();
        if(this.staffID.equals("1234") && this.username.equals("admin")) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid credentials. Login failed.");
        }        
        sc.close();
    }

    public void updateRoomStatus(Room room, RoomStatus newStatus, String roomNumber) {
        System.out.println("Room " + roomNumber + " status updated to " + newStatus);
    }

    public void viewReservation(String reservationCode) {
        // View reservation logic here
        System.out.println("Viewing reservation with code: " + reservationCode);
    }

    public void viewAllReservations() {
        // View all reservations logic here
        System.out.println("Viewing all reservations.");
    }
}