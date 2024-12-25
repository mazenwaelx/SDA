package com.cairouniv.fci.travel.agency.HotelManagement;

import com.cairouniv.fci.travel.agency.EventManager.Event;
import com.cairouniv.fci.travel.agency.Notification.Notification;
import com.cairouniv.fci.travel.agency.UserManagement.User;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class HotelService {
    private static List<Hotel> hotels = List.of();
    public HotelService() {
        this.hotels = new ArrayList<>();
    }
    public void addHotel(Hotel hotel) {
        this.hotels.add(hotel);
    }
    public static List<Hotel> getAllHotels() {
        return hotels;
    }
    public Hotel findHotelByName(String name) {
        for (Hotel hotel : hotels) {
            if (hotel.getHotelName().equalsIgnoreCase(name)) {
                return hotel;
            }
        }
        return null;
    }
    public List<Room> getAvailableRooms(Hotel hotel) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : hotel.getRooms()) {
            if (room.Checkavailbility()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }
    public Booking bookRoom(String hotelName, int roomNumber, User user, String startDate, String endDate) {
        Hotel hotel = findHotelByName(hotelName);
        if (hotel != null) {
            for (Room room : hotel.getRooms()) {
                if (room.getRoomNumber() == roomNumber && room.Checkavailbility()) {
                    room.setAvailable(false);
                    Booking booking = new Booking("B" + System.currentTimeMillis(), startDate, user);
                    hotel.addBooking(booking);
                    user.addBooking(booking);


                    Notification notification = new Notification(
                            user.getEmail(),
                            "Your hotel booking for " + hotel.getHotelName() + " has been confirmed!",
                            Notification.NotificationType.EMAIL,
                            startDate
                    );
                    user.addNotification(notification);

                    return booking;
                }
            }
        }
        throw new IllegalArgumentException("Room not available or hotel not found.");
    }
    public void addEventToHotel(Hotel hotel, Event event) {
        hotel.addEvent(event);
    }
    public List<Booking> getAllBookings(String hotelName) {
        Hotel hotel = findHotelByName(hotelName);
        return hotel != null ? hotel.getBookings() : new ArrayList<>();
    }
    public boolean cancelBooking(String hotelName, String bookingId) {
        Hotel hotel = findHotelByName(hotelName);
        if (hotel != null) {
            for (Booking booking : hotel.getBookings()) {
                if (booking.getBookingId().equals(bookingId)) {
                    Room room = booking.getRoom();
                    if (room != null) {
                        room.setAvailable(true);
                    }
                    hotel.getBookings().remove(booking);
                    return true;
                }
            }
        }
        return false;
    }
    public List<Event> recommendEventsForBooking(String hotelName, String startDate, String endDate) {
        Hotel hotel = findHotelByName(hotelName);
        if (hotel == null) {
            return null;
        }

        List<Event> recommendedEvents = new ArrayList<>();
        for (Event event : hotel.getNearbyEvents()) {
            if (isDateWithinRange(event.getDate(), startDate, endDate)) {
                recommendedEvents.add(event);
            }
        }
        return recommendedEvents;
    }
    private boolean isDateWithinRange(Date eventDate, String startDate, String endDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date start = sdf.parse(startDate);
            Date end = sdf.parse(endDate);
            return eventDate.after(start) && eventDate.before(end);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void recommendAndNotifyEvents(String hotelName, String startDate, String endDate, User user) {
        List<Event> recommendedEvents = recommendEventsForBooking(hotelName, startDate, endDate);
        if (!recommendedEvents.isEmpty()) {
            for (Event event : recommendedEvents) {
                Notification notification = new Notification(
                        user.getEmail(),
                        "Recommended event: " + event.getName() + " at " + event.getLocation() +
                                " on " + event.getDate().toString(),
                        Notification.NotificationType.PUSH_NOTIFICATION,
                        startDate
                );
                user.addNotification(notification);
            }
        } else {
            Notification notification = new Notification(
                    user.getEmail(),
                    "No events available for your booking at " + hotelName + ".",
                    Notification.NotificationType.PUSH_NOTIFICATION,
                    startDate
            );
            user.addNotification(notification);
        }
    }
    public boolean updateHotel(String oldHotelName, Hotel updatedHotel) {
        int index = -1;
        for (int i = 0; i < hotels.size(); i++) {
            if (hotels.get(i).getHotelName().equalsIgnoreCase(oldHotelName)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            hotels.set(index, updatedHotel);
            return true;
        }
        return false;
    }
    public boolean deleteHotel(String hotelName) {
        Iterator<Hotel> iterator = hotels.iterator();
        while (iterator.hasNext()) {
            Hotel hotel = iterator.next();
            if (hotel.getHotelName().equalsIgnoreCase(hotelName)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }






}
