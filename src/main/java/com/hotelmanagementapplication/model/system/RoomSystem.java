package com.hotelmanagementapplication.model.system;

import com.hotelmanagementapplication.controller.DatabaseController;
import com.hotelmanagementapplication.model.room.Room;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class RoomSystem {
    private final Map<Integer, Room> rooms; // RoomId : Room
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public RoomSystem(Map<Integer, Room> rooms) {
        this.rooms = DatabaseController.selectRooms()
                .stream()
                .collect(Collectors.toMap(Room::getRoomId, room -> room));
    }

    /**
     * Adds a room to the system.
     *
     * @param room     The room to be added.
     * @param roomType The type of the room (e.g., "SingleBed", "DoubleBed").
     */
    public void addRoom(Room room, String roomType) {
        executorService.submit(() -> {
            rooms.put(room.getRoomId(), room);
            DatabaseController.insertRoom(room, roomType);

            if ("SingleBed".equalsIgnoreCase(roomType)) {
                DatabaseController.insertSingleBedRoom(room.getRoomId());
            } else if ("DoubleBed".equalsIgnoreCase(roomType)) {
                DatabaseController.insertDoubleBedRoom(room.getRoomId());
            } else {
                throw new IllegalArgumentException("Invalid room type: " + roomType);
            }
        });
    }


}
