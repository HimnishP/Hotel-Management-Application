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

public class ManagerAnalyticsController {
    @FXML
    private VBox vBoxLayout;
    @FXML
    private ListView listView;

    private final HotelManagementSystem hotelManagementSystem = HotelManagementSystem.getInstance();

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
        listView.getItems().addAll(hotelManagementSystem.getAllUsers());
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
    public void handleUpdateProfileButton(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        ScreenHandler.switchScreens(primaryStage, "UpdateUserScreen.fxml");
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
        listView.getItems().addAll(hotelManagementSystem.getAllManagers());
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
        listView.getItems().addAll(hotelManagementSystem.getAllCustomers());
        vBoxLayout.setVisible(true);
        listView.setVisible(true);
    }

    /**
     * Handles the event triggered when the "View Payment" button is clicked.
     * This method retrieves and display a list of all registered payments,
     * showing details like payment type, id information, and amount.
     *
     * @param actionEvent the event triggered by the "View Payment" button click
     */
    public void handlePaymentsButton(ActionEvent actionEvent) {
        listView.getItems().clear();
        listView.getItems().addAll(hotelManagementSystem.getAllPayments());
        vBoxLayout.setVisible(true);
        listView.setVisible(true);
    }

    /**
     * Handles the event triggered when the "View Debit Card Payment" button is clicked.
     * This method retrieves and display a list of all registered debit card payments,
     * showing details like card number
     *
     * @param actionEvent the event triggered by the "View Debit Payment" button click
     */
    public void handleCreditCardPayments(ActionEvent actionEvent) {
        listView.getItems().clear();
        listView.getItems().addAll(hotelManagementSystem.getAllCreditCardPayments());
        vBoxLayout.setVisible(true);
        listView.setVisible(true);
    }

    /**
     * Handles the event triggered when the "View Credit Card Payment" button is clicked.
     * This method retrieves and display a list of all registered customers,
     * showing details like card number.
     *
     * @param actionEvent the event triggered by the "View Debit card payment" button click
     */
    public void handleViewDebitCardPayments(ActionEvent actionEvent) {
        listView.getItems().clear();
        listView.getItems().addAll(hotelManagementSystem.getAllDebitCardPayments());
        vBoxLayout.setVisible(true);
        listView.setVisible(true);
    }
}
