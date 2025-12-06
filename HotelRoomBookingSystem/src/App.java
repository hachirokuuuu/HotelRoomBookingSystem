import java.util.List;
import java.util.Scanner;
public class App {

    
    public static void searchAvailableRooms(searchCriteria criteria) {
        // Logic to search available rooms based on criteria
        List<Room> availableRooms = List.of(); // Placeholder for actual search results
        System.out.println("Available rooms based on search criteria:");
        for (Room room : availableRooms) {
            System.out.println("Room Number: " + room.roomNumber + ", Type: " + room.roomType + ", Price: " + room.price);
        }

    }
    public static void main(String[] args) {

        System.out.println("Welcome to the Hotel Room Booking System!");
        System.out.println("-----------------------------------------\n"
            +"Please select your role:\n1. Staff\n2. Guest\n"
            +"-----------------------------------------"
        );
        Scanner sc = new Scanner(System.in);
        int roleChoice = sc.nextInt();
        switch(roleChoice) {
            case 1:
                Staff staff = new Staff();
                staff.login();
                break;
            case 2:
                Guest guest = new Guest();
                guest.login();
                break;
            default:
                System.out.println("Invalid choice. Exiting.");
                break;
        }



    sc.close();

        Reservation reservation = new Reservation();
        String generatedCode = reservation.codeGenerator();
        System.out.println("Generated Reservation Code: " + generatedCode);
}
}
