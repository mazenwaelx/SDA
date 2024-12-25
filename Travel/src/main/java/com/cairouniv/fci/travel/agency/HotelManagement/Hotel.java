package com.cairouniv.fci.travel.agency.HotelManagement;

import com.cairouniv.fci.travel.agency.EventManager.Event;
import com.cairouniv.fci.travel.agency.UserManagement.User;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String hotelName;
    private String hotelLocation;
    private List<Event> events;
    private List<Room> rooms;
    private List<Booking> bookings = new ArrayList<>();
    private List<Event> nearbyEvents = new ArrayList<>();
    private String endDate;

    public List<Event> getNearbyEvents() {
        return nearbyEvents;
    }
    public void setNearbyEvents(List<Event> nearbyEvents) {
        this.nearbyEvents = nearbyEvents;
    }
    public void addNearbyEvent(Event event) {
        this.nearbyEvents.add(event);
    }
    public List<Booking> getBookings() {
        return bookings;
    }
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }
    public Hotel() {
        this.events = new ArrayList<>();
        this.rooms = new ArrayList<>();
    }
    public Hotel(String hotelName, String hotelLocation) {
        this.hotelName = hotelName;
        this.hotelLocation = hotelLocation;
        this.events = new ArrayList<>();
        this.rooms = new ArrayList<>();
    }
    public String getHotelName() {
        return hotelName;
    }
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
    public String getHotelLocation() {
        return hotelLocation;
    }
    public void setHotelLocation(String hotelLocation) {
        this.hotelLocation = hotelLocation;
    }
    public List<Event> getEvents() {
        return events;
    }
    public void setEvents(List<Event> events) {
        this.events = events;
    }
    public List<Room> getRooms() {
        return rooms;
    }
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
    public void addEvent(Event event) {
        this.events.add(event);
    }
    public void addRoom(Room room) {
        this.rooms.add(room);
    }
    public String getHotelDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Hotel Name: ").append(hotelName).append("\n");
        details.append("Hotel Location: ").append(hotelLocation).append("\n");
        details.append("Events: ");

        if (events.isEmpty()) {
            details.append("No events available.");
        } else {
            for (Event event : events) {
                details.append("\n").append(event.getEventDetails());
            }
        }

        details.append("\nRooms: ");
        if (rooms.isEmpty()) {
            details.append("No rooms available.");
        } else {
            for (Room room : rooms) {
                details.append("\n").append(room.getRoomDetails());
            }
        }

        return details.toString();
    }
    public List<Booking> getBookingsByUser(User user) {
        List<Booking> userBookings = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getUser().equals(user)) {
                userBookings.add(booking);
            }
        }
        return userBookings;
    }
    public Booking createHotelBooking(User user, Room room, String startDate, String endDate) {
        this.endDate = endDate;
        if (!room.Checkavailbility()) {
            throw new IllegalArgumentException("Room is not available for booking.");
        }
        room.setAvailable(false);
        Booking booking = new Booking("B" + System.currentTimeMillis(), startDate, user);
        addBooking(booking);
        return booking;
    }

}
