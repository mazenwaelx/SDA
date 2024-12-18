package Notification_Module;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Notification_Module.Event;


class Concert extends Event {
    public Concert(String name, String location, Date date) {
        super(name, location, date);
    }

    @Override
    public String toString() {
        return "Concert{name='" + getName() + "', location='" + getLocation() + "', date=" + getDate() + "}";
    }
}

class Festival extends Event {
    public Festival(String name, String location, Date date) {
        super(name, location, date);
    }

    @Override
    public String toString() {
        return "Festival{name='" + getName() + "', location='" + getLocation() + "', date=" + getDate() + "}";
    }
}



interface EventAPI {
    List<Event> getEvents(String location); // Fetch all events for a location
}

class MockEventAPI implements EventAPI {
    @Override
    public List<Event> getEvents(String location) {
        List<Event> events = new ArrayList<>();
        events.add(new Concert("Concert A", location, new Date(2024, 12, 20)));
        events.add(new Festival("Festival B", location, new Date(2024, 12, 25)));
        events.add(new Concert("Exhibition C", location, new Date(2025, 1, 5)));
        return events;
    }
}

class RecommendationEngine {
    private EventAPI eventAPI;

    public RecommendationEngine(EventAPI eventAPI) {
        this.eventAPI = eventAPI;
    }

    public List<Event> recommendEvents(String location, Date startDate, Date endDate) {
        List<Event> allEvents = eventAPI.getEvents(location);
        List<Event> recommendedEvents = new ArrayList<>();

        // Filter events based on the provided date range
        for (Event event : allEvents) {
            if (!event.getDate().before(startDate) && !event.getDate().after(endDate)) {
                recommendedEvents.add(event);
            }
        }

        return recommendedEvents;
    }
}

