package Notification_Module;
import Notification_Module.Booking;
import java.util.ArrayList;
import java.util.List;

class User {
    private final String userId;
    private final String name;
    private final List<Booking> bookings = new ArrayList<>();

    public User(String userId, String name, String email, String phoneNumber) {
        this.userId = userId;
        this.name = name;
    }

    // Add a booking to the user's list
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    // Retrieve all bookings
    public List<Booking> viewBookings() {
        return bookings;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    // Function to retrieve user details
    public String getUserDetails() {
        StringBuilder details = new StringBuilder();
        details.append("User ID: ").append(userId).append("\n");
        details.append("Name: ").append(name).append("\n");
        details.append("Flight IDs: ");

        if (bookings.isEmpty()) {
            details.append("No bookings found");
        } else {
            for (Booking booking : bookings) {
                details.append(booking.getFlightId()).append(" ");
            }
        }
        return details.toString();
    }
}
