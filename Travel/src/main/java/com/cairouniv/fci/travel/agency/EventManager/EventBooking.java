package com.cairouniv.fci.travel.agency.EventManager;

import com.cairouniv.fci.travel.agency.HotelManagement.Booking;
import com.cairouniv.fci.travel.agency.UserManagement.User;

public class EventBooking extends Booking {
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
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
    public boolean validateBooking() {
        if (event.isAvailable() && event.getAvailableTickets() >= ticketCount) {
            for (int i = 0; i < ticketCount; i++) {
                event.bookTicket();
            }
            return true;
        }
        return false;
    }
    @Override
    public String getBookingDetails() {
        double totalPrice = event.getTicketPrice() * ticketCount;
        return String.format(
                "Event Booking Details:%nEvent Name: %s%nLocation: %s%nDate: %s%nTicket Count: %d%nTotal Price: %.2f",
                getBookingId(),
                event.getName(),
                event.getLocation(),
                event.getDate(),
                ticketCount,
                totalPrice
        );
    }
    public void notifyUser() {
        String message = String.format(
                "Dear %s, your booking for the event '%s' at %s on %s has been confirmed.%n" +
                        "Ticket Count: %d, Total Price: %.2f.",
                getUser().getName(),
                event.getName(),
                event.getLocation(),
                event.getDate(),
                ticketCount,
                event.getTicketPrice() * ticketCount
        );
        System.out.println("Notification sent to " + getUser().getEmail() + ": " + message);
    }
    }
