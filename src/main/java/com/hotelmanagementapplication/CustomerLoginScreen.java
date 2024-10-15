package com.hotelmanagementapplication;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerLoginScreen {
    /**
     * This method will load the login screen for customers
     *
     * @param stage in which it will be loaded
     * @throws IOException Exception handling
     */
    public static void launchCustomerLoginScreen(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CustomerLoginScreen.class.getResource("view/CustomerLoginScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hotel Management Application - Customer Login");
        stage.setScene(scene);
        stage.show();
    }

}