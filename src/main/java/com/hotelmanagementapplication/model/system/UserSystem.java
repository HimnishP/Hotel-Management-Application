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
import java.util.concurrent.Future;
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
            int id = 0;
            if (user instanceof Manager) {
                id = DatabaseController.insertUser(user, "Manager");
                DatabaseController.insertManager(id);
            } else if (user instanceof Customer) {
                id = DatabaseController.insertUser(user, "Customer");
                DatabaseController.insertCustomer(id);
            } else {
                throw new IllegalArgumentException("Must be a user ");
            }
        });
    }

    /**
     * Removes a user by ID.
     *
     * @param userId the ID of the user to remove
     * @return the removed user
     */
    public Future<User> removeUser(int userId) {
        return EXECUTOR_SERVICE.submit(() -> {
            boolean userExists = userExistsAsync(userId).get();
            if (!userExists) {
                throw new NoSuchElementException("ERROR: User with id " + userId + " does not exist!");
            }
            try {
                DatabaseController.removeUser(userId);
            } catch (Exception e) {
                throw new RuntimeException("Failed to remove user with id " + userId + " from the database.", e);
            }
            return userMap.remove(userId);
        });
    }

    /**
     * Finds a user by ID.
     *
     * @param userId the ID of the user to find
     * @return the user
     */
    public Future<User> getUserById(int userId) {
        return EXECUTOR_SERVICE.submit(() -> {
            if (!userExistsAsync(userId).get()) {
                throw new NoSuchElementException("ERROR: User with id " + userId + " does not exist!");
            }
            return userMap.get(userId);
        });
    }

    /**
     * Lists all users.
     *
     * @return a list of all users
     */
    public Future<List<User>> getAllUsers() {
        return EXECUTOR_SERVICE.submit(() -> userMap.values().stream().toList());
    }

    /**
     * Lists all managers.
     *
     * @return a list of managers
     */
    public Future<List<Manager>> getAllManagers() {
        return EXECUTOR_SERVICE.submit(() -> userMap.values().stream()
                .filter(user -> user instanceof Manager)
                .map(user -> (Manager) user)
                .toList());
    }

    /**
     * Lists all customers.
     *
     * @return a list of customers
     */
    public Future<List<Customer>> getAllCustomers() {
        return EXECUTOR_SERVICE.submit(() -> userMap.values().stream()
                .filter(user -> user instanceof Customer)
                .map(user -> (Customer) user)
                .toList());
    }

    /**
     * Updates an existing user.
     *
     * @param user the user with updated information
     * @param id id of user
     */
    public Future<Void> updateUser(int id, User user) {
        return EXECUTOR_SERVICE.submit(() -> {
            if (!userExistsAsync(id).get()) {
                throw new NoSuchElementException("ERROR: User with id " + id + " does not exist!");
            }
            DatabaseController.alterUser(id, user);
            userMap.put(id, user);
            return null;
        });
    }

    /**
     * Finds users by name.
     *
     * @param name the name to search for
     * @return a list of matching users
     */
    public Future<List<User>> findUsersByName(String name) {
        return EXECUTOR_SERVICE.submit(() -> userMap.values().stream()
                .filter(user -> user.getFullName().contains(name))
                .toList());
    }

    /**
     * Checks if a user exists by ID.
     *
     * @param userId the ID of the user
     * @return true if the user exists, false otherwise
     */
    public Future<Boolean> userExistsAsync(int userId) {
        return EXECUTOR_SERVICE.submit(() -> userMap.containsKey(userId));
    }

    /**
     * Counts the total users.
     *
     * @return the total number of users
     */
    public Future<Integer> getUserCount() {
        return EXECUTOR_SERVICE.submit(userMap::size);
    }

    /**
     * Lists all user IDs.
     *
     * @return a list of user IDs
     */
    public Future<List<Integer>> listUserIds() {
        return EXECUTOR_SERVICE.submit(() -> userMap.keySet()
                .stream()
                .toList());
    }

    /**
     * Method will check if there is an existing user based on email and password
     *
     * @param email    The email
     * @param password The password
     * @return True if user found or false if not found
     */
    public Future<Boolean> validateExistingCustomer(String email, String password) {
        return EXECUTOR_SERVICE.submit(() -> {
            boolean isValid;
            return isValid = userMap.values().stream()
                    .anyMatch(user -> user.getEmail().equals(email)
                            && user.getPassword().equals(password));
        });
    }
}
