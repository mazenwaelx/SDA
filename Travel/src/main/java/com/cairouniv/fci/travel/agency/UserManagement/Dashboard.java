package com.cairouniv.fci.travel.agency.UserManagement;

import com.cairouniv.fci.travel.agency.HotelManagement.Booking;
import com.cairouniv.fci.travel.agency.Notification.Notification;
import com.cairouniv.fci.travel.agency.Notification.NotificationStatsService;

import java.util.ArrayList;
import java.util.List;

public class Dashboard {
    private User user;
    private List<Notification> notifications;

    public Dashboard(User user) {
        this.user = user;
        this.notifications = new ArrayList<>();
    }

    public void displayBookings() {
        List<Booking> bookings = user.viewBookings();
        if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
        } else {
            System.out.println("User Bookings:");
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
        if (notifications.isEmpty()) {
            System.out.println("No notifications available.");
        } else {
            System.out.println("Notifications for user: " + user.getName());
            for (Notification notification : notifications) {
                System.out.println(notification.getNotificationDetails());
            }
        }
    }

    public void addNotification(Notification notification) {
        notifications.add(notification);
    }

    public void clearNotifications() {
        notifications.clear();
        System.out.println("All notifications have been cleared.");
    }

    public List<Notification> getNotifications() {
        return notifications;
    }
    public void displayNotificationStats() {
        System.out.println("Notification Statistics:");
        System.out.println(NotificationStatsService.getStats());
    }
    public void markNotificationsAsRead() {
        user.markAllNotificationsAsRead();
        System.out.println("All notifications have been marked as read.");
    }
}
