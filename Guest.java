import java.time.LocalDate;
import java.util.Scanner;

public class Guest {
    public String guestID;
    public String firstName;
    public String lastName;

    public boolean login(Scanner sc) {
        this.guestID = "";
        this.firstName = "";
        this.lastName = "";

        System.out.print("Enter Guest ID: ");
        this.guestID = sc.nextLine();

        System.out.print("Enter First Name: ");
        this.firstName = sc.nextLine();

        System.out.print("Enter Last Name: ");
        this.lastName = sc.nextLine();

        if(this.guestID.equalsIgnoreCase("5555") &&
        this.firstName.equalsIgnoreCase("John") &&
        this.lastName.equalsIgnoreCase("Doe")) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid credentials. Login failed.");
            return false;
        }
    }

    public static void viewBooking(String code) {
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

    public static void createBooking(int roomNumber, LocalDate checkInDate, LocalDate checkOutDate) {
        // Validate dates
        if (checkInDate == null || checkOutDate == null) {
            System.out.println("Error: Dates cannot be null!");
            return;
        }
        if (checkOutDate.isBefore(checkInDate) || checkOutDate.isEqual(checkInDate)) {
            System.out.println("Error: Check-out date must be after check-in date!");
            return;
        }
        
        // Validate room exists
        boolean roomExists = false;
        for (Room room : App.RM.getRooms()) {
            if (room.roomNumber == roomNumber) {
                roomExists = true;
                break;
            }
        }
        if (!roomExists) {
            System.out.println("Error: Room number " + roomNumber + " does not exist!");
            return;
        }
        
        Reservation newReservation = new Reservation();
        newReservation.roomNumber = roomNumber;
        newReservation.checkInDate = checkInDate;
        newReservation.checkOutDate = checkOutDate;
        newReservation.status = Reservation.ReservationStatus.CONFIRMED;
        String resCode = newReservation.codeGenerator();

        App.allReserv.add(newReservation);
        DataManager.updateFile(App.allReserv);

        System.out.println("Reservation with code " + resCode + " has been made!");

    }
    public static void cancelBooking(String code) {
        if (code == null || code.trim().isEmpty()) {
            System.out.println("Invalid reservation code.");
            return;
        }
        boolean found = false;
        for (Reservation res : App.allReserv) {
            if (res.reservationCode != null && res.reservationCode.equalsIgnoreCase(code)) {
                res.status = Reservation.ReservationStatus.CANCELLED;
                found = true;
                System.out.println("Rezervasyon iptal edildi.");
                break;
            }
        }

        if (found) {
            // Dosyayı güncelle
            DataManager.updateFile(App.allReserv);
        } else {
            System.out.println("İptal edilecek rezervasyon bulunamadı.");
        }
    }


}