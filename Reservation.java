import java.time.LocalDate;
import java.util.Random;

public class Reservation {
    public String reservationCode;
    public int roomNumber;
    public LocalDate checkInDate;
    public LocalDate checkOutDate;
    public double totalPrice;
    public ReservationStatus status;
    
public enum ReservationStatus {
    PENDING,
    CONFIRMED,
    CANCELLED
}

 public String codeGenerator(){
     String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
     StringBuilder sb = new StringBuilder();
     Random rnd = new Random();
     for(int i=0; i<6; i++) sb.append(chars.charAt(rnd.nextInt(chars.length())));
     this.reservationCode = sb.toString();
     return this.reservationCode;
 }
}