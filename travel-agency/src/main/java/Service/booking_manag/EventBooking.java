package Service.booking_manag;

import Model.Booking;
import Model.Event;
import Model.User;

import java.util.Date;

class EventBooking extends Booking {
    private Event event;

    public EventBooking(String bookingId, String bookingDate, User user, Event event) {
        super(bookingId, bookingDate, (org.apache.catalina.User) user);
        this.event = event;
    }

    @Override
    public String getBookingDetails() {
        return "Model.Event Model.Booking - Model.Event Name: " + event.getName() +
                ", Model.Event Location: " + event.getLocation() +
                ", Model.Event Date: " + event.getDate();
    }
}
