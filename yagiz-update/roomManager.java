import java.util.List;
import java.util.ArrayList;

public class roomManager {
    private List<Room> rooms;

    public roomManager() {
        rooms = new ArrayList<>();
        initRooms();
    }

    public void initRooms() {
        rooms.add(new Room(101,1,500,false));
        rooms.add(new Room(102,1,500,false));
        rooms.add(new Room(103,1,500,false));
        rooms.add(new Room(104,1,500,false));
        rooms.add(new Room(105,1,600,true));
        rooms.add(new Room(106,1,600,true));
        rooms.add(new Room(107,1,600,true));

        rooms.add(new Room(201,2,800,false));
        rooms.add(new Room(202,2,800,false));
        rooms.add(new Room(203,2,800,false));
        rooms.add(new Room(204,2,800,false));
        rooms.add(new Room(205,2,900,true));
        rooms.add(new Room(206,2,900,true));
        rooms.add(new Room(207,2,900,true));

        rooms.add(new Room(301,3,1100,false));
        rooms.add(new Room(302,3,1100,false));
        rooms.add(new Room(303,3,1100,false));
        rooms.add(new Room(304,3,1100,false));
        rooms.add(new Room(305,3,1200,true));
        rooms.add(new Room(306,3,1200,true));
        rooms.add(new Room(307,3,1200,true));

        rooms.add(new Room(401,4,1400,false));
        rooms.add(new Room(402,4,1400,false));
        rooms.add(new Room(403,4,1400,false));
        rooms.add(new Room(404,4,1400,false));
        rooms.add(new Room(405,4,1500,true));
        rooms.add(new Room(406,4,1500,true));
        rooms.add(new Room(407,4,1500,true));

    }

    public List<Room> getRooms() {
        return rooms;
    }
}
