package com.hotelmanagementapplication.model.system;

import com.hotelmanagementapplication.controller.DatabaseController;
import com.hotelmanagementapplication.model.room.Room;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
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

    /**
     * Removes a room from the system by ID.
     *
     * @param roomId The ID of the room to be removed.
     * @return A Future containing the removed room.
     */
    public Future<Room> removeRoom(int roomId) {
        return executorService.submit(() -> {
            if (!roomExistsAsync(roomId).get()) {
                throw new NoSuchElementException("ERROR: Room with id " + roomId + " does not exist!");
            }
            DatabaseController.removeRoom(roomId);
            return rooms.remove(roomId);
        });
    }

    /**
     * Checks if a room exists by ID.
     *
     * @param roomId The ID of the room.
     * @return A Future containing true if the room exists, false otherwise.
     */
    public Future<Boolean> roomExistsAsync(int roomId) {
        return executorService.submit(() -> rooms.containsKey(roomId));
    }

    /**
     * Retrieves a room by ID.
     *
     * @param roomId The ID of the room to retrieve.
     * @return A Future containing the room.
     */
    public Future<Room> getRoomById(int roomId) {
        return executorService.submit(() -> {
            if (!roomExistsAsync(roomId).get()) {
                throw new NoSuchElementException("ERROR: Room with id " + roomId + " does not exist!");
            }
            return rooms.get(roomId);
        });
    }

    /**
     * Retrieves all rooms.
     *
     * @return A Future containing a list of all rooms.
     */
    public Future<List<Room>> getAllRooms() {
        return executorService.submit(() -> rooms.values().stream().toList());
    }
}
