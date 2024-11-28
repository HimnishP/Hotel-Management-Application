package com.hotelmanagementapplication.controller.screens;

import com.hotelmanagementapplication.controller.l10n_i18n.ScreenHandler;
import com.hotelmanagementapplication.model.room.Room;
import com.hotelmanagementapplication.model.system.HotelManagementSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class RemoveRoomScreenController {
    @FXML
    private TextField roomId;
    @FXML
    private Label displayLabel;

    /**
     * Method will remove room if the room is existing, if not it will display error message
     * @param actionEvent Event
     */
    public void handleValidateButton(ActionEvent actionEvent) {
        Room room = HotelManagementSystem.getInstance().removeRoom(Integer.parseInt(roomId.getText()));
        ResourceBundle resourceBundle = ScreenHandler.getResourceBundle();
        if (room != null) {
            String successMessage = resourceBundle.getString("removeRoom");
            displayLabel.setText(successMessage);
        } else {
            displayLabel.setText(resourceBundle.getString("roomNotFound"));
        }
    }

    /**
     * Handles the event triggered when the "Switch Screens" button is clicked.
     * This method switches the screen to previous analytics view.
     *
     * @param actionEvent the event triggered by the "Switch Screens" button click
     */
    public void handleGoBackToWelcomeScreen(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        ScreenHandler.switchScreens(primaryStage, "ManagerAnalyticsScreen2.fxml");
    }
}
