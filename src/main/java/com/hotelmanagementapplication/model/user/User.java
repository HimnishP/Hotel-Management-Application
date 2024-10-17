package com.hotelmanagementapplication.model.user;

import lombok.*;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    private static int userIdCounter = 0; // will be used to increment user id
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNum;
    private String password;

    public User(String firstName, String lastName, String email, String phoneNum, String password) {
        this.userId = String.valueOf(++userIdCounter);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNum = phoneNum;
        this.password = password;
    }

    /**
     * Puts the first and last name together.
     *
     * @return the full name of the user
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
