package com.hotelmanagementapplication.model;

public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNum;
    private String password;

    public User(int userId, String firstName, String lastName, String email, String phoneNum, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNum = phoneNum;
        this.password = password;
    }

    /**
     * puts the first and last name together
     * @return the full name of the user
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
