package com.cairouniv.fci.travel.agency.Notification;

import com.cairouniv.fci.travel.agency.UserManagement.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class NotificationService {
    private final List<Notification> notifications;
    public NotificationService() {
        this.notifications = new ArrayList<>();
    }
    public void addNotification(String recipient, String message, Notification.NotificationType type, String timestamp, Map<String, String> placeholders) {
        Notification notification = new Notification(recipient, message, type, timestamp, placeholders);
        notifications.add(notification);
        System.out.println("Notification added: " + notification.getNotificationDetails());
    }
    public void sendBulkNotifications(List<Notification> bulkNotifications) {
        System.out.println("Sending bulk notifications...");
        for (Notification notification : bulkNotifications) {
            sendNotification(notification);
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
        List<Notification> userNotifications = getNotificationsByRecipient(recipient);
        for (Notification notification : userNotifications) {
            notification.markAsRead();
        }
        System.out.println("All notifications for " + recipient + " have been marked as read.");
    }
    public void clearNotifications(String recipient) {
        notifications.removeIf(notification -> notification.getRecipient().equalsIgnoreCase(recipient));
        System.out.println("All notifications for " + recipient + " have been cleared.");
    }
    public void sendNotification(Notification notification) {
        try {
            switch (notification.getType()) {
                case EMAIL:
                    sendEmailNotification(notification);
                    break;
                case SMS:
                    sendSmsNotification(notification);
                    break;
                case PUSH_NOTIFICATION:
                    sendPushNotification(notification);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported notification type: " + notification.getType());
            }
        } catch (Exception e) {
            System.err.println("Failed to send notification: " + e.getMessage());
            throw e;
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
}
