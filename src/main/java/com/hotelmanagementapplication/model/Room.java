package com.hotelmanagementapplication.model;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private int roomId;
    private double price;
    private Status status;
    List<Room> listOfRooms;

    public Room(int roomId, double price, Status status) {
        this.roomId = roomId;
        this.price = price;
        this.status = status;
        listOfRooms = new ArrayList<>();
    }
}
