package com.hotelmanagementapplication.controller.utildatabase;

import com.hotelmanagementapplication.model.room.DoubleBed;
import com.hotelmanagementapplication.model.room.Room;
import com.hotelmanagementapplication.model.room.SingleBed;
import com.hotelmanagementapplication.model.room.Status;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.hotelmanagementapplication.controller.utildatabase.DatabaseUtil.*;

public class RoomDatabase {

    /**
     * Creates Room table.
     */
    public static void createRoomTable(){
        String sql = """
                CREATE TABLE IF NOT EXISTS Rooms (
                room_Id INTEGER PRIMARY KEY AUTOINCREMENT,
                room_price REAL NOT NULL,
                room_status TEXT NOT NULL,
                room_type TEXT NOT NULL,
                )
                """;
        executeUpdate(sql);
    }

    /**
     * creates a table for the rooms with double bed.
     */
    public static void createDoubleBedRoomTable(){
        String sql = """
                CREATE TABLE IF NOT EXISTS DoubleBeds (
                room_Id INTEGER PRIMARY KEY,
                FOREIGN KEY (room_id) REFERENCES Rooms (room_Id),
                )
                """;
        executeUpdate(sql);
    }

    /**
     * creates a table for the rooms with single bed.
     */
    public static void createSingleBedRoomTable(){
        String sql = """
                CREATE TABLE IF NOT EXISTS SingleBeds (
                room_Id INTEGER PRIMARY KEY,
                FOREIGN KEY (room_id) REFERENCES Rooms (room_Id),
                )
                """;
        executeUpdate(sql);
    }

    /**
     * inserts a room records in room table.
     * @param room the room
     * @param roomType the type of room.
     */
    public static void insertRoom(Room room, String roomType) {
        String sql = """
                INSERT INTO Rooms (room_price, room_status, room_type) VALUES (?, ?, ?)
                """;
        double roomPrice = room.getPrice();
        Status roomStatus = room.getStatus();

        executeInsert(sql, roomPrice, roomStatus, roomType);
    }

    /**
     * inserts in records in double-bed room table
     * @param roomId
     */
    public static void insertDoubleBedRoom(int roomId) {
        String sql = """
                INSERT INTO DoubleBeds (room_id) VALUES (?)
                """;
        executeInsert(sql, roomId);
    }

    /**
     * selects everything from Room table
     * @return
     */
    public static List<Room> selectRooms() {
        String sql = "SELECT * FROM Rooms";
        return executeQuery(sql, DatabaseUtil::mapRoom);
    }

    /**
     * selects all records from SingleBed Table
     * @return
     */
    public static List<SingleBed> selectSingleRooms() {
        String sql = """
                SELECT * FROM SingleBeds s
                JOIN Rooms r ON r.room_id = s.room_id
                """;
        return executeQuery(sql, DatabaseUtil::mapSingleBed);
    }

    public static List<DoubleBed> selectDoubleRooms() {
        String sql = """
                SELECT * FROM DoubleBeds d
                JOIN Rooms r ON r.room_id = d.room_id
                """;

        return executeQuery(sql, DatabaseUtil::mapDoubleBed);
    }




}
