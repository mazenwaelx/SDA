package com.cairouniv.fci.travel.agency.controller;


import com.cairouniv.fci.travel.agency.HotelManagement.Hotel;
import com.cairouniv.fci.travel.agency.HotelManagement.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/add")
    public ResponseEntity<String> addHotel(@RequestBody Hotel hotel) {
        hotelService.addHotel(hotel);
        return ResponseEntity.ok("Hotel added successfully.");
    }

    @GetMapping("/{hotel_name}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotel_name) {
        Hotel hotel = hotelService.findHotelByName(hotel_name);
        if (hotel != null) {
            return ResponseEntity.ok(hotel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{hotel_name}")
    public ResponseEntity<String> updateHotel(@PathVariable String hotel_name, @RequestBody Hotel hotel) {
        hotelService.updateHotel(hotel_name, hotel);
        return ResponseEntity.ok("Hotel updated successfully.");
    }

    @DeleteMapping("/delete/{hotel_name}")
    public ResponseEntity<String> deleteHotel(@PathVariable String hotel_name) {
        boolean isDeleted = hotelService.deleteHotel(hotel_name);
        if (isDeleted) {
            return ResponseEntity.ok("Hotel deleted successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to delete hotel.");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Hotel>> getAllHotels() {
        List<Hotel> hotels = hotelService.getAllHotels();
        return ResponseEntity.ok(hotels);
    }
}

