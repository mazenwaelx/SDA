package Service.booking_manag;

import Model.User;
import Model.Room;

import java.util.ArrayList;
import java.util.List;

class BasicHotelBooking extends HotelBooking {
    public BasicHotelBooking(String bookingId, String bookingDate, User user, Room room) {
        super(bookingId, bookingDate, user, room);
    }

    @Override
    public List<Room> searchRooms(String location, String checkIn, String checkOut) {
        // Dummy implementation
        List<Room> availableRooms = new ArrayList<>();
        availableRooms.add(new Room("R201", Room.RoomType.FAMILY, 150.0));
        return availableRooms;
    }
}