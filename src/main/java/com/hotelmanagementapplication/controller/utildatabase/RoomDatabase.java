package com.hotelmanagementapplication.controller.utildatabase;

import static com.hotelmanagementapplication.controller.utildatabase.DatabaseUtil.executeUpdate;

public class RoomDatabase {

    /**
     * Creates Room table.
     */
    public static void createRoomTable(){
        String sql = """
                CREATE TABLE IF NOT EXISTS Rooms (
                roomId INTEGER PRIMARY KEY AUTOINCREMENT,
                room_price REAL NOT NULL,
                room_status TEXT NOT NULL,
                room_type TEXT NOT NULL,
                )
                """;
        executeUpdate(sql);
    }

}
