import java.time.LocalDate;
import java.util.Scanner;

public class Guest {
    public String guestID;
    public String firstName;
    public String lastName;
    public paymentInfo paymentDetails;

    public boolean login() {
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
            return true;
        } else {
            System.out.println("Invalid credentials. Login failed.");
            return false;
        }
    }

    public static void viewBooking(String code) {
        boolean found = false;
        for (Reservation res : App.allReserv) {
            if (res.reservationCode.equalsIgnoreCase(code)) {
                System.out.println("\nRezervasyon Bulundu:");
                System.out.println("Oda: " + res.roomNumber);
                System.out.println("Tarih: " + res.checkInDate + " - " + res.checkOutDate);
                System.out.println("Durum: " + res.status);
                found = true;
                break;
            }
        }
        if (!found) System.out.println("Rezervasyon bulunamadı.");
    }

    public static void createBooking(int roomNumber, LocalDate checkInDate, LocalDate checkOutDate) {
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
        boolean found = false;
        for (Reservation res : App.allReserv) {
            if (res.reservationCode.equalsIgnoreCase(code)) {
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
