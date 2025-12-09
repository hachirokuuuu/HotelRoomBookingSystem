import java.util.Scanner;
public class Staff {
    public String staffID;
    public String username;


    public boolean login(Scanner sc) {
        this.staffID = "";
        this.username = "";

        System.out.print("Enter Staff ID: ");
        this.staffID = sc.nextLine();

        System.out.print("Enter Username: ");
        this.username = sc.nextLine();

        if(this.staffID.equals("1234") && 
        this.username.equals("admin")) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid credentials. Login failed.");
            return false;
        }
    }

    public void updateRoomStatus(String code, Scanner sc) {
        if (code == null || code.trim().isEmpty()) {
            System.out.println("Invalid reservation code.");
            return;
        }
        boolean found = false;
        for (Reservation res : App.allReserv) {
            if (res.reservationCode != null && res.reservationCode.equalsIgnoreCase(code)) {
                System.out.print("Enter new status (PENDING, CONFIRMED, CANCELLED): ");
                String newStatusStr = sc.nextLine();
                switch(newStatusStr.toUpperCase()) {
                    case "PENDING":
                        res.status = Reservation.ReservationStatus.PENDING;
                        found = true;
                        break;
                    case "CONFIRMED":
                        res.status = Reservation.ReservationStatus.CONFIRMED;
                        found = true;
                        break;
                    case "CANCELLED":
                        res.status = Reservation.ReservationStatus.CANCELLED;
                        found = true;
                        break;
                    default:
                        System.out.println("Invalid status entered.");
                        return;
                }
                break;
            }
        }
        if (found) {
            DataManager.updateFile(App.allReserv);
            System.out.println("Reservation status updated successfully.");
        } else {
            System.out.println("Reservation not found.");
        }
    }

    public static void viewReservation(String code) {
        if (code == null || code.trim().isEmpty()) {
            System.out.println("Invalid reservation code.");
            return;
        }
        boolean found = false;
        for (Reservation res : App.allReserv) {
            if (res.reservationCode != null && res.reservationCode.equalsIgnoreCase(code)) {
                System.out.println("\nReservation Found:");
                System.out.println("Room: " + res.roomNumber);
                System.out.println("Date: " + res.checkInDate + " - " + res.checkOutDate);
                System.out.println("Status: " + res.status);
                found = true;
                break;
            }
        }
        if (!found) System.out.println("Reservation not found.");
    }

    public void viewAllReservations() {
        if (App.allReserv.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }
        System.out.println("Viewing all reservations:");
        for (Reservation res : App.allReserv) {
            System.out.println("Reservation Code: " +  res.reservationCode +
                               " | Room: " + res.roomNumber +
                               " | Date: " + res.checkInDate + " - " + res.checkOutDate +
                               " | Status: " + res.status);
        }
    }
}