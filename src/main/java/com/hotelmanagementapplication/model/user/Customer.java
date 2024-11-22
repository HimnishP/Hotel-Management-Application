package com.hotelmanagementapplication.model.user;

public class Customer extends User {

    public Customer(String firstName, String lastName, String email, String phoneNum, String password) {
        super(firstName, lastName, email, phoneNum, password);
    }

    public Customer(User user) {
        super(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNum(), user.getPassword());
    }
}
