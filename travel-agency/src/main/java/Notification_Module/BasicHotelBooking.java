package Notification_Module;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class BasicHotelBooking extends HotelBooking {
    public BasicHotelBooking(String bookingId, Date bookingDate, User user, Room room) {
        super(bookingId, bookingDate, user, room);
    }

    @Override
    public List<Room> searchRooms(String location, Date checkIn, Date checkOut) {
        // Dummy implementation
        List<Room> availableRooms = new ArrayList<>();
        availableRooms.add(new Room("R201", Room.RoomType.FAMILY, 150.0));
        return availableRooms;
    }
}