package com.cairouniv.fci.travel.agency.Notification;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationStatsService {
    private static final Map<String, Integer> stats = new HashMap<>();
    public static void incrementSuccessCount(Notification.NotificationType type) {
        stats.merge(type.name() + "_success", 1, Integer::sum);
    }
    public static void incrementFailureCount(Notification.NotificationType type) {
        stats.merge(type.name() + "_failure", 1, Integer::sum);
    }
    public static Map<String, Integer> getStats() {
        return new HashMap<>(stats);
    }
    public Map<String, Integer> getStatsByType(Notification.NotificationType type) {
        Map<String, Integer> typeStats = new HashMap<>();
        typeStats.put(type.name() + "_success", stats.getOrDefault(type.name() + "_success", 0));
        typeStats.put(type.name() + "_failure", stats.getOrDefault(type.name() + "_failure", 0));
        return typeStats;
    }
    public void resetStats() {
        stats.clear();
    }
    public void resetStatsForType(Notification.NotificationType type) {
        stats.remove(type.name() + "_success");
        stats.remove(type.name() + "_failure");
    }
}
