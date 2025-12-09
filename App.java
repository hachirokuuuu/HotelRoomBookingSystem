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
        // Validate dates
        if (criteria == null || criteria.checkInDate == null || criteria.checkOutDate == null) {
            System.out.println("Error: Invalid search criteria!");
            return;
        }
        if (criteria.checkOutDate.isBefore(criteria.checkInDate) || criteria.checkOutDate.isEqual(criteria.checkInDate)) {
            System.out.println("Error: Check-out date must be after check-in date!");
            return;
        }
        if (criteria.capacity <= 0) {
            System.out.println("Error: Capacity must be greater than 0!");
            return;
        }
        
        System.out.println("Available rooms based on search criteria:");
        boolean found = false;

        for (Room room : RM.getRooms()) {
            if(room.capacity < criteria.capacity) continue;
            if(!room.withBalcony && criteria.withBalcony) continue;

            boolean isAvailable = true;
            for(Reservation reservation : allReserv) {
                if(reservation != null && reservation.roomNumber == room.roomNumber && 
                   reservation.status != null && reservation.status != Reservation.ReservationStatus.CANCELLED){
                    if(reservation.checkInDate != null && reservation.checkOutDate != null &&
                       criteria.checkInDate.isBefore(reservation.checkOutDate) &&
                       criteria.checkOutDate.isAfter(reservation.checkInDate)){
                        isAvailable = false;
                        break;
                    }
                }
            }
            if(isAvailable){
                long days = ChronoUnit.DAYS.between(criteria.checkInDate, criteria.checkOutDate);
                found = true;
                System.out.println("Room number: " + room.roomNumber +
                                    " | Capacity: " + room.capacity +
                                    " | Price: " + (room.price * days) +
                                    " | Balcony: " + (room.withBalcony ? "Yes" : "No"));
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
        int roleChoice = 0;
        try {
            roleChoice = sc.nextInt();
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number.");
            sc.nextLine();
            sc.close();
            return;
        }

        switch(roleChoice) {
            case 1:
                Staff staff = new Staff();
                if (staff.login(sc)) {
                    boolean staffExit = false;

                    while (!staffExit) {
                        System.out.println("\n--- STAFF MENU ---");
                        System.out.println("1. View All Reservations (Tüm Rezervasyonları Gör)");
                        System.out.println("2. Update Reservation Status (Rezervasyon Durumunu Güncelle)");
                        System.out.println("3. Logout (Çıkış)");
                        System.out.print("Choice: ");

                        int staffChoice = 0;
                        try {
                            staffChoice = sc.nextInt();
                            sc.nextLine();
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please enter a number.");
                            sc.nextLine();
                            continue;
                        }

                        switch (staffChoice) {
                            case 1:
                                staff.viewAllReservations();
                                break;

                            case 2:
                                System.out.print("Enter Reservation Code to Update: ");
                                String codeUpdate = sc.nextLine();
                                staff.updateRoomStatus(codeUpdate, sc);
                                break;

                            case 3:
                                System.out.println("Logging out...");
                                staffExit = true;
                                break;

                            default:
                                System.out.println("Invalid input!");
                        }
                    }
                }



                break;
            case 2:
                Guest guest = new Guest();

                if (guest.login(sc)) {
                    boolean guestExit = false;

                    while (!guestExit) {
                        System.out.println("\n--- GUEST MENU ---");
                        System.out.println("1. Search & Book Room (Oda Ara ve Ayırt)");
                        System.out.println("2. View My Reservation (Rezervasyonumu Gör)");
                        System.out.println("3. Cancel Reservation (İptal Et)");
                        System.out.println("4. Logout (Çıkış)");
                        System.out.print("Choice: ");

                        int guestChoice = 0;
                        try {
                            guestChoice = sc.nextInt();
                            sc.nextLine();
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please enter a number.");
                            sc.nextLine();
                            continue;
                        }

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
                                    sc.nextLine(); // Consume newline

                                    System.out.print("Balcony? (true/false): ");
                                    String balconyInput = sc.nextLine().trim().toLowerCase();
                                    criteria.withBalcony = balconyInput.equals("true") || balconyInput.equals("yes") || balconyInput.equals("y");

                                    // Müsait odaları listele
                                    searchAvailableRooms(criteria);

                                    // Kullanıcıya rezervasyon yapmak isteyip istemediğini sor
                                    System.out.print("\nEnter Room Number to book (or 0 to cancel): ");
                                    try {
                                        int roomToBook = sc.nextInt();
                                        sc.nextLine(); // Consume newline

                                        if (roomToBook != 0) {
                                            // Rezervasyon yapma metodunu çağır (Guest sınıfındaki static metod)
                                            Guest.createBooking(roomToBook, criteria.checkInDate, criteria.checkOutDate);
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Invalid room number input.");
                                        sc.nextLine();
                                    }

                                } catch (Exception e) {
                                    System.out.println("Invalid input! Please use correct formats.");
                                    sc.nextLine(); // Hata olursa bufferı temizle
                                }
                                break;

                            case 2:
                                System.out.print("Enter Reservation Code: ");
                                String codeView = sc.nextLine();

                                Guest.viewBooking(codeView);
                                break;

                            case 3:
                                System.out.print("Enter Reservation Code to Cancel: ");
                                String codeCancel = sc.nextLine();
                                Guest.cancelBooking(codeCancel);
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