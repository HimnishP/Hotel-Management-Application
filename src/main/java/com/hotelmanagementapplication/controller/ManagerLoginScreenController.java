package com.hotelmanagementapplication.controller;

import com.hotelmanagementapplication.ManagerAnalyticsScreen;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerLoginScreenController {
    public Button validateButton;
    public TextField firstNameTF;
    public TextField emailTF;
    public TextField lastNameTF;
    public PasswordField passwordTF;
    public TextField phoneNumberTF;

    /**
     * This button event handler will validate the customers information and switch screens
     *
     * @param actionEvent Event
     */
    public void validateButtonHandler(ActionEvent actionEvent) throws IOException {
        if (validate()) {
            Stage stage = new Stage();
            ManagerAnalyticsScreen.launchManagerAnalyticsScreen(stage);
            System.out.println("Validation successful!");
        }
    }

    /**
     * Method will validate customers information
     * First Name,Last Name,Email,Password,Phone Number cannot be empty
     *
     * @return True if valid or False if not valid
     */
    private boolean validate() {
        String firstName = firstNameTF.getText().trim();
        String lastName = lastNameTF.getText().trim();
        String email = emailTF.getText().trim();
        String password = passwordTF.getText();
        String phoneNumber = phoneNumberTF.getText().trim();

        if (firstName.isEmpty()) {
            showAlert("First name is required.");
            return false;
        }
        if (lastName.isEmpty()) {
            showAlert("Last name is required.");
            return false;
        }
        if (email.isEmpty() || !isValidEmail(email)) {
            showAlert("A valid email is required.");
            return false;
        }
        if (password.isEmpty() || password.length() < 6) {
            showAlert("Password must be at least 6 characters long.");
            return false;
        }
        if (phoneNumber.isEmpty() || !isValidPhoneNumber(phoneNumber)) {
            showAlert("A valid phone number is required.");
            return false;
        }
        return true;
    }

    /**
     * Method, when called, will display alert message indicating what the user must validate
     *
     * @param message to be displayed
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Method will validate email based on predefined format (example@gmail.com)
     *
     * @param email the email which user entered
     * @return true if valid or false if not valid
     */
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    /**
     * Method will validate phone number based on predefined format (123-456-7890)
     *
     * @param phoneNumber the phone number which user entered
     * @return true if valid or false if not valid
     */
    private boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^(\\d{3}-\\d{3}-\\d{4})$";
        return phoneNumber.matches(phoneRegex);
    }
}

