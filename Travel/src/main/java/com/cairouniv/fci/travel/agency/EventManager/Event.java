package com.cairouniv.fci.travel.agency.EventManager;


import com.cairouniv.fci.travel.agency.HotelManagement.Booking;
import com.cairouniv.fci.travel.agency.UserManagement.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Iterator;

public class Event {
    private String eventId;
    private String name;
    private String location;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date date;
    private double ticketPrice;
    private int availableTickets;


    public int getAvailableTickets() {
        return availableTickets;
    }
    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }
    public boolean isAvailable() {
        return availableTickets > 0;
    }
    public Event(String eventId, String name, String location, Date date, double ticketPrice) {
        this.eventId = eventId;
        this.name = name;
        this.location = location;
        this.date = date;
        this.ticketPrice = ticketPrice;
    }
    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getEventId() {
        return eventId;
    }
    public double getTicketPrice() {
        return ticketPrice;
    }

    public String getName() {
        return name;
    }
    public String getLocation() {
        return location;
    }
    public Date getDate() {
        return date;
    }
    public boolean bookTicket() {
        if (availableTickets > 0) {
            availableTickets--;
            return true;
        }
        return false;
    }

    public String getEventDetails() {
        return String.format(
                eventId,
                name,
                location,
                date.toString(),
                ticketPrice,
                availableTickets
        );
    }
    public void reduceTickets() {
        availableTickets--;
    }

    public Booking createEventBooking(User user, int ticketCount) {


        if (!isAvailable()) {
            throw new IllegalArgumentException("Event is not available for booking.");
        }

        if (ticketCount <= 0 || getAvailableTickets() < ticketCount) {
            throw new IllegalArgumentException("Not enough tickets available for this event.");
        }

        availableTickets -= ticketCount;

        Booking booking = new Booking("B" + System.currentTimeMillis(), getDate().toString(), user);
        booking.setBookingType(Booking.BookingType.EVENT);

        System.out.println("Booking created: " + booking.getBookingId());
        return booking;
    }


}


