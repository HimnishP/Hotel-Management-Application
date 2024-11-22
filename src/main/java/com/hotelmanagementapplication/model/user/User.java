package com.hotelmanagementapplication.model.user;

import lombok.*;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    private static int userIdCounter = 0; // will be used to increment user id
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNum;
    private String password;

    public User(String firstName, String lastName, String email, String phoneNum, String password) {
        this.userId = ++userIdCounter;
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

    /**
     * Method will validate email based on predefined format (example@gmail.com)
     *
     * @param email the email which user entered
     * @return true if valid or false if not valid
     */
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    /**
     * Method will validate phone number based on predefined format (123-456-7890)
     *
     * @param phoneNumber the phone number which user entered
     * @return true if valid or false if not valid
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^(\\d{3}-\\d{3}-\\d{4})$";
        return phoneNumber.matches(phoneRegex);
    }
}
