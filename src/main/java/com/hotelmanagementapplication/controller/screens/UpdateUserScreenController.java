package com.hotelmanagementapplication.controller.screens;

import com.hotelmanagementapplication.controller.l10n_i18n.ScreenHandler;
import com.hotelmanagementapplication.model.system.HotelManagementSystem;
import com.hotelmanagementapplication.model.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

import static com.hotelmanagementapplication.model.user.User.isValidEmail;
import static com.hotelmanagementapplication.model.user.User.isValidPhoneNumber;

public class UpdateUserScreenController {
    @FXML
    private Label displayLabel;
    @FXML
    private TextField idTF;
    @FXML
    private TextField firstNameTF;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField lastNameTF;
    @FXML
    private PasswordField passwordTF;
    @FXML
    private TextField phoneNumberTF;

    private final HotelManagementSystem hotelManagementSystem = HotelManagementSystem.getInstance();
    private final ResourceBundle resourceBundle = ScreenHandler.getResourceBundle();


    public void handleUpdateUserButton(ActionEvent actionEvent) {
        if (validate()) {
            String id = idTF.getText();
            String firstName = firstNameTF.getText();
            String lastName = lastNameTF.getText();
            String email = emailTF.getText();
            String password = passwordTF.getText();
            String phoneNumber = phoneNumberTF.getText();
            User newUser = new User(Integer.parseInt(id), firstName, lastName, email, phoneNumber, password);
            User oldUser = hotelManagementSystem.getUserById(Integer.parseInt(id));
            hotelManagementSystem.updateUser(oldUser.getUserId(), newUser);
            displayLabel.setText(resourceBundle.getString("success.label"));
        }
    }

    public void handleReturnToManagerAnalyticsButton(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        ScreenHandler.switchScreens(primaryStage, "ManagerAnalyticsScreen.fxml");
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
        if (password.length() < 6) {
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
}
