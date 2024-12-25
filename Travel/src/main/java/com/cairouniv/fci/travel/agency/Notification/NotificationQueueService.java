package com.cairouniv.fci.travel.agency.Notification;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class NotificationQueueService {

    private final ConcurrentLinkedQueue<Notification> queue = new ConcurrentLinkedQueue<>();

    public void enqueueNotification(Notification notification) {
        queue.add(notification);
        System.out.println("Notification added to the queue: " + notification.getNotificationDetails());
    }

    public Notification dequeueNotification() {
        return queue.poll();
    }

    public boolean isQueueEmpty() {
        return queue.isEmpty();
    }
}
