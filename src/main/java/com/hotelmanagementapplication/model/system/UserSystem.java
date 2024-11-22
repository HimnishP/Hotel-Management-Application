package com.hotelmanagementapplication.model.system;

import com.hotelmanagementapplication.controller.DatabaseController;
import com.hotelmanagementapplication.model.user.Customer;
import com.hotelmanagementapplication.model.user.Manager;
import com.hotelmanagementapplication.model.user.User;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class UserSystem {
    private final Map<Integer, User> userMap; // key : userID , value : user
    private final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    public UserSystem() {
        userMap = DatabaseController.selectUsers()
                .stream()
                .collect(Collectors.toMap(User::getUserId, user -> user));
    }

    /**
     * Adds a user to the system.
     *
     * @param user the user to add
     */
    public void addUser(User user) {
        EXECUTOR_SERVICE.submit(() -> {
            userMap.put(user.getUserId(), user);
            DatabaseController.insertUser(user);
        });
    }

    /**
     * Removes a user by ID.
     *
     * @param userId the ID of the user to remove
     * @return the removed user
     */
    public User removeUser(int userId) {
        EXECUTOR_SERVICE.submit(() -> {
            if (!userExists(userId)) {
                throw new NoSuchElementException("ERROR: User with id " + userId + " does not exist!");
            }
            //TODO : Implement a remove user row method in database util
            return userMap.remove(userId);
        });
    }

    /**
     * Finds a user by ID.
     *
     * @param userId the ID of the user to find
     * @return the user
     */
    public User getUserById(int userId) {
        if (!userExists(userId)) {
            throw new NoSuchElementException("ERROR: User with id " + userId + " does not exist!");
        }
        return userMap.get(userId);
    }

    /**
     * Lists all users.
     *
     * @return a list of all users
     */
    public List<User> getAllUsers() {
        return userMap.values().stream().toList();
    }

    /**
     * Lists all managers.
     *
     * @return a list of managers
     */
    public List<Manager> getAllManagers() {
        return userMap.values().stream()
                .filter(user -> user instanceof Manager)
                .map(user -> (Manager) user)
                .toList();
    }

    /**
     * Lists all customers.
     *
     * @return a list of customers
     */
    public List<Customer> getAllCustomers() {
        return userMap.values().stream()
                .filter(user -> user instanceof Customer)
                .map(user -> (Customer) user)
                .toList();
    }

    /**
     * Updates an existing user.
     *
     * @param user the user with updated information
     */
    public void updateUser(User user) {
        if (!userExists(user.getUserId())) {
            throw new NoSuchElementException("ERROR: User with id " + user.getUserId() + " does not exist!");
        }
        userMap.put(user.getUserId(), user);
    }

    /**
     * Finds users by name.
     *
     * @param name the name to search for
     * @return a list of matching users
     */
    public List<User> findUsersByName(String name) {
        return userMap.values().stream()
                .filter(user -> user.getFullName().contains(name))
                .toList();
    }

    /**
     * Checks if a user exists by ID.
     *
     * @param userId the ID of the user
     * @return true if the user exists, false otherwise
     */
    public boolean userExists(int userId) {
        return userMap.containsKey(userId);
    }

    /**
     * Counts the total users.
     *
     * @return the total number of users
     */
    public int getUserCount() {
        return userMap.size();
    }

    /**
     * Lists all user IDs.
     *
     * @return a list of user IDs
     */
    public List<Integer> listUserIds() {
        return userMap.keySet().stream().toList();
    }
}
