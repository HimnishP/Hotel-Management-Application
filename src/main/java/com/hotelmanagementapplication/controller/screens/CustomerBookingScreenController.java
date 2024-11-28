package com.hotelmanagementapplication.controller.screens;

import com.hotelmanagementapplication.controller.currentsession.UserSession;
import com.hotelmanagementapplication.controller.l10n_i18n.ScreenHandler;
import com.hotelmanagementapplication.model.room.Room;
import com.hotelmanagementapplication.model.room.Status;
import com.hotelmanagementapplication.model.system.HotelManagementSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ResourceBundle;

public class CustomerBookingScreenController implements Initializable {
    @FXML
    private Label displayLabel;
    @FXML
    private Label welcomeCustomerLabel;
    @FXML
    private VBox vBox;
    @FXML
    private ListView listView;

    private final HotelManagementSystem hotelManagementSystem = HotelManagementSystem.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resourceBundle = ScreenHandler.getResourceBundle();
        String currentUserName = UserSession.getInstance().getCurrentUser().getFullName();
        String message = resourceBundle.getString("helloManager");
        String formattedMessage = MessageFormat.format(message, currentUserName);
        welcomeCustomerLabel.setText(formattedMessage);
    }

    /**
     * Button will set the vBox and list view visible. It will update the list view by retrieving data from the database for the rooms
     *
     * @param actionEvent button click
     */
    public void handleSingleBedButton(ActionEvent actionEvent) {
        listView.getItems().clear();
        listView.getItems().addAll(hotelManagementSystem.getAllSingleBedRooms());
        vBox.setVisible(true);
        listView.setVisible(true);
    }

    /**
     * Button will set the vBox and list view visible. It will update the list view by retrieving data from the database for the rooms
     *
     * @param actionEvent button click
     */
    public void handleDoubleBedButton(ActionEvent actionEvent) {
        listView.getItems().clear(); // Clear any previous items
        listView.getItems().addAll(hotelManagementSystem.getAllDoubleBedRooms());
        vBox.setVisible(true);
        listView.setVisible(true);
    }

    /**
     * Button will switch screens to the payments screen
     *
     * @param actionEvent button click
     */
    public void handleProcessPayments(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        ScreenHandler.switchScreens(primaryStage, "PaymentSelectionScreen.fxml");
    }

    /**
     * Method will store the room the user has selected
     * Method will also display an error message in a label if user selects a room which is booked
     *
     * @param actionEvent On mouse click (selecting from list view)
     */
    public void handleSelectedRoom(MouseEvent actionEvent) {
        ResourceBundle resourceBundle = ScreenHandler.getResourceBundle();
        Room selectedRoom = (Room) listView.getSelectionModel().getSelectedItem();
        UserSession.getInstance().setCurrentRoom(selectedRoom);
        Status status = selectedRoom.getStatus();
        if (status == Status.BOOKED) {
            displayLabel.setText(resourceBundle.getString("displayErrorLabel"));
        }
    }
}
