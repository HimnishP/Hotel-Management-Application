package com.hotelmanagementapplication.controller;

import com.hotelmanagementapplication.guis.CustomerLoginScreen;
import com.hotelmanagementapplication.guis.ManagerLoginScreen;
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
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        ManagerLoginScreen.launchManagerLoginScreen(stage);
    }

    /**
     * Once the button is clicked it will load the manager login screen
     *
     * @param event Event
     * @throws IOException Exception
     */
    public void customerButtonHandler(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        CustomerLoginScreen.launchCustomerLoginScreen(stage);
    }
}