package com.hotelmanagementapplication.model;

public class HotelManagementSystem {
    private static HotelManagementSystem instance;
    //TODO declare all of the lists , maps or sets here

    private HotelManagementSystem() {
        //TODO initialize them here
    }

    public static HotelManagementSystem getInstance() {
        if (instance == null) {
            synchronized (HotelManagementSystem.class) {
                if (instance == null) {
                    instance = new HotelManagementSystem();
                }
            }
        }
        return instance;
    }
}
