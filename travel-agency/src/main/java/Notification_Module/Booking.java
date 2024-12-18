package Notification_Module;

import java.util.Date;

import org.apache.catalina.User;

public abstract class Booking {
    protected String bookingId;
    protected Date bookingDate;
    protected User user;

    public Booking(String bookingId, Date bookingDate, User user) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.user = user;
    }
    public String getFlightId() {
        return bookingId;
    }

    // Abstract method for child classes to implement their booking details
    public abstract String getBookingDetails();
}

