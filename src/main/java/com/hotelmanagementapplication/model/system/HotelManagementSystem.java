package com.hotelmanagementapplication.model.system;

import com.hotelmanagementapplication.model.user.Customer;
import com.hotelmanagementapplication.model.user.Manager;
import com.hotelmanagementapplication.model.user.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class HotelManagementSystem {
    private static HotelManagementSystem instance;
    private final UserSystem userSystem;

    private HotelManagementSystem() {
        userSystem = new UserSystem();
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
     * Adds a user to the system asynchronously.
     *
     * @param user the user to add
     * @return
     */
    public void addUser(User user) {
        userSystem.addUser(user);
    }

    /**
     * Removes a user by ID and returns the removed user.
     *
     * @param userId the ID of the user to remove
     * @return the removed user
     */
    public User removeUser(int userId) {
        try {
            return userSystem.removeUser(userId).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to remove user with ID " + userId, e);
        }
    }

    /**
     * Retrieves a user by ID.
     *
     * @param userId the ID of the user to retrieve
     * @return the user
     */
    public User getUserById(int userId) {
        try {
            return userSystem.getUserById(userId).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to retrieve user with ID " + userId, e);
        }
    }

    /**
     * Retrieves a list of all users.
     *
     * @return the list of all users
     */
    public List<User> getAllUsers() {
        try {
            return userSystem.getAllUsers().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to retrieve all users", e);
        }
    }

    /**
     * Retrieves a list of all managers.
     *
     * @return the list of all managers
     */
    public List<Manager> getAllManagers() {
        try {
            return userSystem.getAllManagers().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to retrieve all managers", e);
        }
    }

    /**
     * Retrieves a list of all customers.
     *
     * @return the list of all customers
     */
    public List<Customer> getAllCustomers() {
        try {
            return userSystem.getAllCustomers().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to retrieve all customers", e);
        }
    }

    /**
     * Updates a user asynchronously.
     *
     * @param user the user with updated information
     * @param id the id of the user
     */
    public void updateUser(int id, User user) {
        userSystem.updateUser(id, user);
    }

    /**
     * Finds users by name.
     *
     * @param name the name to search for
     * @return the list of matching users
     */
    public List<User> findUsersByName(String name) {
        try {
            return userSystem.findUsersByName(name).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to find users by name: " + name, e);
        }
    }

    /**
     * Checks if a user exists by ID.
     *
     * @param userId the ID of the user
     * @return true if the user exists, false otherwise
     */
    public boolean userExists(int userId) {
        try {
            return userSystem.userExistsAsync(userId).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to check if user exists with ID " + userId, e);
        }
    }

    /**
     * Retrieves the total user count.
     *
     * @return the total number of users
     */
    public int getUserCount() {
        try {
            return userSystem.getUserCount().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to retrieve user count", e);
        }
    }

    /**
     * Lists all user IDs.
     *
     * @return the list of user IDs
     */
    public List<Integer> listUserIds() {
        try {
            return userSystem.listUserIds().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to list user IDs", e);
        }
    }
}
