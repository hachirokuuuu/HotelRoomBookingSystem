import java.time.LocalDate;

public class Room{
    public int roomNumber;
    public int capacity;
    public double price;
    public boolean withBalcony;
    public RoomStatus status;

    enum RoomStatus {
        AVAILABLE,
        BOOKED,
        LOCKED
    }

    public Room(int roomNumber, int capacity, double price, boolean withBalcony) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
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