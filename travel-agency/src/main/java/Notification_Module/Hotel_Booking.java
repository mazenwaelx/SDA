package Notification_Module;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Notification_Module.User;

class Hotel {
    private String hotelId;
    private String name;
    private String location;
    private List<Room> rooms;

    // Methods
    public List<Room> searchRooms(String location, Date checkIn, Date checkOut) {
        // Dummy implementation
        List<Room> availableRooms = new ArrayList<>();
        availableRooms.add(new Room("R201", Room.RoomType.FAMILY, 150.0));
        return availableRooms;
    }
}

@Getter
class Room {
    private final String roomId;
    private final RoomType roomType; // Enum: SINGLE, DOUBLE, FAMILY
    private final double price;

    // Constructor
    public Room(String roomId, RoomType roomType, double price) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.price = price;
    }

    public String getRoomId() {
        return this.roomId;
    }

    public RoomType getRoomType() {
        return this.roomType;
    }

    public enum RoomType {
        SINGLE,
        DOUBLE,
        FAMILY
    }
}

class HotelBooking extends Booking {
    private final Room room;

    public HotelBooking(String bookingId, Date bookingDate, User user, Room room) {
        super(bookingId, bookingDate, (org.apache.catalina.User) user);
        this.room = room;
    }

    @Override
    public String getBookingDetails() {
        return "Notification_Module.Hotel Notification_Module.Booking - Notification_Module.Room ID: " + room.getRoomId() +
                ", Notification_Module.Room Type: " + room.getRoomType() +
                ", Check-in Date: " + bookingDate;
    }

    public List<Room> searchRooms(String location, Date checkIn, Date checkOut) {
        // Dummy implementation for demonstration
        List<Room> availableRooms = new ArrayList<>();

        // Populate availableRooms based on location, check-in, and check-out dates
        // In real-world scenarios, this would query a database or API.
        availableRooms.add(new Room("R102", Room.RoomType.SINGLE, 80.0));
        availableRooms.add(new Room("R103", Room.RoomType.DOUBLE, 120.0));

        return availableRooms;
    }
}

