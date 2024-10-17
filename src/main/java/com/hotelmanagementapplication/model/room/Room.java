package com.hotelmanagementapplication.model.room;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Room {
    private static int idCounter = 0; // will be used to increment room ids
    private String roomId;
    private double price;
    private Status status;
    List<Room> listOfRooms;

    public Room(double price, Status status) {
        this.roomId = String.valueOf(++idCounter);
        this.price = price;
        this.status = status;
        listOfRooms = new ArrayList<>();
    }
}
