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
    this.reservationCode = String.valueOf(Math.random() * 1000000).substring(0, 6);
    return reservationCode;
 }
}
