package com.hotelmanagementapplication.model.room.factory;

import com.hotelmanagementapplication.model.room.DoubleBed;
import com.hotelmanagementapplication.model.room.Room;
import com.hotelmanagementapplication.model.room.SingleBed;
import com.hotelmanagementapplication.model.room.Status;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomFactoryTest {

    // Test case for creating a single room
    @Test
    void createRoom1() {
        Room singleRoom = RoomFactory.createRoom("single", 100.00, Status.AVAILABLE);
        assertTrue(singleRoom instanceof SingleBed);
        assertEquals(100.00, singleRoom.getPrice());
        assertEquals(Status.AVAILABLE, singleRoom.getStatus());
    }

    // Test case for creating a double room
    @Test
    void createRoom2() {
        Room doubleRoom = RoomFactory.createRoom("double", 150.00, Status.BOOKED);
        assertTrue(doubleRoom instanceof DoubleBed);
        assertEquals(150.00, doubleRoom.getPrice());
        assertEquals(Status.BOOKED, doubleRoom.getStatus());
    }

    // Test case for creating a room with an invalid type
    @Test
    void createRoom3() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                RoomFactory.createRoom("suite", 200.00, Status.AVAILABLE)
        );
        assertEquals("Invalid Type: suite", exception.getMessage());
    }

    // Test case for creating a room using the overloaded method with an existing room
    @Test
    void createRoom4() {
        Room existingRoom = new SingleBed(100.00, Status.BOOKED);
        Room newRoom = RoomFactory.createRoom("double", existingRoom);

        assertTrue(newRoom instanceof DoubleBed);
        assertEquals(100.00, newRoom.getPrice()); // price is copied from existing room
        assertEquals(Status.BOOKED, newRoom.getStatus()); // status is copied from existing room
    }

    // Test case for creating a room with a blank type (should throw exception)
    @Test
    void createRoom5() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                RoomFactory.createRoom("", 150.00, Status.AVAILABLE)
        );
        assertEquals("Invalid Type: ", exception.getMessage());
    }
}