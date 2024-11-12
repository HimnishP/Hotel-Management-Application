package com.hotelmanagementapplication.controller;

import com.hotelmanagementapplication.controller.l10n_i18n.ScreenHandler;
import com.hotelmanagementapplication.model.system.HotelManagementSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class CustomerBookingScreenController {
    @FXML
    private Button handleProcessPayments;
    @FXML
    private Button singleBedButton;
    @FXML
    private Button doubleBedButton;
    @FXML
    private Label welcomeCustomerLabel;
    @FXML
    private VBox vBox;
    @FXML
    private ListView listView;

    private final HotelManagementSystem hotelManagementSystem = HotelManagementSystem.getInstance();

    @FXML
    public void initialize() {
        ResourceBundle resourceBundle = ScreenHandler.getResourceBundle();
        String customerName = hotelManagementSystem.getAllCustomers().getLast().getFullName();
        String customerText = resourceBundle.getString("welcomeCustomer");
        String formattedCustomerText = MessageFormat.format(customerText, customerName);
        welcomeCustomerLabel.setText(formattedCustomerText);
    }

    /**
     * Button will set the vBox and list view visible. It will update the list view by retrieving data from the database for the rooms
     *
     * @param actionEvent button click
     */

    public void handleSingleBedButton(ActionEvent actionEvent) {
        //TODO implement DB to retrieve data here
        // Simulated data for testing (replace with DB call later)
        listView.getItems().clear();
        listView.getItems().addAll("Single Room 101", "Single Room 102", "Single Room 103");
        vBox.setVisible(true);
        listView.setVisible(true);
    }

    /**
     * Button will set the vBox and list view visible. It will update the list view by retrieving data from the database for the rooms
     *
     * @param actionEvent button click
     */
    public void handleDoubleBedButton(ActionEvent actionEvent) {
        //TODO implement DB to retrieve data here
        // Simulated data for testing (replace with DB call later)
        listView.getItems().clear(); // Clear any previous items
        listView.getItems().addAll("Double Room 201", "Double Room 202", "Double Room 203");
        vBox.setVisible(true);
        listView.setVisible(true);
    }

    /**
     * Button will switch screens to the payments screen
     *
     * @param actionEvent button click
     */
    public void handleProcessPayments(ActionEvent actionEvent) {
        //TODO switch screens here
    }
}
