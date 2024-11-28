package com.hotelmanagementapplication.controller.screens;

import com.hotelmanagementapplication.controller.currentsession.UserSession;
import com.hotelmanagementapplication.controller.l10n_i18n.ScreenHandler;
import com.hotelmanagementapplication.model.system.HotelManagementSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ResourceBundle;

public class ManagerAnalyticsController2 implements Initializable {
    @FXML
    private Label helloManagerLabel;
    @FXML
    private VBox vBoxLayout;
    @FXML
    private ListView listView;

    private final HotelManagementSystem hotelManagementSystem = HotelManagementSystem.getInstance();

    /**
     * Method initialized the welcome message with username
     *
     * @param url            Url
     * @param resourceBundle Resource Bundle
     */
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resourceBundle = ScreenHandler.getResourceBundle();
        String currentUserName = UserSession.getInstance().getCurrentUser().getFullName();
        String message = resourceBundle.getString("helloManager");
        String formattedMessage = MessageFormat.format(message, currentUserName);
        helloManagerLabel.setText(formattedMessage);
    }

    /**
     * Method will load all the double rooms
     *
     * @param actionEvent
     */
    public void handleViewDoubleRoom(ActionEvent actionEvent) {
        listView.getItems().clear();
        listView.getItems().addAll(hotelManagementSystem.getAllDoubleBedRooms());
        vBoxLayout.setVisible(true);
        listView.setVisible(true);
    }

    /**
     * Handles the event triggered when the "Switch Screens" button is clicked.
     * This method switches the screen to previous analytics view.
     *
     * @param actionEvent the event triggered by the "Switch Screens" button click
     */
    public void handlePreviousScreen(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        ScreenHandler.switchScreens(primaryStage, "ManagerAnalyticsScreen.fxml");
    }

    /**
     * Handles the event triggered when the "Switch Screens" button is clicked.
     * This method switches the screen to welcome screen
     *
     * @param actionEvent the event triggered by the "Switch Screens" button click
     */
    public void handleGoBackToWelcomeScreen(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        ScreenHandler.switchScreens(primaryStage, "WelcomeScreen.fxml");
    }
}
