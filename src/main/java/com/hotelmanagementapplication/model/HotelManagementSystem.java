package com.hotelmanagementapplication.model;

import com.hotelmanagementapplication.model.user.User;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class HotelManagementSystem {
    private static HotelManagementSystem instance;
    // Users
    private Map<Integer, User> userMap = new HashMap<>(); // key : userID , value : user


    private HotelManagementSystem() {
    }

    public static HotelManagementSystem getInstance() {
        if (instance == null) {
            synchronized (HotelManagementSystem.class) {
                if (instance == null) {
                    instance = new HotelManagementSystem();
                }
            }
        }
        return instance;
    }

    public void addUser(User user) {
        userMap.put(user.getUserId(), user);
    }

    public User removeUser(int userId) {
        if (!userMap.containsKey(userId)) {
            throw new NoSuchElementException("ERROR: User with id " + userId + " does not exist!");
        }
        return userMap.remove(userId);
    }
}
