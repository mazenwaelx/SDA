package Notification_Module;

import java.util.LinkedList;
import java.util.Queue;

class QueueManagement {
    private Queue<Notification> notificationQueue;

    public QueueManagement() {
        notificationQueue = new LinkedList<>();
    }

    // Add a notification to the queue
    public void enqueue(Notification notification) {
        notificationQueue.add(notification);
        System.out.println("Notification added to the queue: " + notification);
    }

    // Remove and return the next notification in the queue
    public Notification dequeue() {
        if (notificationQueue.isEmpty()) {
            System.out.println("Queue is empty, no notification to dequeue.");
            return null;
        }
        Notification notification = notificationQueue.poll();
        System.out.println("Notification removed from the queue: " + notification);
        return notification;
    }
}

