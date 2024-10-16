package com.hotelmanagementapplication.model;

public class Customer extends User{
    private int customerId;
    private PaymentMethod paymentMethod;

    public Customer(int userId, String firstName, String lastName, String email, String phoneNum, String password) {
        super(userId, firstName, lastName, email, phoneNum, password);
    }
}
