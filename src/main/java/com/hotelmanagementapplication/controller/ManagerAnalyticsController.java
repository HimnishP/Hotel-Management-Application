package com.hotelmanagementapplication.controller;

import com.hotelmanagementapplication.controller.l10n_i18n.ScreenHandler;
import com.hotelmanagementapplication.model.system.HotelManagementSystem;
import com.hotelmanagementapplication.model.utildatabase.DatabaseUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class ManagerAnalyticsController {
    @FXML
    private VBox vBoxLayout;
    @FXML
    private ListView listView;
    @FXML
    private Label helloManagerLabel;

    private final HotelManagementSystem hotelManagementSystem = HotelManagementSystem.getInstance();
    private final ResourceBundle resourceBundle = ScreenHandler.getResourceBundle();


    @FXML
    public void initialize() {
        String managerName = hotelManagementSystem.getAllManagers().getLast().getFullName();
        String managerText = resourceBundle.getString("helloManager");
        String formattedManagerText = MessageFormat.format(managerText, managerName);
        helloManagerLabel.setText(formattedManagerText);
    }

    /**
     * Handles the event triggered when the "View Payments" button is clicked.
     * This method retrieves and display payment records in the UI, providing
     * details on recent or historical payments made by users.
     *
     * @param actionEvent the event triggered by the "View Payments" button click
     */
    public void handleViewPaymentsButton(ActionEvent actionEvent) {
    }

    /**
     * Handles the event triggered when the "View All Users" button is clicked.
     * This method retrieves and display a list of all users in the system,
     * including customers and managers.
     *
     * @param actionEvent the event triggered by the "View All Users" button click
     */
    public void handleViewAllUsersButton(ActionEvent actionEvent) {
        listView.getItems().clear();
        listView.getItems().addAll(DatabaseUtil.selectUsers());
        vBoxLayout.setVisible(true);
        listView.setVisible(true);
    }

    /**
     * Handles the event triggered when the "View Room Statuses" button is clicked.
     * This method retrieves and display the current status of all rooms,
     * such as availability, occupancy, or maintenance needs.
     *
     * @param actionEvent the event triggered by the "View Room Statuses" button click
     */
    public void handleViewRoomStatusesButton(ActionEvent actionEvent) {
    }

    /**
     * Handles the event triggered when the "Update Profile" button is clicked.
     * This method initiates the process for updating the user’s profile information,
     * allowing them to modify details such as name, contact information, and preferences.
     *
     * @param actionEvent the event triggered by the "Update Profile" button click
     */
    public void handleUpdateProfileButton(ActionEvent actionEvent) {
    }

    /**
     * Handles the event triggered when the "View Managers" button is clicked.
     * This method retrieves and display a list of managers, including
     * relevant details like roles, contact information, and assigned responsibilities.
     *
     * @param actionEvent the event triggered by the "View Managers" button click
     */
    public void handleViewManagersButton(ActionEvent actionEvent) {
        listView.getItems().clear();
        listView.getItems().addAll(DatabaseUtil.selectManagers());
        vBoxLayout.setVisible(true);
        listView.setVisible(true);
    }

    /**
     * Handles the event triggered when the "View Customers" button is clicked.
     * This method retrieves and display a list of all registered customers,
     * showing details like booking history, profile information, and contact info.
     *
     * @param actionEvent the event triggered by the "View Customers" button click
     */
    public void handleViewCustomersButton(ActionEvent actionEvent) {
        listView.getItems().clear();
        listView.getItems().addAll(DatabaseUtil.selectCustomers());
        vBoxLayout.setVisible(true);
        listView.setVisible(true);
    }
}
