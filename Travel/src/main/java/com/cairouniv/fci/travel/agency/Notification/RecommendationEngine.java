package com.cairouniv.fci.travel.agency.Notification;

import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import com.cairouniv.fci.travel.agency.UserManagement.User;

public class RecommendationEngine {

    public static List<Notification> generateRecommendations(User user) {
        List<Notification> recommendations = new ArrayList<>();
        if (user.hasRecentBookings()) {
            Notification bookingReminder = createNotification(
                    user.getEmail(),
                    "Remember to check-in for your upcoming booking!",
                    Notification.NotificationType.EMAIL,
                    "2024-12-20T12:00:00",
                    Map.of("user", user.getName(), "booking", "upcoming trip")
            );
            bookingReminder.fillPlaceholders();
            recommendations.add(bookingReminder);
        }

        if (user.isInterestedInEvents()) {
            Notification eventPromo = createNotification(
                    user.getEmail(),
                    "Don't miss our upcoming events near you!",
                    Notification.NotificationType.PUSH_NOTIFICATION,
                    "2024-12-20T15:00:00",
                    Map.of("user", user.getName(), "event", "Local Music Concert")
            );
            eventPromo.fillPlaceholders();
            recommendations.add(eventPromo);
        }

        return recommendations;
    }


    private static Notification createNotification(String recipient, String message, Notification.NotificationType type, String timestamp, Map<String, String> placeholders) {
        return new Notification(recipient, message, type, timestamp, placeholders);
    }
}

