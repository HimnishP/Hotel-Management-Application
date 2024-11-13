package com.hotelmanagementapplication.model.room.factory;

import com.hotelmanagementapplication.model.room.DoubleBed;
import com.hotelmanagementapplication.model.room.Room;
import com.hotelmanagementapplication.model.room.SingleBed;
import com.hotelmanagementapplication.model.room.Status;

public class RoomFactory {

    /**
     * Creates a room
     *
     * @param type   Type of room that needs to be created
     * @param price  The price of the room
     * @param status The status of the room if it will be available or not
     * @return The type of room that is created.
     */
    public static Room createRoom(String type, double price, Status status) {
        return switch (type.toLowerCase()) {
            case "single" -> new SingleBed(price, status);
            case "double" -> new DoubleBed(price, status);
            default -> throw new IllegalArgumentException("Invalid Type: " + type);
        };
    }
}
