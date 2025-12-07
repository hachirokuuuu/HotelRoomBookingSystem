import java.time.LocalDate;

public class Room{
    public String roomNumber;
    public String roomType;
    public double price;
    public boolean withBalcony;
    public static boolean isAvailable;


    public Room(String roomNumber, String roomType, double price, boolean withBalcony, boolean isAvailable) {
        this.roomNumber = "R" + roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.withBalcony = withBalcony;
        this.isAvailable = isAvailable;
    }

    public Room(String roomNumber2, String roomType2, double price2, boolean withBalcony2, Boolean isAvailable) {
        //TODO Auto-generated constructor stub
    }

    public void updateStatus(String newStatus) {
        System.out.println("Room " + roomNumber + " status updated to " + newStatus);
    }
    public boolean isAvailable(LocalDate checkIn, LocalDate checkOut) {
        if(this.isAvailable) {
            return true;
        }
        return false;
    }

    
}