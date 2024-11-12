package com.hotelmanagementapplication.model.system;

import com.hotelmanagementapplication.model.user.Customer;
import com.hotelmanagementapplication.model.user.Manager;
import com.hotelmanagementapplication.model.user.User;

import java.util.List;

public class HotelManagementSystem {
    private static HotelManagementSystem instance;
    private final UserSystem userSystem = new UserSystem();

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
        userSystem.addUser(user);
    }

    public User removeUser(int userId) {
        return userSystem.removeUser(userId);
    }

    public User getUserById(int userId) {
        return userSystem.getUserById(userId);
    }

    public List<User> getAllUsers() {
        return userSystem.getAllUsers();
    }

    public List<Manager> getAllManagers() {
        return userSystem.getAllManagers();
    }

    public List<Customer> getAllCustomers() {
        return userSystem.getAllCustomers();
    }

    public void updateUser(User user) {
        userSystem.updateUser(user);
    }

    public List<User> findUsersByName(String name) {
        return userSystem.findUsersByName(name);
    }

    public boolean userExists(int userId) {
        return userSystem.userExists(userId);
    }

    public int getUserCount() {
        return userSystem.getUserCount();
    }

    public List<Integer> listUserIds() {
        return userSystem.listUserIds();
    }
}
