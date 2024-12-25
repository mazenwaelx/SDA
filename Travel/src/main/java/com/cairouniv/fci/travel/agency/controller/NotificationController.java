package com.cairouniv.fci.travel.agency.controller;


import com.cairouniv.fci.travel.agency.Notification.Notification;
import com.cairouniv.fci.travel.agency.Notification.NotificationService;
import com.cairouniv.fci.travel.agency.UserManagement.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.cairouniv.fci.travel.agency.UserManagement.User;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    private final UserService userService;

    public NotificationController(UserService userService) {
        this.userService = userService;
        this.notificationService = notificationService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody Notification notification) {
        notificationService.addNotification(
                notification.getRecipient(),
                notification.getMessage(),
                notification.getType(),
                notification.getTimestamp()
        );

        return ResponseEntity.ok("Notification sent successfully.");
    }

    @PostMapping("/send-bulk")
    public ResponseEntity<String> sendBulkNotifications(@RequestBody List<Notification> notifications) {
        try {
            notificationService.sendBulkNotifications(notifications);
            return ResponseEntity.ok("Bulk notifications sent successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send bulk notifications: " + e.getMessage());
        }
    }


    @GetMapping("/by-recipient/{recipient}")
    public ResponseEntity<List<Notification>> getNotificationsByRecipient(@PathVariable String recipient) {
        try {
            List<Notification> recipientNotifications = notificationService.getNotificationsByRecipient(recipient);
            if (recipientNotifications.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(recipientNotifications);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }


    @PostMapping("/mark-read/{recipient}")
    public ResponseEntity<String> markNotificationsAsRead(@PathVariable String recipient) {
        boolean updated = notificationService.markNotificationsAsRead(recipient);

        if (updated) {
            return ResponseEntity.ok("Notifications marked as read for: " + recipient);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No unread notifications found for recipient: " + recipient);
        }
    }

    @DeleteMapping("/clear/{recipient}")
    public ResponseEntity<String> clearNotifications(@PathVariable String recipient) {
        try {
            notificationService.clearNotifications(recipient);
            return ResponseEntity.ok("Notifications cleared for " + recipient);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to clear notifications: " + e.getMessage());
        }
    }

}

