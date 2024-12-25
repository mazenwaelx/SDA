package com.cairouniv.fci.travel.agency.EventManager;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Date;

@Service
public class EventService {

    private static List<Event> events = new ArrayList<>();


    public static void addEvent(Event event) {
        Event existingEvent = findEventById(event.getEventId());
        if (existingEvent == null) {
            events.add(event);
        } else {
            existingEvent.setName(event.getName());
            existingEvent.setLocation(event.getLocation());
            existingEvent.setDate(event.getDate());
            existingEvent.setTicketPrice(event.getTicketPrice());
            existingEvent.setAvailableTickets(event.getAvailableTickets());
        }
    }
    public static Event findEventById(String eventId) {
        for (Event event : events) {
            if (event.getEventId().equals(eventId)) {
                return event;
            }
        }
        return null;
    }

    public static boolean deleteEvent(String eventId) {
        Iterator<Event> iterator = events.iterator();
        while (iterator.hasNext()) {
            Event event = iterator.next();
            if (event.getEventId().equals(eventId)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
    public static boolean updateEvent(String eventId, Event newEvent) {
        Event event = findEventById(eventId);
        if (event != null) {
            event.setName(newEvent.getName());
            event.setLocation(newEvent.getLocation());
            event.setDate(newEvent.getDate());
            event.setTicketPrice(newEvent.getTicketPrice());
            event.setAvailableTickets(newEvent.getAvailableTickets());
            return true;
        }
        return false;
    }
    public static List<Event> getAllEvents() {
        return new ArrayList<>(events);
    }
}