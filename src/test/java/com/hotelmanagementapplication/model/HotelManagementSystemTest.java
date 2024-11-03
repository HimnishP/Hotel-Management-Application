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
        User manager = new Manager("Bob", "Smith", "bob@example.com", "0987654321", "password");
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
        // Assert
        assertThrows(NoSuchElementException.class, () -> hotelManagementSystem.removeUser(999));
    }

    @Test
    void getUserById() {
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