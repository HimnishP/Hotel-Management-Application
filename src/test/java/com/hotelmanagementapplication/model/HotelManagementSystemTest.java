package com.hotelmanagementapplication.model;

import com.hotelmanagementapplication.model.user.Customer;
import com.hotelmanagementapplication.model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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


    @Test
    void addUser() {
    }

    @Test
    void removeUser() {
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