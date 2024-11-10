package com.hotelmanagementapplication.controller;

import com.hotelmanagementapplication.controller.l10n_i18n.ScreenHandler;
import com.hotelmanagementapplication.model.HotelManagementSystem;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class CustomerBookingScreenController {
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
}
