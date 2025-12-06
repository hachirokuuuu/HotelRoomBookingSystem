import java.time.LocalDate;

public class Room{
    public String roomNumber;
    public String roomType;
    public double price;
    public boolean withBalcony;
    public RoomStatus status;

    enum RoomStatus {
        AVAILABLE,
        BOOKED,
        LOCKED
    }

    public Room(int roomNumber, String roomType, double price, boolean withBalcony) {
        this.roomNumber = "R" + roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.withBalcony = withBalcony;
        this.status = RoomStatus.AVAILABLE;
    }

    public void updateStatus(RoomStatus newStatus) {
        System.out.println("Room " + roomNumber + " status updated to " + newStatus);
    }
    public boolean isAvailable(LocalDate checkIn, LocalDate checkOut) {
        return true;
    }
}