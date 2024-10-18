package com.hotelmanagementapplication;

import javafx.application.Application;
import javafx.stage.Stage;

public class WelcomeScreen extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
     ScreenHandler.switchScreens(primaryStage,"WelcomeScreen.fxml","Hotel Management Application - Welcome Screen");
    }

    public static void main(String[] args) {
        launch();
    }
}