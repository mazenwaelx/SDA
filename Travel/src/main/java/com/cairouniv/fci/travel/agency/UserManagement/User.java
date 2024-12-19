package com.cairouniv.fci.travel.agency.UserManagement;

import com.cairouniv.fci.travel.agency.HotelManagement.Booking;
import com.cairouniv.fci.travel.agency.Notification.Notification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class User {
    private String userId;
    private String name;
    private String email;
    private String phoneNO;
    private List<Booking> bookings = new ArrayList<>();
    private static final List<User> userList = new ArrayList<>();
    private List<Notification> notifications = new ArrayList<>();
    private boolean interestedInEvents;
    private long lastBookingTime;  // Timestamp of last booking to simulate recent activity



    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
    public List<Notification> getNotifications() {
        return notifications;
    }
    public void addNotification(Notification notification) {
        notifications.add(notification);
    }
    public void clearNotifications() {
        notifications.clear();
    }
    public User(String userId, String name, String email, String phoneNO, boolean interestedInEvents, long lastBookingTime) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phoneNO = phoneNO;
        this.interestedInEvents = interestedInEvents;
        this.lastBookingTime = lastBookingTime;
    }

    public List<Booking> viewBookings() {
        return bookings;
    }
    public String getUserId() {
        return userId;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNO() {
        return phoneNO;
    }
    public void adduser(User user) {
        userId = user.userId;
        name = user.name;
        email = user.email;
        phoneNO = user.phoneNO;

    }
    public static List<User> getAllUsers() {
        return userList;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhoneNO(String phoneNO) {
        this.phoneNO = phoneNO;
    }
    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
    public String getUserDetails() {
        StringBuilder details = new StringBuilder();
        details.append("User ID: ").append(userId).append("\n");
        details.append("Name: ").append(name).append("\n");
        details.append("Email: ").append(email).append("\n");
        details.append("Phone Number: ").append(phoneNO).append("\n");
        details.append("Bookings: ");

        if (bookings.isEmpty()) {
            details.append("No bookings found");
        } else {
            for (Booking booking : bookings) {
                details.append("\n").append(booking.getBookingDetails());
            }
        }
        return details.toString();
    }
    public void markAllNotificationsAsRead() {
        for (Notification notification : notifications) {
            notification.markAsRead();
        }
    }
    public List<Notification> getUnreadNotifications() {
        return notifications.stream()
                .filter(notification -> !notification.isRead())
                .collect(Collectors.toList());
    }

    public boolean isInterestedInEvents() {
        return interestedInEvents;
    }

    public void setInterestedInEvents(boolean interestedInEvents) {
        this.interestedInEvents = interestedInEvents;
    }

    public boolean hasRecentBookings() {
        // Check if the last booking was within a certain timeframe, e.g., last 30 days
        long thirtyDaysMillis = 30L * 24 * 60 * 60 * 1000;
        return (System.currentTimeMillis() - lastBookingTime) < thirtyDaysMillis;
    }

    public void setLastBookingTime(long lastBookingTime) {
        this.lastBookingTime = lastBookingTime;
    }

    // Existing methods unchanged...
    public void addBooking(Booking booking) {
        bookings.add(booking);
        setLastBookingTime(System.currentTimeMillis()); // Update last booking time when a new booking is added
    }

    public List<Booking> getBookings() {
        return bookings;
    }



}
