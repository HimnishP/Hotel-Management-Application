package com.hotelmanagementapplication.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenHandler {
    /**
     * Switches screens by loading the specified FXML file and setting the stage title.
     *
     * @param primaryStage The primary stage to set the new scene.
     * @param fxmlFileName The name of the FXML file to load (without the path).
     * @param title        The title of the stage.
     * @throws IOException If there is an error loading the FXML file.
     */
    public static void switchScreens(Stage primaryStage, String fxmlFileName, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ScreenHandler.class.getResource("/com/hotelmanagementapplication/view/" + fxmlFileName));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
