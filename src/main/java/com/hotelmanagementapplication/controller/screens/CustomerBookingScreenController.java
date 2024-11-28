package com.hotelmanagementapplication.controller.screens;

import com.hotelmanagementapplication.controller.l10n_i18n.ScreenHandler;
import com.hotelmanagementapplication.model.system.HotelManagementSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerBookingScreenController {
    @FXML
    private VBox vBox;
    @FXML
    private ListView listView;

    private final HotelManagementSystem hotelManagementSystem = HotelManagementSystem.getInstance();
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
}
