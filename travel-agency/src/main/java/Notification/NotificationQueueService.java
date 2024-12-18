package Notification;


import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class NotificationQueueService<NotificationMessage> {
    private ConcurrentLinkedQueue<NotificationMessage> queue = new ConcurrentLinkedQueue<>();

    public void enqueueNotification(NotificationMessage message) {
        queue.add(message);
    }

    public NotificationMessage dequeueNotification() {
        return queue.poll();
    }
}
