package com.hotelmanagementapplication.controller.utildatabase;

import com.hotelmanagementapplication.model.room.DoubleBed;
import com.hotelmanagementapplication.model.room.Room;
import com.hotelmanagementapplication.model.room.SingleBed;
import com.hotelmanagementapplication.model.room.Status;

import java.util.List;

import static com.hotelmanagementapplication.controller.utildatabase.DatabaseUtil.*;

public class RoomDatabase {

    /**
     * Creates Room table.
     */
    public static void createRoomTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS Rooms (
                room_Id INTEGER PRIMARY KEY AUTOINCREMENT,
                room_price TEXT NOT NULL,
                room_status TEXT NOT NULL,
                room_type TEXT NOT NULL
                );
                """;
        executeUpdate(sql);
    }

    /**
     * creates a table for the rooms with double bed.
     */
    public static void createDoubleBedRoomTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS DoubleBeds (
                double_bed_id INTEGER PRIMARY KEY AUTOINCREMENT,
                room_Id INTEGER NOT NULL,
                FOREIGN KEY (room_id) REFERENCES Rooms (room_Id) ON DELETE CASCADE
                )
                """;
        executeUpdate(sql);
    }

    /**
     * creates a table for the rooms with single bed.
     */
    public static void createSingleBedRoomTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS SingleBeds (
                single_bed_id INTEGER PRIMARY KEY AUTOINCREMENT,
                room_Id INTEGER NOT NULL,
                FOREIGN KEY (room_id) REFERENCES Rooms (room_Id) ON DELETE CASCADE
                )
                """;
        executeUpdate(sql);
    }

    /**
     * inserts a room records in room table.
     *
     * @param room     the room
     * @param roomType the type of room.
     */
    public static int insertRoom(Room room, String roomType) {
        String sql = """
                INSERT INTO Rooms (room_price, room_status, room_type) VALUES (?, ?, ?)
                """;
        double roomPrice = room.getPrice();
        Status roomStatus = room.getStatus();
        return executeInsert(sql, roomPrice, roomStatus, roomType);
    }

    /**
     * inserts in records in double-bed room table
     *
     * @param roomId the room id
     */
    public static void insertDoubleBedRoom(int roomId) {
        String sql = """
                INSERT INTO DoubleBeds (room_id) VALUES (?)
                """;
        executeInsert(sql, roomId);
    }

    /**
     * inserts in records in single-bed room table
     *
     * @param roomId the room id
     */
    public static void insertSingleBedRoom(int roomId) {
        String sql = """
                INSERT INTO SingleBeds (room_id) VALUES (?)
                """;
        executeInsert(sql, roomId);
    }

    /**
     * selects everything from Room table
     *
     * @return all the rooms.
     */
    public static List<Room> selectRooms() {
        String sql = "SELECT * FROM Rooms";
        return executeQuery(sql, DatabaseUtil::mapRoom);
    }

    /**
     * selects all records from SingleBed Table
     *
     * @return the rooms with single bed
     */
    public static List<SingleBed> selectSingleRooms() {
        String sql = """
                SELECT * FROM SingleBeds s
                JOIN Rooms r ON r.room_id = s.room_id
                """;
        return executeQuery(sql, DatabaseUtil::mapSingleBed);
    }

    /**
     * selects all records from DoubleBed Table
     *
     * @return the rooms with double bed.
     */
    public static List<DoubleBed> selectDoubleRooms() {
        String sql = """
                SELECT * FROM DoubleBeds d
                JOIN Rooms r ON r.room_id = d.room_id
                """;
        return executeQuery(sql, DatabaseUtil::mapDoubleBed);
    }

    /**
     * deletes a room from the database.
     *
     * @param roomId the room id that will be deleted.
     */
    public static void removeRoom(int roomId) {
        String sql = "DELETE FROM Rooms WHERE room_id = ?";
        try {
            executeUpdate(sql, roomId);
            System.out.println("Room with ID " + roomId + " removed from the database.");
        } catch (RuntimeException e) {
            throw new IllegalStateException("Failed to remove room with ID: " + roomId, e);
        }
    }

    /**
     * Method will remove room from database
     * @param room the room
     */
    public static void removeRoom(Room room) {
        int roomId = room.getRoomId();
        removeRoom(roomId);
    }

    /**
     * modifies the room specifications.
     * @param roomId the room id of the room
     * @param room the room to modify
     */
    public static void alterRoom(int roomId, Room room) {
        String sql = "UPDATE Rooms SET room_price = ?, room_status = ? WHERE room_id = ?";
        System.out.println("Altered Room");
        executeUpdate(sql, room.getPrice(), room.getStatus(), roomId);
    }
}
