package com.hotelmanagementapplication.model.system;

import com.hotelmanagementapplication.model.room.Room;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RoomSystem {
    private final Map<Integer, Room> rooms;
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public RoomSystem(Map<Integer, Room> rooms) {
        //TODO : Initialize map by retrieving DB values
        this.rooms = rooms;
    }


}
