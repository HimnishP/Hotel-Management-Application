package com.hotelmanagementapplication.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CustomerLoginScreenController {
    public Button validateButton;
    public TextField firstNameTF;
    public TextField emailTF;
    public TextField lastNameTF;
    public PasswordField passwordTF;
    public TextField phoneNumberTF;

    public void validateButtonHandler(ActionEvent actionEvent) {
        if (validate()) {
            // Proceed with further actions if validation is successful
            System.out.println("Validation successful!");
        }
    }

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

        return true; // All validations passed
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean isValidEmail(String email) {
        // Basic email validation regex
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        // Regex to match phone numbers in the format 514-123-4125
        String phoneRegex = "^(\\d{3}-\\d{3}-\\d{4})$"; // Example: 514-123-4125
        return phoneNumber.matches(phoneRegex);
    }
}
