package com.hotelmanagementapplication.model;

import com.hotelmanagementapplication.model.user.Customer;
import com.hotelmanagementapplication.model.user.Manager;
import com.hotelmanagementapplication.model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class HotelManagementSystemTest {
    private HotelManagementSystem hotelManagementSystem;

    @BeforeEach
    void setUp() {
        hotelManagementSystem = HotelManagementSystem.getInstance();
    }

    /**
     * Tests the addition of a user to the system.
     * Asserts that the user is successfully added and can be found in the system.
     */
    @Test
    void addUser_ShouldAddUserSuccessfully() {
        User customer = new Customer("Alice", "Johnson", "alice@example.com", "123-456-7890", "password");
        hotelManagementSystem.addUser(customer);
        assertTrue(hotelManagementSystem.userExists(customer.getUserId()));
    }

    /**
     * Tests the removal of a user from the system.
     * Asserts that the correct user is removed and can no longer be found in the system.
     */
    @Test
    void removeUser_ShouldRemoveUserSuccessfully() {
        User manager = new Manager("Bob", "Smith", "bob@example.com", "098-765-4321", "password");
        hotelManagementSystem.addUser(manager);
        User removedUser = hotelManagementSystem.removeUser(manager.getUserId());
        assertEquals(manager, removedUser);
        assertFalse(hotelManagementSystem.userExists(manager.getUserId()));
    }

    /**
     * Tests the removal of a user that does not exist.
     * Asserts that an exception is thrown when attempting to remove a non-existing user.
     */
    @Test
    void removeUser_ShouldThrowExceptionIfUserDoesNotExist() {
        assertThrows(NoSuchElementException.class, () -> hotelManagementSystem.removeUser(999));
    }

    /**
     * Tests retrieval of a user by their ID.
     * Asserts that the correct user is returned when a valid ID is provided.
     */
    @Test
    void getUserById_ShouldReturnUser() {
        User customer = new Customer("Charlie", "Brown", "charlie@example.com", "111-222-3333", "password");
        hotelManagementSystem.addUser(customer);
        User retrievedUser = hotelManagementSystem.getUserById(customer.getUserId());
        assertEquals(customer, retrievedUser);
    }

    /**
     * Tests retrieval of a user by an ID that does not exist.
     * Asserts that an exception is thrown when attempting to get a non-existing user.
     */
    @Test
    void getUserById_ShouldThrowExceptionIfUserDoesNotExist() {
        assertThrows(NoSuchElementException.class, () -> hotelManagementSystem.getUserById(999));
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void getAllManagers() {
    }

    @Test
    void getAllCustomers() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void findUsersByName() {
    }

    @Test
    void userExists() {
    }

    @Test
    void getUserCount() {
    }

    @Test
    void listUserIds() {
    }
}