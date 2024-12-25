package com.cairouniv.fci.travel.agency.Notification;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class Notification {
    private String recipient;
    private String message;
    private NotificationType type;
    private String timestamp;
    private boolean isRead;
    private String notificationId;

    public static Map<String, String> getPlaceholders() {
        return placeholders;
    }

    public void setPlaceholders(Map<String, String> placeholders) {
        this.placeholders = placeholders;
    }

    private static Map<String, String> placeholders;


    public String getNotificationId() {
        return notificationId;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
    public  void setType(NotificationType type) {
        this.type = type;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public void setRead(boolean read) {
        isRead = read;
    }
    public Notification(String recipient, String message, NotificationType type, String timestamp) {
        this.recipient = recipient;
        this.message = message;
        this.type = type;
        this.timestamp = timestamp;
        this.isRead = false;
        this.placeholders = this.placeholders;
    }
    public String getRecipient() {
        return recipient;
    }
    public String getMessage() {
        return message;
    }
    public NotificationType getType() {
        return type;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public boolean isRead() {
        return isRead;
    }

    public void fillPlaceholders() {
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            message = message.replace("{" + entry.getKey() + "}", entry.getValue());
        }
    }

    public String getNotificationDetails() {
        return String.format(
                "Recipient: %s%nType: %s%nMessage: %s%nTimestamp: %s%nStatus: %s",
                recipient,
                type,
                message,
                timestamp,
                isRead ? "Read" : "Unread"
        );
    }
    public enum NotificationType {
        EMAIL,
        SMS,
        PUSH_NOTIFICATION
    }
    public String toString() {
        return "Notification{" +
                "recipient='" + recipient + '\'' +
                ", message='" + message + '\'' +
                ", type=" + type +
                ", timestamp='" + timestamp + '\'' +
                ", isRead=" + isRead +
                '}';
    }
    public void markAsRead() {
        this.isRead = true;
    }

}
