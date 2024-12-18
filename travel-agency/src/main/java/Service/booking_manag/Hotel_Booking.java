package Service.booking_manag;

import Model.Booking;
import Model.User;
import Model.Room;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Hotel {
    private String hotelId;
    private String name;
    private String location;
    private List<Room> rooms;

    // Methods
    public List<Room> searchRooms(String location, String checkIn, String checkOut) {
        // Dummy implementation
        List<Room> availableRooms = new ArrayList<>();
        availableRooms.add(new Room("R201", Room.RoomType.FAMILY, 150.0));
        return availableRooms;
    }
}

abstract class HotelBooking extends Booking {
    private final Room room;

    public HotelBooking(String bookingId, String bookingDate, User user, Room room) {
        super(bookingId, bookingDate, (org.apache.catalina.User) user);
        this.room = room;
    }

    @Override
    public String getBookingDetails() {
        return "Service.notification.booking_manag.Hotel Model.Booking - Model.Room ID: " + room.getRoomId() +
                ", Model.Room Type: " + room.getRoomType() +
                ", Check-in Date: " + bookingDate;
    }

    public List<Room> searchRooms(String location, String checkIn, String checkOut) {
        // Dummy implementation for demonstration
        List<Room> availableRooms = new ArrayList<>();

        // Populate availableRooms based on location, check-in, and check-out dates
        // In real-world scenarios, this would query a database or API.
        availableRooms.add(new Room("R102", Room.RoomType.SINGLE, 80.0));
        availableRooms.add(new Room("R103", Room.RoomType.DOUBLE, 120.0));

        return availableRooms;
    }
}


