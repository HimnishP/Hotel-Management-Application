package com.hotelmanagementapplication.model;

public class Manager extends User {
    private int managerId;

    public Manager(int userId, String firstName, String lastName, String email, String phoneNum, String password) {
        super(userId, firstName, lastName, email, phoneNum, password);
    }
}
