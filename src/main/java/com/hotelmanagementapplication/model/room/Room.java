package com.hotelmanagementapplication.model.room;

import com.hotelmanagementapplication.model.user.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Room {
    private static int idCounter = 0; // will be used to increment room ids
    private int roomId;
    private double price;
    private Status status;
    private User purchasedBy;
    private static int userId;

    public Room(double price, Status status) {
        this.roomId = ++idCounter;
        this.price = price;
        this.status = status;
    }

    public Room(int roomId, double roomPrice, Status roomStatus) {
        this.roomId = roomId;
        this.price = roomPrice;
        this.status = roomStatus;
    }

    public Room(int roomId, double roomPrice, Status roomStatus, int userId) {
        this.roomId = roomId;
        this.price = roomPrice;
        this.status = roomStatus;
        Room.userId = userId;
    }

    /**
     * Assigns a room to a user
     *
     * @param room The room to assign
     * @param user The user purchasing the room
     */
    public static void assignRoomToUser(Room room, User user) {
        if (room.getStatus() == Status.AVAILABLE) {
            room.setPurchasedBy(user);
            room.setStatus(Status.BOOKED);
            userId = user.getUserId();
        } else {
            throw new IllegalStateException("Room is not available for purchase");
        }
    }
}
