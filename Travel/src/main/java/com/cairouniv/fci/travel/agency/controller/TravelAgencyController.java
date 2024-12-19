package com.cairouniv.fci.travel.agency.controller;

import com.cairouniv.fci.travel.agency.HotelManagement.Booking;
import com.cairouniv.fci.travel.agency.UserManagement.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TravelAgencyController {

    private  User user = new User("202255","Omar","zzz@gmail.com","010255555",true,4L); // Manually instantiate
    private final Booking b = new Booking("A901","10/10/12",user);

    @GetMapping("/usr")
    public ResponseEntity<String> bookingDetails() {
        return new ResponseEntity<>(user.getUserId(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
         user.adduser(user);
        return ResponseEntity.ok("User registered successfully.");
    }

}



