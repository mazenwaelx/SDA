package Model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Notification {
    // Getter and Setter methods
    @Getter
    private String recipient;
    private String message;
    private NotificationType type;

    public Notification(String recipient, String message, NotificationType type) {
        this.recipient = recipient;
        this.message = message;
        this.type = type;
    }

    public enum NotificationType {
        EMAIL,
        SMS,
        PUSH_NOTIFICATION
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

