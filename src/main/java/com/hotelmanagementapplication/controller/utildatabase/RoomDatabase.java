package com.hotelmanagementapplication.controller.utildatabase;

import com.hotelmanagementapplication.model.room.Room;
import com.hotelmanagementapplication.model.room.Status;

import static com.hotelmanagementapplication.controller.utildatabase.DatabaseUtil.executeInsert;
import static com.hotelmanagementapplication.controller.utildatabase.DatabaseUtil.executeUpdate;

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

    public static void insertRoom(Room room, String roomType) {
        String sql = """
                INSERT INTO Rooms (room_price, room_status, room_type) VALUES (?, ?, ?)
                """;
        double roomPrice = room.getPrice();
        Status roomStatus = room.getStatus();

        executeInsert(sql, roomPrice, roomStatus, roomType);
    }


}