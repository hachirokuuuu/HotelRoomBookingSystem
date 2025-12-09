import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DataManager {
    private static final String FILENAME = "reservations.txt";

    public static void updateFile(List<Reservation> Allreservations) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME, false))) {
            for (Reservation r : Allreservations) {
                //CSV format ResCode,roomNum,Check_in,Check_out,Status
                String line = r.reservationCode + "," +
                              r.roomNumber + "," +
                              r.checkInDate + "," +
                              r.checkOutDate + "," +
                              r.status;
                bw.write(line);
                bw.newLine();
            }
        }
        catch (IOException e){
            System.out.println("Error writing to file!!!" + e.getMessage());
        }
    }

    public static List<Reservation> readFile() {
        List<Reservation> list = new ArrayList<>();
        File file = new File(FILENAME);
        if (!file.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = br.readLine()) != null){
                String[] data = line.split(",");
                if(data.length >= 4){
                    Reservation res = new Reservation();
                    res.reservationCode = data[0];
                    res.roomNumber = Integer.parseInt(data[1]);
                    res.checkInDate = LocalDate.parse(data[2]);
                    res.checkOutDate = LocalDate.parse(data[3]);

                    if(data.length > 4){
                        res.status = Reservation.ReservationStatus.valueOf(data[4]);
                    }
                    else{
                        res.status = Reservation.ReservationStatus.PENDING;
                    }
                    list.add(res);
                }
            }
        }
        catch (Exception e){
            System.out.println("Error reading from file!!!" + e.getMessage());
        }
        return list;
    }
}
