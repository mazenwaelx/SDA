package com.cairouniv.fci.travel.agency.HotelManagement;

import com.cairouniv.fci.travel.agency.EventManager.Event;
import com.cairouniv.fci.travel.agency.Notification.Notification;
import com.cairouniv.fci.travel.agency.Notification.NotificationService;
import com.cairouniv.fci.travel.agency.UserManagement.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final List<Booking> bookings = new ArrayList<>();

    @Autowired
    private NotificationService notificationService;

    public Booking createHotelBooking(User user, Hotel hotel, Room room, String startDate, String endDate) {
        if (!room.Checkavailbility()) {
            throw new IllegalArgumentException("Room is not available for booking.");
        }
        Event event = null;
        int ticketCount = 0;
        Booking booking = new Booking("B" + System.currentTimeMillis(), startDate, user);
        booking.setBookingDate(String.valueOf(Booking.BookingType.HOTEL));
        bookings.add(booking);
        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("eventName", event.getName());
        placeholders.put("eventLocation", event.getLocation());
        placeholders.put("ticketCount", String.valueOf(ticketCount));
        user.addBooking(booking);
        Notification notification = new Notification(
                user.getEmail(),
                "Your hotel booking at " + hotel.getHotelName() + " has been confirmed for " +
                        startDate + " to " + endDate,
                Notification.NotificationType.EMAIL,
                startDate
        );
        notificationService.sendNotification(notification);

        return booking;
    }

    public Booking createEventBooking(User user, Event event, int ticketCount) {
        if (!event.isAvailable() || event.getAvailableTickets() < ticketCount) {
            throw new IllegalArgumentException("Not enough tickets available for this event.");
        }
        event.reduceTickets();
        Booking booking = new Booking("B" + System.currentTimeMillis(), event.getDate().toString(), user);
        booking.setBookingType(Booking.BookingType.EVENT);
        bookings.add(booking);
        user.addBooking(booking);
        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("eventName", event.getName());
        placeholders.put("eventLocation", event.getLocation());
        placeholders.put("ticketCount", String.valueOf(ticketCount));
        Notification notification = new Notification(
                user.getEmail(),
                "Your booking for the event '" + event.getName() + "' at " + event.getLocation() +
                        " has been confirmed. Tickets booked: " + ticketCount,
                Notification.NotificationType.PUSH_NOTIFICATION,
                event.getDate().toString()
        );
        notificationService.sendNotification(notification);

        return booking;
    }
    public List<Booking> getAllBookings() {
        return bookings;
    }
    public List<Booking> getBookingsByUserId(String userId) {
        if (bookings == null || bookings.isEmpty()) {
            return new ArrayList<>();
        }
        return bookings.stream()
                .filter(booking -> booking.getUser().getUserId().equalsIgnoreCase(userId))
                .collect(Collectors.toList());
    }

    public boolean cancelBooking(Booking booking) {
        boolean isCancelled = false;

        if (booking.getBookingType() == Booking.BookingType.HOTEL) {
            Room room = findRoomByBooking(booking);
            if (room != null) {
                room.setAvailable(true);
                isCancelled = true;
            }
        }

        if (booking.getBookingType() == Booking.BookingType.EVENT) {
            Event event = findEventByBooking(booking);
            if (event != null) {
                event.setAvailableTickets(event.getAvailableTickets() + 1);
                isCancelled = true;
            }
        }
        if (isCancelled) {
            Event event = findEventByBooking(booking);
            Map<String, String> placeholders = new HashMap<>();
            placeholders.put("eventName", event != null ? event.getName() : "");
            placeholders.put("eventLocation", event != null ? event.getLocation() : "");
            int ticketCount = event.getAvailableTickets();
            placeholders.put("ticketCount", String.valueOf(ticketCount));

            Notification notification = new Notification(
                    booking.getUser().getEmail(),
                    "Your booking with ID " + booking.getBookingId() + " has been canceled.",
                    Notification.NotificationType.EMAIL,
                    booking.getBookingDate()
            );
            notificationService.sendNotification(notification);
            bookings.remove(booking);
        }

        return isCancelled;
    }


    private Event findEventByBooking(Booking booking) {
        return null;
    }

    private Room findRoomByBooking(Booking booking) {
        if (booking.getBookingType() != Booking.BookingType.HOTEL) {
            return null;
        }
        for (Hotel hotel : HotelService.getAllHotels()) {
            for (Room room : hotel.getRooms()) {
                if (room.getRoomNumber() == Integer.parseInt(booking.getBookingId().substring(1))) {
                    return room;
                }
            }
        }
        return null;
    }


}
