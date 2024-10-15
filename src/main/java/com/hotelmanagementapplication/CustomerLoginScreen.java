package com.hotelmanagementapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerLoginScreen {

    public static void launchCustomerLoginScreen(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CustomerLoginScreen.class.getResource("view/CustomerLoginScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hotel Management Application - Customer Login");
        stage.setScene(scene);
        stage.show();
    }

}