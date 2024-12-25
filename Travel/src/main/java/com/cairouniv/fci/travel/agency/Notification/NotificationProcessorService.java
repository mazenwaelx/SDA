package com.cairouniv.fci.travel.agency.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class NotificationProcessorService {

    @Autowired
    private NotificationQueueService queueService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationStatsService statsService;

    @Scheduled(fixedDelay = 10000)
    public void processQueue() {
        Notification notification;
        while ((notification = (Notification) queueService.dequeueNotification()) != null) {
            try {
                notificationService.sendNotification(notification);

                statsService.incrementSuccessCount(notification.getType());
                System.out.println("Notification successfully sent: " + notification.getNotificationDetails());
            } catch (Exception e) {
                statsService.incrementFailureCount(notification.getType());
                System.err.println("Failed to send notification: " + notification.getNotificationDetails());
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}
