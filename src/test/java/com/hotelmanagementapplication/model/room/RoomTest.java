package com.hotelmanagementapplication.model.room;

import com.hotelmanagementapplication.model.user.Customer;
import com.hotelmanagementapplication.model.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void assignRoomToUser1() {
        Room room = new SingleBed(100.00, Status.AVAILABLE);
        User user = new Customer("John", "Doe", "john.doe@example.com", "1234567890", "password");


        Room.assignRoomToUser(room, user);

        assertEquals(Status.BOOKED, room.getStatus());
        assertEquals(user.getUserId(), room.getPurchasedBy().getUserId());
    }

    @Test
    void assignRoomToUser2() {
        Room room = new SingleBed(100.00, Status.BOOKED);
        User user = new Customer("Jane", "Smith", "jane.smith@example.com", "0987654321", "password");

        Exception exception = assertThrows(IllegalStateException.class, () ->
                Room.assignRoomToUser(room, user)
        );
        assertEquals("Room is not available for purchase", exception.getMessage());
    }

//    @Test
//    void assignRoomToUser3() {
//        Room room = new SingleBed(150.00, Status.BOOKED);
//        User user1 = new Customer("David", "Brown", "david.brown@example.com", "1122334455", "password");
//        User user2 = new Customer("Sarah", "White", "sarah.white@example.com", "9988776655", "password");
//
//        Room.assignRoomToUser(room, user1);
//
//        assertEquals(Status.BOOKED, room.getStatus());
//        assertEquals(user1.getUserId(), room.getPurchasedBy().getUserId());
//
//        Exception exception = assertThrows(IllegalStateException.class, () ->
//                Room.assignRoomToUser(room, user2)
//        );
//
//        assertEquals("Room is not available for purchase", exception.getMessage());
//    }

}