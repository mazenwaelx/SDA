package com.cairouniv.fci.travel.agency.UserManagement;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private static final List<User> userList = new ArrayList<>();
    public boolean loginUser(String username, String password) {
        System.out.println("Authenticating user: " + username);
        return "user".equals(username) && "password".equals(password);
    }
    public String updateUser(String userId, String newDetails) {
        System.out.println("Updating user: " + userId + " with details: " + newDetails);
        return "User updated successfully";
    }
    public String DeleteUser(String userID){
        System.out.println("Updating user: " + userID);
        return "User Deleted Successfully";
    }
    public String addUser(User user){
        user.adduser(user);
        userList.add(user);
        return "User added";
    }



}
