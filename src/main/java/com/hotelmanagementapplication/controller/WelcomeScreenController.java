package com.hotelmanagementapplication.controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeScreenController {
    /**
     * Once the button is clicked it will load the manager login screen
     *
     * @param actionEvent Event
     * @throws IOException Exception
     */
    public void staffButtonHandler(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        ScreenHandler.switchScreens(primaryStage, "ManagerLoginScreen.fxml", "Hotel Management Application - Manager Login Screen");
    }

    /**
     * Once the button is clicked it will load the manager login screen
     *
     * @param event Event
     * @throws IOException Exception
     */
    public void customerButtonHandler(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ScreenHandler.switchScreens(primaryStage, "CustomerLoginScreen.fxml", "Hotel Management Application - Customer Login Screen");
    }
}