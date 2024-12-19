package com.cairouniv.fci.travel.agency.EventManager;

import java.util.Date;

public class Event {
    private String eventId;
    private String name;
    private String location;
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
    public Event(String name, String location, Date date) {
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
        return false; // Tickets sold out
    }
    public String getEventDetails() {
        return String.format(
                "Event ID: %s%nName: %s%nLocation: %s%nDate: %s%nTicket Price: %.2f%nAvailable Tickets: %d",
                eventId,
                name,
                location,
                date.toString(),
                ticketPrice,
                availableTickets
        );
    }


}


