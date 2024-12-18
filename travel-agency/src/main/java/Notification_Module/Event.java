package Notification_Module;

import java.util.Date;

public class Event {
    private String eventId;
    private String name;
    private String location;
    private Date date;

    // Constructor
    public Event(String eventId, String name, String location, Date date) {
        this.eventId = eventId;
        this.name = name;
        this.location = location;
        this.date = date;
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
}

class EventBooking extends Booking {
    private Event event;

    public EventBooking(String bookingId, Date bookingDate, User user, Event event) {
        super(bookingId, bookingDate, (org.apache.catalina.User) user);
        this.event = event;
    }

    @Override
    public String getBookingDetails() {
        return "Notification_Module.Event Notification_Module.Booking - Notification_Module.Event Name: " + event.getName() +
                ", Notification_Module.Event Location: " + event.getLocation() +
                ", Notification_Module.Event Date: " + event.getDate();
    }
}
