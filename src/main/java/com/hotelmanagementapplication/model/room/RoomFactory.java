package com.hotelmanagementapplication.model.room;

public class RoomFactory {

    /**
     * creates a room
     * @param type type of room that needs to be created
     * @param price the price of the room
     * @param status the status of the room if it will be available or not
     * @return the type of room that is created.
     */
    public static Room createRoom(String type, double price, Status status){
        if (type.equalsIgnoreCase("Single")){
            return new SingleBed(price, status);
        } else if (type.equalsIgnoreCase("Double")) {
            return new DoubleBed(price, status);
        }
        else {
            throw new IllegalArgumentException("Invalid Type : " + type);
        }
    }
}
