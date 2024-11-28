package com.hotelmanagementapplication.controller.screens;

import com.hotelmanagementapplication.controller.l10n_i18n.ScreenHandler;
import com.hotelmanagementapplication.model.room.DoubleBed;
import com.hotelmanagementapplication.model.room.Room;
import com.hotelmanagementapplication.model.room.SingleBed;
import com.hotelmanagementapplication.model.room.Status;
import com.hotelmanagementapplication.model.system.HotelManagementSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class AddNewRoomScreenController {
    private static boolean isSingleBed = false;
    private static boolean isDoubleBed = false;
    private static boolean isAvailable = false;
    private static boolean isBooked = false;
    @FXML
    private TextField priceTF;
    @FXML
    private Label displayLabel;

    /**
     * This method is called when the "Validate" button is clicked.
     * It validates the room details and adds a new room to the system based on the selected options.
     *
     * @param actionEvent the event triggered by clicking the "Validate" button
     */
    public void handleValidateButton(ActionEvent actionEvent) {
        double price = Double.parseDouble(String.valueOf(priceTF));
        ResourceBundle resourceBundle = ScreenHandler.getResourceBundle();
        String status = "";
        if (isSingleBed) {
            status = "SingleBed";
        } else if (isDoubleBed) {
            status = "DoubleBed";
        }
        Room room = null;
        if (isSingleBed && isAvailable) {
            room = new SingleBed(price, Status.AVAILABLE);
        } else if (isSingleBed && isBooked) {
            room = new SingleBed(price, Status.BOOKED);
        } else if (isDoubleBed && isAvailable) {
            room = new DoubleBed(price, Status.AVAILABLE);
        } else if (isDoubleBed && isBooked) {
            room = new DoubleBed(price, Status.BOOKED);
        }
        HotelManagementSystem.getInstance().addRoom(room, status);
        String successMessage = String.format(resourceBundle.getString("addRoom"), room);
        displayLabel.setText(successMessage);
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

    /**
     * Handles the event triggered when the "Single Bed" option is selected.
     * Sets the room type to Single Bed.
     *
     * @param actionEvent the event triggered by selecting the "Single Bed" option
     */
    public void handleSingleBed(ActionEvent actionEvent) {
        isSingleBed = true;
    }

    /**
     * Handles the event triggered when the "Double Bed" option is selected.
     * Sets the room type to Double Bed.
     *
     * @param actionEvent the event triggered by selecting the "Double Bed" option
     */
    public void handleDoubleBed(ActionEvent actionEvent) {
        isDoubleBed = true;
    }

    /**
     * Handles the event triggered when the "Available" status is selected.
     * Sets the room's availability status to available.
     *
     * @param actionEvent the event triggered by selecting the "Available" status
     */
    public void handleAvailable(ActionEvent actionEvent) {
        isAvailable = true;
    }

    /**
     * Handles the event triggered when the "Booked" status is selected.
     * Sets the room's availability status to booked.
     *
     * @param actionEvent the event triggered by selecting the "Booked" status
     */
    public void handleBooked(ActionEvent actionEvent) {
        isBooked = true;
    }
}
