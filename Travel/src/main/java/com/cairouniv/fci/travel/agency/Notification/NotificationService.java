package com.cairouniv.fci.travel.agency.Notification;

import com.cairouniv.fci.travel.agency.UserManagement.User;
import com.cairouniv.fci.travel.agency.UserManagement.UserService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    private final List<User> users;
    private final List<Notification> notifications;
    private final UserService userService;

    public NotificationService(UserService userService) {
        this.userService = userService;
        this.notifications = new ArrayList<>();
        this.users = new ArrayList<>();
    }


    public void addNotification(String recipient, String message, Notification.NotificationType type, String timestamp) {
        Notification notification = new Notification(recipient, message, type, timestamp);
        notifications.add(notification);


        User user = userService.getUserByEmail(recipient);
        if (user != null) {
            user.getNotifications().add(notification);
            if (!notification.isRead()) {
                user.getUnreadNotifications().add(notification);
            }
        } else {
            System.out.println("User not found for recipient: " + recipient);
        }
    }

    public void sendBulkNotifications(List<Notification> notifications) {
        for (Notification notification : notifications) {
            addNotification(
                    notification.getRecipient(),
                    notification.getMessage(),
                    notification.getType(),
                    notification.getTimestamp()
            );
        }
    }
    public List<Notification> getNotificationsByRecipient(String recipient) {
        List<Notification> userNotifications = new ArrayList<>();
        for (Notification notification : notifications) {
            if (notification.getRecipient().equalsIgnoreCase(recipient)) {
                userNotifications.add(notification);
            }
        }
        return userNotifications;
    }
    public List<Notification> getAllNotifications() {
        return notifications;
    }
    public void markAllAsRead(String recipient) {
        Optional<User> userOpt = users.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(recipient))
                .findFirst();

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.markAllNotificationsAsRead();
            System.out.println("All notifications for " + recipient + " marked as read.");
        } else {
            System.out.println("User not found for recipient: " + recipient);
        }
    }
    public void clearNotifications(String recipient) {

        // Remove notifications for the recipient
        notifications.removeIf(notification -> notification.getRecipient().equalsIgnoreCase(recipient));

        // Update the user's notification lists
        User user = userService.getUserByEmail(recipient);
        if (user != null) {
            user.getNotifications().clear();
            user.getUnreadNotifications().clear();
        }
    }
    public void sendNotification(Notification notification) {
        Optional<User> userOpt = users.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(notification.getRecipient()))
                .findFirst();

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.addNotification(notification);
            System.out.println("Notification sent and added to user: " + user.getName());
        } else {
            System.out.println("User not found for notification: " + notification.getRecipient());
        }
    }
    private void sendEmailNotification(Notification notification) {
        System.out.println("Sending EMAIL notification to: " + notification.getRecipient());
        System.out.println("Subject: Booking Confirmation");
        System.out.println("Message: " + notification.getMessage());
        System.out.println("Timestamp: " + notification.getTimestamp());
    }
    private void sendSmsNotification(Notification notification) {
        System.out.println("Sending SMS notification to: " + notification.getRecipient());
        System.out.println("Message: " + notification.getMessage());
        System.out.println("Timestamp: " + notification.getTimestamp());
    }
    private void sendPushNotification(Notification notification) {
        System.out.println("Sending PUSH_NOTIFICATION to: " + notification.getRecipient());
        System.out.println("Message: " + notification.getMessage());
        System.out.println("Timestamp: " + notification.getTimestamp());
    }
    public void generateAndSendRecommendations(User userContext) {
        List<Notification> recommendations = RecommendationEngine.generateRecommendations(userContext);
        for (Notification notification : recommendations) {
            sendNotification(notification);
        }
    }
    public List<User> getAllUsers() {
        return users;
    }
    public boolean markNotificationsAsRead(String recipient) {
        User user = userService.findUserByEmail(recipient);
        if (user == null) {
            System.out.println("User not found for recipient: " + recipient);
            return false;
        }

        List<Notification> unreadNotifications = user.getUnreadNotifications();
        if (unreadNotifications.isEmpty()) {
            System.out.println("No unread notifications for recipient: " + recipient);
            return false;
        }

        for (Notification notification : new ArrayList<>(unreadNotifications)) {
            notification.markAsRead();
            user.addNotification(notification);
        }
        user.clearUnreadNotifications();

        System.out.println("Marked all unread notifications as read for recipient: " + recipient);
        return true;
    }
    public void addUnreadNotification(String recipient, String message, Notification.NotificationType type, String timestamp) {
        Notification notification = new Notification(recipient, message, type, timestamp);

        User user = userService.findUserByEmail(recipient);
        if (user != null) {
            user.addUnreadNotification(notification);
            System.out.println("Unread notification added to user: " + user.getName());
        } else {
            System.out.println("User not found for recipient: " + recipient);
        }
    }
}


