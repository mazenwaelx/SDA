package Notification_Module;
import java.util.List;
class Dashboard {
    private User user;

    public Dashboard(User user) {
        this.user = user;
    }

    public void displayBookings() {
        List<Booking> bookings = user.viewBookings();
        if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
        } else {
            for (Booking booking : bookings) {
                System.out.println(booking.getBookingDetails());
            }
        }
    }

    public void printBooking(Booking booking) {
        System.out.println("Printing booking details...");
        System.out.println(booking.getBookingDetails());
    }

    public void displayNotifications() {
        // Example placeholder, connect this with Notification module
        System.out.println("Displaying notifications for user: " + user.getName());
    }
}
