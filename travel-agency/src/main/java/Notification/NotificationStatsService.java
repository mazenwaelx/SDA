package Notification;



import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationStatsService {
    private Map<String, Integer> stats = new HashMap<>();

    public void incrementSuccessCount(String type) {
        stats.merge(type + "_success", 1, Integer::sum);
    }

    public void incrementFailureCount(String type) {
        stats.merge(type + "_failure", 1, Integer::sum);
    }

    public Map<String, Integer> getStats() {
        return stats;
    }
}
