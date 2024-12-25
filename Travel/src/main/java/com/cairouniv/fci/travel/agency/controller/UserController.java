package com.cairouniv.fci.travel.agency.controller;


import com.cairouniv.fci.travel.agency.UserManagement.User;
import com.cairouniv.fci.travel.agency.UserManagement.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cairouniv.fci.travel.agency.EventManager.Event;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok("User created successfully.");
    }
    @PostMapping("/{userId}/events")
    public ResponseEntity<String> addEventToUser(@PathVariable String userId, @RequestBody Event event) {
        User user = userService.getUserById(userId);
        if (user != null) {
            user.addEvent(event);
            return ResponseEntity.ok("Event added to user successfully.");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        User user = userService.getUserById(userId);
        System.out.println("Looking up user with ID: " + userId);
        System.out.println("Found user: " + user);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable String userId, @RequestBody User user) {
        userService.updateUser(userId, user);
        return ResponseEntity.ok("User updated successfully.");
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        boolean isDeleted = userService.deleteUser(userId);
        if (isDeleted) {
            return ResponseEntity.ok("User deleted successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to delete user: User not found.");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("/{userId}/events")
    public ResponseEntity<List<Event>> getUserEvents(@PathVariable String userId) {
        List<Event> events = userService.getEventsByUserId(userId);
        if (events.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(events);
    }

}

