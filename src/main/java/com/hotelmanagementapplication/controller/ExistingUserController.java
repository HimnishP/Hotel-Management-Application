package com.hotelmanagementapplication.controller;

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
    private static boolean isManger = false;
    private static boolean isCustomer = false;
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
        boolean isValidUser = hotelManagementSystem.validateExistingCustomer(emailTB.getText(), passwordTB.getText());
        if (isValidUser && isCustomer) {
            ScreenHandler.switchScreens(stage, "CustomerBookingScreen.fxml");
        } else if (isValidUser && isManger) {
            ScreenHandler.switchScreens(stage, "ManagerAnalyticsScreen.fxml");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Failed");
            alert.setHeaderText("Invalid Credentials");
            alert.setContentText("The email or password you entered is incorrect. Please try again.");
            alert.showAndWait();
        }
    }

    /**
     * Method will check if user is a manager
     *
     * @param actionEvent The Event
     */
    public void handleManagerRadioButton(ActionEvent actionEvent) {
        isManger = true;
    }

    /**
     * Method will check if user is a customer
     *
     * @param actionEvent The Event
     */
    public void handleCustomerRadioButton(ActionEvent actionEvent) {
        isCustomer = true;
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
}
