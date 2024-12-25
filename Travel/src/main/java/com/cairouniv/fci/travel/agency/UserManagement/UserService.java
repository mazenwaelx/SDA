package com.cairouniv.fci.travel.agency.UserManagement;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.cairouniv.fci.travel.agency.EventManager.Event;


@Service
public class UserService {

    private static final List<User> userList = new ArrayList<>();
    private final List<User> users = new ArrayList<>();
    public boolean loginUser(String username, String password) {
        System.out.println("Authenticating user: " + username);
        return "user".equals(username) && "password".equals(password);
    }

    public void updateUser(String userId, User updatedUser) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                user.setName(updatedUser.getName());
                user.setEmail(updatedUser.getEmail());
                user.setPhoneNO(updatedUser.getPhoneNO());
                user.setInterestedInEvents(updatedUser.isInterestedInEvents());

                // Preserve existing userEvents if not explicitly provided
                if (updatedUser.getUserEvents() == null || updatedUser.getUserEvents().isEmpty()) {
                    updatedUser.setUserEvents(user.getUserEvents());
                }

                // Preserve other lists (e.g., notifications) if necessary
                if (updatedUser.getNotifications() == null || updatedUser.getNotifications().isEmpty()) {
                    updatedUser.setNotifications(user.getNotifications());
                }

                user.setUserEvents(updatedUser.getUserEvents());
                user.setNotifications(updatedUser.getNotifications());

                System.out.println("User updated: " + user);
                return;
            }
        }
        throw new IllegalArgumentException("User not found with ID: " + userId);
    }


    public User addUser(User user) {
        users.add(user);
        userList.add(user);
        return user;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userList);
    }


    public boolean deleteUser(String userId) {



        Iterator<User> iterator = users.iterator();
        boolean removedFromUsers = false;
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getUserId().equalsIgnoreCase(userId)) {
                iterator.remove();
                removedFromUsers = true;
                break;
            }
        }


        boolean removedFromUserList = userList.removeIf(user -> user.getUserId().equalsIgnoreCase(userId));

        if (removedFromUsers || removedFromUserList) {

            return true;
        }


        return false;
    }



    public User getUserById(String userId) {
        return userList.stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
    }
    public List<Event> getEventsByUserId(String userId) {
        User user = getUserById(userId);
        if (user != null && user.isInterestedInEvents()) {
            return user.getUserEvents();
        }
        return new ArrayList<>();
    }
    public User findUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public User getUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email.trim()))
                .findFirst()
                .orElse(null);
    }

}

