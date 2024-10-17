package com.hotelmanagementapplication.guis;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerLoginScreen {
    /**
     * This method will load the login screen for manager
     *
     * @param stage in which it will be loaded
     * @throws IOException Exception handling
     */
    public static void launchManagerLoginScreen(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ManagerLoginScreen.class.getResource("/com/hotelmanagementapplication/view/ManagerLoginScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hotel Management Application - Manager Login");
        stage.setScene(scene);
        stage.show();
    }

}