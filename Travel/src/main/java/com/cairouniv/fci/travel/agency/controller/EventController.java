package com.cairouniv.fci.travel.agency.controller;


import com.cairouniv.fci.travel.agency.EventManager.Event;
import com.cairouniv.fci.travel.agency.EventManager.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/create")
    public ResponseEntity<String> createEvent(@RequestBody Event event) {
        try {
            eventService.addEvent(event);
            return ResponseEntity.ok("Event created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create event: " + e.getMessage());
        }
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Event> getEvent(@PathVariable String eventId) {
        Event event = eventService.findEventById(eventId);
        if (event != null) {
            return ResponseEntity.ok(event);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{eventId}")
    public ResponseEntity<String> updateEvent(@PathVariable String eventId, @RequestBody Event event) {
        eventService.updateEvent(eventId, event);
        return ResponseEntity.ok("Event updated successfully.");
    }

    @DeleteMapping("/delete/{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable String eventId) {
        boolean isDeleted = eventService.deleteEvent(eventId);
        if (isDeleted) {
            return ResponseEntity.ok("Event deleted successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to delete event.");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }
}

