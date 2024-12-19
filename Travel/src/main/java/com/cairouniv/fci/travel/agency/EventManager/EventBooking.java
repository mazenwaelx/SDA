package com.cairouniv.fci.travel.agency.EventManager;

import com.cairouniv.fci.travel.agency.HotelManagement.Booking;
import com.cairouniv.fci.travel.agency.UserManagement.User;

class EventBooking extends Booking {
    private Event event;

    private int ticketCount;

    public EventBooking(String bookingId, String bookingDate, User user, Event event, int ticketCount) {
        super(bookingId, bookingDate, user);
        this.event = event;
        this.ticketCount = ticketCount;
    }
    public int getTicketCount() {
        return ticketCount;
    }
    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }
    public boolean validateBooking() {
        if (event.isAvailable() && event.getAvailableTickets() >= ticketCount) {
            for (int i = 0; i < ticketCount; i++) {
                event.bookTicket();
            }
            return true;
        }
        return false; // Not enough tickets available
    }
    public String getBookingDetails() {
        double totalPrice = event.getTicketPrice() * ticketCount;
        return String.format(
                "Event Booking Details:%nEvent Name: %s%nLocation: %s%nDate: %s%nTicket Count: %d%nTotal Price: %.2f",
                event.getName(),
                event.getLocation(),
                event.getDate(),
                ticketCount,
                totalPrice
        );
    }

    }
