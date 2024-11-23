package com.hotelmanagementapplication.model;

import com.hotelmanagementapplication.model.system.HotelManagementSystem;
import com.hotelmanagementapplication.model.user.Customer;
import com.hotelmanagementapplication.model.user.Manager;
import com.hotelmanagementapplication.model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
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

    /**
     * Tests the retrieval of all users from the system.
     * Asserts that the correct number of users is returned and that the expected users are included.
     */
    @Test
    void getAllUsers_ShouldReturnAllUsers() {
        User customer1 = new Customer("Alice", "Johnson", "alice@example.com", "123-456-7890", "password");
        User customer2 = new Customer("Bob", "Smith", "bob@example.com", "098-765-4321", "password");
        hotelManagementSystem.addUser(customer1);
        hotelManagementSystem.addUser(customer2);
        List<User> users = hotelManagementSystem.getAllUsers();
        assertEquals(2, users.size());
        assertTrue(users.contains(customer1));
        assertTrue(users.contains(customer2));
    }

    /**
     * Tests the retrieval of all managers from the system.
     * Asserts that the correct number of managers is returned and that the expected managers are included.
     */
    @Test
    void getAllManagers_ShouldReturnAllManagers() {
        User manager1 = new Manager("Bob", "Smith", "bob@example.com", "098-765-4321", "password");
        User manager2 = new Manager("Alice", "Johnson", "alice@example.com", "123-456-7890", "password");
        hotelManagementSystem.addUser(manager1);
        hotelManagementSystem.addUser(manager2);
        List<Manager> managers = hotelManagementSystem.getAllManagers();
        assertEquals(2, managers.size());
        assertTrue(managers.contains(manager1));
        assertTrue(managers.contains(manager2));
    }

    /**
     * Tests the retrieval of all customers from the system.
     * Asserts that the correct number of customers is returned and that the expected customers are included.
     */
    @Test
    void getAllCustomers_ShouldReturnAllCustomers() {
        User customer1 = new Customer("Charlie", "Brown", "charlie@example.com", "111-222-3333", "password");
        User customer2 = new Customer("Diana", "Prince", "diana@example.com", "444-555-6666", "password");
        hotelManagementSystem.addUser(customer1);
        hotelManagementSystem.addUser(customer2);
        List<Customer> customers = hotelManagementSystem.getAllCustomers();
        assertEquals(2, customers.size());
        assertTrue(customers.contains(customer1));
        assertTrue(customers.contains(customer2));
    }

    /**
     * Tests updating a user in the system.
     * Asserts that the user’s details are updated correctly.
     */
    @Test
    void updateUser_ShouldUpdateUserSuccessfully() {
        User customer = new Customer("Alice", "Johnson", "alice@example.com", "123-456-7890", "password");
        hotelManagementSystem.addUser(customer);
        customer.setEmail("newalice@example.com");
        hotelManagementSystem.updateUser(customer.getUserId(),customer);
        User updatedUser = hotelManagementSystem.getUserById(customer.getUserId());
        assertEquals("newalice@example.com", updatedUser.getEmail());
    }

    /**
     * Tests updating a user that does not exist.
     * Asserts that an exception is thrown when attempting to update a non-existing user.
     */
    @Test
    void updateUser_ShouldThrowExceptionIfUserDoesNotExist() {
        User customer = new Customer("Alice", "Johnson", "alice@example.com", "123-456-7890", "password");
        assertThrows(NoSuchElementException.class, () -> hotelManagementSystem.updateUser(customer.getUserId(), customer));
    }

    /**
     * Tests searching for users by their name.
     * Asserts that the correct users are returned when searching by name.
     */
    @Test
    void findUsersByName_ShouldReturnMatchingUsers() {
        User customer1 = new Customer("Charlie", "Brown", "charlie@example.com", "111-222-3333", "password");
        User customer2 = new Customer("Charlie", "Johnson", "charlie.j@example.com", "222-333-4444", "password");
        hotelManagementSystem.addUser(customer1);
        hotelManagementSystem.addUser(customer2);
        List<User> foundUsers = hotelManagementSystem.findUsersByName("Charlie");
        assertEquals(2, foundUsers.size());
        assertTrue(foundUsers.contains(customer1));
        assertTrue(foundUsers.contains(customer2));
    }

    /**
     * Tests checking if a user exists by their ID.
     * Asserts that the method returns true for existing users and false for non-existing users.
     */
    @Test
    void userExists_ShouldReturnTrueForExistingUser() {
        User customer = new Customer("Alice", "Johnson", "alice@example.com", "123-456-7890", "password");
        hotelManagementSystem.addUser(customer);
        boolean exists = hotelManagementSystem.userExists(customer.getUserId());
        assertTrue(exists);
    }

    /**
     * Tests checking if a user exists by an ID that does not exist.
     * Asserts that the method returns false for non-existing users.
     */
    @Test
    void userExists_ShouldReturnFalseForNonExistingUser() {
        boolean exists = hotelManagementSystem.userExists(999);
        assertFalse(exists);
    }

    /**
     * Tests getting the count of users in the system.
     * Asserts that the count reflects the number of users added.
     */
    @Test
    void getUserCount_ShouldReturnCorrectUserCount() {
        User customer1 = new Customer("Alice", "Johnson", "alice@example.com", "123-456-7890", "password");
        User customer2 = new Customer("Bob", "Smith", "bob@example.com", "098-765-4321", "password");
        hotelManagementSystem.addUser(customer1);
        hotelManagementSystem.addUser(customer2);
        int count = hotelManagementSystem.getUserCount();
        assertEquals(2, count);
    }

    /**
     * Tests listing all user IDs in the system.
     * Asserts that the correct IDs are returned and match the IDs of existing users.
     */
    @Test
    void listUserIds_ShouldReturnAllUserIds() {
        User customer1 = new Customer("Alice", "Johnson", "alice@example.com", "123-456-7890", "password");
        User customer2 = new Customer("Bob", "Smith", "bob@example.com", "098-765-4321", "password");
        hotelManagementSystem.addUser(customer1);
        hotelManagementSystem.addUser(customer2);
        List<Integer> userIds = hotelManagementSystem.listUserIds();
        assertTrue(userIds.contains(customer1.getUserId()));
        assertTrue(userIds.contains(customer2.getUserId()));
        assertEquals(2, userIds.size());
    }
}
