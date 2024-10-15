package com.hotelmanagementapplication;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerLoginScreen {

    public static void launchManagerLoginScreen(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ManagerLoginScreen.class.getResource("view/ManagerLoginScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hotel Management Application - Manager Login");
        stage.setScene(scene);
        stage.show();
    }

}