package com.cairouniv.fci.travel.agency.HotelManagement;

import com.cairouniv.fci.travel.agency.UserManagement.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Booking {
    public enum BookingType {
        HOTEL,
        EVENT;
    }
    public static BookingType BookingType;
    protected String bookingId;
    protected String flightId;
    protected String bookingDate;
    @JsonIgnore
    protected User user;
    protected Room room;
    public Room getRoom() {
        return room;
    }
    public void setRoom(Room room) {
        this.room = room;
    }
    public Booking(String bookingId, String bookingDate, User user) {
        this.bookingId = bookingId;
        this.bookingDate = String.valueOf(bookingDate);
        this.user = user;
    }
    public String getBookingId() {
        return bookingId;
    }
    public String getFlightId() {
        return flightId;
    }
    public String getBookingDate() {
        return bookingDate;
    }
    public User getUser() {
        return user;
    }
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }
    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getBookingDetails() {
        return String.format(
                bookingId,
                bookingDate,
                user != null ? user.getName() : "No User Assigned"

        );
    }
    public boolean isValidBooking() {
        return bookingId != null && !bookingId.isEmpty()
                && bookingDate != null && !bookingDate.isEmpty()
                && user != null;
    }
    public static void setBookingType(Booking.BookingType bookingType) {
        BookingType = bookingType;
    }
    public static Booking.BookingType getBookingType() {
        return BookingType;
    }

}
