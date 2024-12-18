package Notification_Module;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
class Notification {
    // Getter and Setter methods
    private String recipient;
    private String message;
    private NotificationType type;

    public Notification(String recipient, String message, NotificationType type) {
        this.recipient = recipient;
        this.message = message;
        this.type = type;
    }

    public String getRecipient() {
        return this.recipient;
    }

    public enum NotificationType {
        EMAIL,
        SMS,
        PUSH_NOTIFICATION;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "recipient='" + recipient + '\'' +
                ", message='" + message + '\'' +
                ", type=" + type +
                '}';
    }
}

