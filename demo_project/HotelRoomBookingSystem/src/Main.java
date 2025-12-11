import java.util.List;
import java.util.Scanner;
public class Main {

    public List<Room> rooms;{
        rooms = List.of(
            new Room("101", "Single", 100.0, false, true),
            new Room("102", "Double", 150.0, false, false),
            new Room("103", "Suite", 200.0, false, true),
            new Room("104", "Single", 150.0, true, true),
            new Room("105", "Double", 200.0, true, true),
            new Room("106", "Suite", 250.0, true, true),
            new Room("107", "Single", 100.0, false, true),
            new Room("108", "Double", 150.0, false, false),
            new Room("109", "Suite", 200.0, false, true),
            new Room("201", "Single", 100.0, false, true),
            new Room("202", "Double", 150.0, false, true),
            new Room("203", "Suite", 200.0, false, false),
            new Room("204", "Single", 150.0, true, false),
            new Room("205", "Double", 200.0, true, true),
            new Room("206", "Suite", 250.0, true, false),
            new Room("207", "Single", 100.0, false, true),
            new Room("208", "Double", 150.0, false, true),
            new Room("209", "Suite", 200.0, false, true)
        );
        
    }
    public static void searchAvailableRooms(searchCriteria criteria) {

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;

        System.out.println("Welcome to the Hotel Room Booking System!");
        System.out.println("-----------------------------------------\n"
            +"Please select your role:\n1. Staff\n2. Guest\n" + "3. Exit\n"
            +"-----------------------------------------"
        );
            while (isRunning) {
                System.out.println(">>>");
                String input = sc.nextLine();
                switch (input) {
                    case "1":
                        
                        Staff staff = new Staff();
                        staff.login(sc);
                        System.out.println("for updating a room's status use '-update' command.\n"
                            +"for viewing a reservation use '-viewres' command.\n"
                            +"for viewing all reservations use '-viewallres' command.\n");
                        String staffCommand = sc.nextLine();
                    

                        switch(staffCommand) {
                                case "-viewallres":
                                    staff.viewAllReservations();
                                    break;
                                case "-exit"    :
                                    isRunning = false;
                                    System.out.println("Exiting the system. Goodbye!");
                                    break;

                                    default:
                                    System.out.println("Invalid command.");
                            
                        }
                        break;
                    
                    case "2":
                        Guest guest = new Guest();
                        guest.login();
                        System.out.println("for viewing a booking use '-view' command.\n"
                            +"for creating a booking use '-create' command.\n"
                            +"for cancelling a booking use '-cancel' command.\n"
                        );
                        String guestCommand = sc.nextLine();

                        switch(guestCommand) {
                            case "-view":
                                System.out.println("Enter Reservation Code: ");
                                String reservationCode = sc.nextLine();
                                guest.viewBooking(reservationCode);
                                break;

                            case "-create":
                                searchCriteria criteria = new searchCriteria();
                                guest.createBooking(criteria);
                                break;

                            case "-cancel":
                                System.out.println("Enter Reservation Code: ");
                                String resCode = sc.nextLine();
                                guest.cancelBooking(resCode);
                                break;
                        }


                        break;
                    case "3":
                        isRunning = false;
                        System.out.println("Exiting the system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }


            }
        }
    }


