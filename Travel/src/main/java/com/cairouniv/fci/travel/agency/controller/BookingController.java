package com.cairouniv.fci.travel.agency.controller;

import com.cairouniv.fci.travel.agency.HotelManagement.Booking;
import com.cairouniv.fci.travel.agency.HotelManagement.BookingService;
import com.cairouniv.fci.travel.agency.UserManagement.User;
import com.cairouniv.fci.travel.agency.UserManagement.UserService;
import com.cairouniv.fci.travel.agency.HotelManagement.Hotel;
import com.cairouniv.fci.travel.agency.HotelManagement.Room;
import com.cairouniv.fci.travel.agency.EventManager.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private UserService userService;

    @PostMapping("/create-hotel-booking")
    public ResponseEntity<String> createHotelBooking(@RequestBody HotelBookingRequest request) {
        try {
            Hotel hotel = request.getHotel();
            Room room = request.getRoom();
            User user = userService.getUserById(request.getUser().getUserId());
            String startDate = request.getStartDate();
            String endDate = request.getEndDate();
            if (hotel == null || room == null || user == null || startDate == null || endDate == null) {
                throw new IllegalArgumentException("Invalid request: Missing required fields.");
            }

            Booking booking = hotel.createHotelBooking(user, room, startDate, endDate);
            user.addBooking(booking);
            booking.setRoom(room);
            return ResponseEntity.ok("Hotel booking created successfully with ID: " + booking.getBookingId());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create hotel booking: " + e.getMessage());
        }
    }



    @PostMapping("/create-event-booking")
    public ResponseEntity<String> createEventBooking(@RequestBody EventBookingRequest request) {
        try {
            Event event = request.getEvent();
            int ticketCount = request.getTicketCount();

            User user = userService.getUserById(request.getUser().getUserId());
            if (event == null || user == null || ticketCount <= 0) {
                throw new IllegalArgumentException("Invalid request: Missing required fields or invalid ticket count.");
            }


            Booking booking = event.createEventBooking(user, ticketCount);

            user.addBooking(booking);

            user.addEvent(event);

            return ResponseEntity.ok("Event booking created successfully with ID: " + booking.getBookingId());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to create event booking: " + e.getMessage());
        }
    }




    public static class HotelBookingRequest {
        private Hotel hotel;

        public Room getRoom() {
            return room;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public void setRoom(Room room) {
            this.room = room;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public Hotel getHotel() {
            return hotel;
        }

        public void setHotel(Hotel hotel) {
            this.hotel = hotel;
        }

        private Room room;
        private User user;
        private String startDate;
        private String endDate;


    }
    public static class EventBookingRequest {
        private Event event;
        private User user;
        private int ticketCount;

        public Event getEvent() {
            return event;
        }

        public void setEvent(Event event) {
            this.event = event;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public int getTicketCount() {
            return ticketCount;
        }

        public void setTicketCount(int ticketCount) {
            this.ticketCount = ticketCount;
        }
    }


}
