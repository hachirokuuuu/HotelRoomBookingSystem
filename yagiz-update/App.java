import javax.xml.crypto.Data;
import java.sql.SQLOutput;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class App {

    static roomManager RM = new roomManager();
    static List<Reservation> allReserv = new ArrayList<Reservation>();

    // Logic to search available rooms based on criteria
    public static void searchAvailableRooms(searchCriteria criteria) {
        System.out.println("Available rooms based on search criteria:");
        boolean found = false;

        for (Room room : RM.getRooms) {
            if(room.capacity < criteria.capacity) continue;
            if(!room.withBalcony && criteria.withBalcony) continue;

            boolean isAvailable = true;
            for(Reservation reservation : allReserv) {
                if(reservation.roomNumber == room.roomNumber && reservation.status != Reservation.ReservationStatus.CANCELLED){
                    if(criteria.checkInDate.isBefore(reservation.checkOutDate) &&
                    criteria.checkOutDate.isAfter(reservation.checkInDate)){
                        isAvailable = false;
                        break;
                    }
                }
            }
            if(isAvailable){
                long days = ChronoUnit.DAYS.between(criteria.checkInDate, criteria.checkOutDate);
                found = true;
                System.out.println("Available rooms found!");
                System.out.println("Room number: " + room.roomNumber +
                                    "|Capacity: " + room.capacity +
                                    "|Price: " + (room.price * days) +
                                    "|Balcony: " + (room.withBalcony ? "Yes" : "No"));
            }
        }
        if(!found) System.out.println("No available rooms found!");
    }

    public static void main(String[] args) {
        allReserv = DataManager.readFile();

        System.out.println("Welcome to the Hotel Room Booking System!");
        System.out.println("-----------------------------------------\n"
            +"Please select your role:\n1. Staff\n2. Guest\n"
            +"-----------------------------------------"
        );
        Scanner sc = new Scanner(System.in);
        int roleChoice = sc.nextInt();
        sc.nextLine();

        switch(roleChoice) {
            case 1:
                Staff staff = new Staff();
                staff.login();
                System.out.println(allReserv.size() + "reservations have been made");


                // Staff menu


                break;
            case 2:
                Guest guest = new Guest();

                if (guest.login()) {
                    boolean guestExit = false;

                    while (!guestExit) {
                        System.out.println("\n--- GUEST MENU ---");
                        System.out.println("1. Search & Book Room (Oda Ara ve Ayırt)");
                        System.out.println("2. View My Reservation (Rezervasyonumu Gör)");
                        System.out.println("3. Cancel Reservation (İptal Et)");
                        System.out.println("4. Logout (Çıkış)");
                        System.out.print("Choice: ");

                        int guestChoice = sc.nextInt();
                        sc.nextLine();

                        switch (guestChoice) {
                            case 1:
                                System.out.println("\n--- Search Criteria ---");
                                searchCriteria criteria = new searchCriteria();

                                try {
                                    System.out.print("Check-in Date (YYYY-MM-DD): ");
                                    criteria.checkInDate = LocalDate.parse(sc.nextLine());

                                    System.out.print("Check-out Date (YYYY-MM-DD): ");
                                    criteria.checkOutDate = LocalDate.parse(sc.nextLine());

                                    System.out.print("Person Count: ");
                                    criteria.capacity = sc.nextInt();

                                    System.out.print("Balcony? (true/false): ");
                                    criteria.withBalcony = sc.nextBoolean();

                                    // Müsait odaları listele
                                    searchAvailableRooms(criteria);

                                    // Kullanıcıya rezervasyon yapmak isteyip istemediğini sor
                                    System.out.print("\nEnter Room Number to book (or 0 to cancel): ");
                                    int roomToBook = sc.nextInt();

                                    if (roomToBook != 0) {
                                        // Rezervasyon yapma metodunu çağır (App sınıfındaki static metod)
                                        guest.createBooking(roomToBook, criteria.checkInDate, criteria.checkOutDate);
                                    }

                                } catch (Exception e) {
                                    System.out.println("Invalid input! Please use correct formats.");
                                    sc.nextLine(); // Hata olursa bufferı temizle
                                }
                                break;

                            case 2:
                                System.out.print("Enter Reservation Code: ");
                                String codeView = sc.nextLine();

                                guest.viewBooking(codeView);
                                break;

                            case 3:
                                System.out.print("Enter Reservation Code to Cancel: ");
                                String codeCancel = sc.nextLine();
                                guest.cancelBooking(codeCancel);
                                break;

                            case 4:
                                System.out.println("Logging out...");
                                guestExit = true;
                                break;

                            default:
                                System.out.println("Invalid input!");
                        }
                    }
                }
                break;
            default:
                System.out.println("Invalid choice. Exiting.");
                break;
        }
        sc.close();
    }
}
