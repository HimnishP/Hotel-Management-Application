package com.hotelmanagementapplication.model.room;

public class RoomFactory {

    public static Room createRoom(String type){
        if (type.equalsIgnoreCase("Single")){
            return new SingleBed();
        } else if (type.equalsIgnoreCase("Double")) {
            return new DoubleBed();
        }
        else {
            throw new IllegalArgumentException("Invalid Type : " + type);
        }
    }
}
