package com.hotelmanagementapplication.controller;

import com.hotelmanagementapplication.CustomerLoginScreen;
import com.hotelmanagementapplication.ManagerLoginScreen;
import com.hotelmanagementapplication.WelcomeScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeScreenController {

    public void staffButtonHandler(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        ManagerLoginScreen.launchManagerLoginScreen(stage);
    }

    public void customerButtonHandler(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        CustomerLoginScreen.launchCustomerLoginScreen(stage);
    }
}