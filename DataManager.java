import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DataManager {
    private static final String FILENAME = "reservations.txt";

    public static void updateFile(List<Reservation> Allreservations) {
        if (Allreservations == null) {
            System.out.println("Error: Reservation list is null!");
            return;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME, false))) {
            for (Reservation r : Allreservations) {
                if (r == null) continue; // Skip null reservations
                //CSV format ResCode,roomNum,Check_in,Check_out,Status
                String reservationCode = (r.reservationCode != null) ? r.reservationCode : "";
                String status = (r.status != null) ? r.status.toString() : "PENDING";
                String line = reservationCode + "," +
                              r.roomNumber + "," +
                              r.checkInDate + "," +
                              r.checkOutDate + "," +
                              status;
                bw.write(line);
                bw.newLine();
            }
        }
        catch (IOException e){
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static List<Reservation> readFile() {
        List<Reservation> list = new ArrayList<>();
        File file = new File(FILENAME);
        if (!file.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;
            while((line = br.readLine()) != null){
                lineNumber++;
                line = line.trim();
                if (line.isEmpty()) continue; // Skip empty lines
                
                String[] data = line.split(",");
                if(data.length >= 4){
                    try {
                        Reservation res = new Reservation();
                        res.reservationCode = data[0].trim();
                        if (res.reservationCode.isEmpty()) {
                            System.out.println("Warning: Skipping line " + lineNumber + " - empty reservation code");
                            continue;
                        }
                        
                        res.roomNumber = Integer.parseInt(data[1].trim());
                        res.checkInDate = LocalDate.parse(data[2].trim());
                        res.checkOutDate = LocalDate.parse(data[3].trim());

                        if(data.length > 4 && !data[4].trim().isEmpty()){
                            try {
                                res.status = Reservation.ReservationStatus.valueOf(data[4].trim().toUpperCase());
                            } catch (IllegalArgumentException e) {
                                System.out.println("Warning: Invalid status at line " + lineNumber + ", using PENDING");
                                res.status = Reservation.ReservationStatus.PENDING;
                            }
                        }
                        else{
                            res.status = Reservation.ReservationStatus.PENDING;
                        }
                        list.add(res);
                    } catch (NumberFormatException e) {
                        System.out.println("Warning: Invalid room number at line " + lineNumber + " - " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Warning: Error parsing line " + lineNumber + " - " + e.getMessage());
                    }
                } else {
                    System.out.println("Warning: Skipping invalid line " + lineNumber + " - insufficient data");
                }
            }
        }
        catch (IOException e){
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return list;
    }
}