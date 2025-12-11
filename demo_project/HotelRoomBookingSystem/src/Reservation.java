import java.time.LocalDate;

public class Reservation {
    public String reservationCode;
    public LocalDate checkInDate;
    public LocalDate checkOutDate;
    public double totalPrice;
    public ReservationStatus status;
    
enum ReservationStatus {
    PENDING,
    CONFIRMED,
    CANCELLED,
    CHECKED_IN,
    CHECKED_OUT
}

 public String codeGenerator(){
    this.reservationCode = String.valueOf(Math.random() * 100000).substring(0, 6);
    return reservationCode;
 }

 public void updateStatus(ReservationStatus newStatus) {
    System.out.println("Reservation " + reservationCode + " status updated to " + newStatus);
 }

 public void cancel() {
    System.out.println("Reservation " + reservationCode + " has been cancelled.");
 }

 public void viewDetails() {
    System.out.println("Reservation Code: " + reservationCode);
    System.out.println("Check-In Date: " + checkInDate);
    System.out.println("Check-Out Date: " + checkOutDate);
    System.out.println("Total Price: " + totalPrice);
    System.out.println("Status: " + status);
 }


}
