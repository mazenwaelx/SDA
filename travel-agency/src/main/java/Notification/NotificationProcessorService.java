package Notification;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class NotificationProcessorService<NotificationMessage> {
    @Autowired
    private NotificationQueueService queueService;

    @Scheduled(fixedDelay = 10000)  // Process every 10 seconds
    public void processQueue() {
        NotificationMessage message;
        while ((message = (NotificationMessage) queueService.dequeueNotification()) != null) {
            // Send notification logic
            // Log success or failure
        }
    }
}
