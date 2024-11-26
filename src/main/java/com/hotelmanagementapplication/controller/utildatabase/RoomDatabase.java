package com.hotelmanagementapplication.controller.utildatabase;

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


}
