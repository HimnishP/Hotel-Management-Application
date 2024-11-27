package com.hotelmanagementapplication.controller.screens;

import com.hotelmanagementapplication.controller.l10n_i18n.ScreenHandler;
import com.hotelmanagementapplication.model.system.HotelManagementSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ExistingUserController {
    @FXML
    private TextField emailTB;
    @FXML
    private TextField passwordTB;

    HotelManagementSystem hotelManagementSystem = HotelManagementSystem.getInstance();

    /**
     * Method will check if the user is existing in the database, it will then switch screens accordingly
     *
     * @param actionEvent The Event
     * @throws IOException The Exception
     */
    public void handleValidateButton(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        String userType = hotelManagementSystem.validateExistingCustomer(emailTB.getText(), passwordTB.getText());
        if (userType == null || userType.equals("Unknown")) {
            showAlertBox();
        } else if (userType.equals("Customer")) {
            ScreenHandler.switchScreens(stage, "CustomerBookingScreen.fxml");
        } else if (userType.equals("Manager")) {
            ScreenHandler.switchScreens(stage, "ManagerAnalyticsScreen.fxml");
        }
    }

    /**
     * Method will bring you to welcome screen
     *
     * @param actionEvent The Event
     * @throws IOException The Exception
     */
    public void handleGoBackToWelcomeScreen(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        ScreenHandler.switchScreens(primaryStage, "WelcomeScreen.fxml");
    }

    /**
     * Shows Alert Box
     */
    private static void showAlertBox() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Failed");
        alert.setHeaderText("Invalid Credentials");
        alert.setContentText("The email or password you entered is incorrect. Please try again.");
        alert.showAndWait();
    }
}
