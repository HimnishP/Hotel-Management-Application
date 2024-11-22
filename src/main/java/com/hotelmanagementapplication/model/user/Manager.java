package com.hotelmanagementapplication.model.user;

public class Manager extends User {

    public Manager(String firstName, String lastName, String email, String phoneNum, String password) {
        super(firstName, lastName, email, phoneNum, password);
    }

    public Manager(User user) {
        super(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNum(), user.getPassword());
    }
}
