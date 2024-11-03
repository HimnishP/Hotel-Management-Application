package com.hotelmanagementapplication.model;

import com.hotelmanagementapplication.model.user.Customer;
import com.hotelmanagementapplication.model.user.Manager;
import com.hotelmanagementapplication.model.user.User;

import java.util.HashMap;
import java.util.List;
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

    /**
     * Adds a user to the map
     *
     * @param user which will be added
     */
    public void addUser(User user) {
        userMap.put(user.getUserId(), user);
    }

    /**
     * Removes user based on ID
     *
     * @param userId of the user
     * @return the removed user, if any or else throws exception
     */
    public User removeUser(int userId) {
        if (!userExists(userId)) {
            throw new NoSuchElementException("ERROR: User with id " + userId + " does not exist!");
        }
        return userMap.remove(userId);
    }

    /**
     * Returns the user by its id
     *
     * @param userId the id
     * @return the user, if any or else throws exception
     */
    public User getUserById(int userId) {
        if (!userExists(userId)) {
            throw new NoSuchElementException("ERROR: User with id " + userId + " does not exist!");
        }
        return userMap.get(userId);
    }

    /**
     * Method will return a list of all users
     *
     * @return the list of users
     */
    public List<User> getAllUsers() {
        if (userMap.isEmpty()) {
            throw new NoSuchElementException("There are no users");
        }
        return userMap.values().stream().toList();
    }

    /**
     * Method will return a list of all managers
     *
     * @return list of managers
     */
    public List<Manager> getAllManagers() {
        if (userMap.isEmpty()) {
            throw new NoSuchElementException("There are no users");
        }
        return userMap.values().stream()
                .filter(user -> user instanceof Manager)
                .map(user -> (Manager) user)
                .toList();
    }

    /**
     * Method will return a list of customers
     *
     * @return list of customers
     */
    public List<Customer> getAllCustomers() {
        if (userMap.isEmpty()) {
            throw new NoSuchElementException("There are no users");
        }
        return userMap.values().stream()
                .filter(user -> user instanceof Customer)
                .map(user -> (Customer) user)
                .toList();
    }

    /**
     * Updates the information of an existing user in the map.
     *
     * @param user the user with updated information
     * @throws NoSuchElementException if the user does not exist in the map
     */
    public void updateUser(User user) {
        if (!userMap.containsValue(user)) {
            throw new NoSuchElementException("ERROR: User with id " + user.getUserId() + " does not exist!");
        }
        userMap.put(user.getUserId(), user);
    }

    /**
     * Finds users in the map based on their name.
     *
     * @param name the name to search for
     * @return a list of users whose names contain the specified name
     */
    public List<User> findUsersByName(String name) {
        return userMap.values().stream()
                .filter(user -> user.getFullName().contains(name))
                .toList();
    }

    /**
     * Checks if a user exists in the map by their ID.
     *
     * @param userId the ID of the user
     * @return true if the user exists, false otherwise
     */
    public boolean userExists(int userId) {
        return userMap.containsKey(userId);
    }

    /**
     * Returns the total number of users in the map.
     *
     * @return the count of users
     */
    public int getUserCount() {
        return userMap.size();
    }

    /**
     * Lists all user IDs present in the map.
     *
     * @return a list of user IDs
     */
    public List<Integer> listUserIds() {
        return userMap.keySet().stream().toList();
    }
}
