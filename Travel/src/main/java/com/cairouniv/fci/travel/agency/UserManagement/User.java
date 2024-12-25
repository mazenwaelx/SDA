package com.cairouniv.fci.travel.agency.UserManagement;

import com.cairouniv.fci.travel.agency.HotelManagement.Booking;
import com.cairouniv.fci.travel.agency.Notification.Notification;
import com.cairouniv.fci.travel.agency.EventManager.Event;


import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String name;
    private String email;
    private String phoneNO;
    private List<Booking> bookings = new ArrayList<>();
    private static final List<User> userList = new ArrayList<>();
    private List<Notification> notifications = new ArrayList<>();
    private List<Notification> unreadNotifications = new ArrayList<>();
    private boolean interestedInEvents;
    private long lastBookingTime;
    private final List<User> users = new ArrayList<>();
    private List<Event> userEvents = new ArrayList<>();



    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
    public List<Notification> getNotifications() {
        return notifications;
    }
    public void addNotification(Notification notification) {
        if (!notifications.contains(notification)) {
            notifications.add(notification);
        }
        if (!unreadNotifications.contains(notification) && !notification.isRead()) {
            unreadNotifications.add(notification);
        }
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

    public void addEvent(Event event) {
        this.userEvents.add(event);
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

    public void markAllNotificationsAsRead() {
        for (Notification notification : notifications) {
            notification.markAsRead();
        }
    }
    public boolean deleteUser(String userId) {
        return users.removeIf(user -> user.getUserId().equalsIgnoreCase(userId));
    }

    public List<Notification> getUnreadNotifications() {
        return unreadNotifications;
    }

    public boolean isInterestedInEvents() {
        return interestedInEvents;
    }

    public void setInterestedInEvents(boolean interestedInEvents) {
        this.interestedInEvents = interestedInEvents;
    }

    public boolean hasRecentBookings() {
        long thirtyDaysMillis = 30L * 24 * 60 * 60 * 1000;
        return (System.currentTimeMillis() - lastBookingTime) < thirtyDaysMillis;
    }

    public void setLastBookingTime(long lastBookingTime) {
        this.lastBookingTime = lastBookingTime;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
        setLastBookingTime(System.currentTimeMillis());
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void addUnreadNotification(Notification notification) {
        if (!unreadNotifications.contains(notification)) {
            unreadNotifications.add(notification);
        }}
    public void clearUnreadNotifications() {
        this.unreadNotifications.clear();
    }
    public List<Event> getUserEvents() {
        return userEvents;
    }

    public void setUserEvents(List<Event> userEvents) {
        this.userEvents = userEvents;
    }

}
