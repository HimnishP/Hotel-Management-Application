package com.hotelmanagementapplication;

import com.hotelmanagementapplication.controller.l10n_i18n.ScreenHandler;
import com.hotelmanagementapplication.model.databaseutil.DatabaseUtil;
import javafx.application.Application;
import javafx.stage.Stage;

public class WelcomeScreen extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ScreenHandler.switchScreens(primaryStage, "WelcomeScreen.fxml");
    }

    public static void main(String[] args) {
//        DatabaseUtil.createTableUser();
        DatabaseUtil.insertUser("John", "Doe", "john.doe@example.com", "1234567890", "password123");
        DatabaseUtil.insertUser("Jane", "Smith", "jane.smith@example.com", "0987654321", "securepassword");
        DatabaseUtil.insertUser("Emily", "Johnson", "emily.johnson@example.com", "0987654321", "emilysecure");
        DatabaseUtil.insertUser("Michael", "Brown", "michael.brown@example.com", "1122334455", "mikepass");
        DatabaseUtil.insertUser("Patrick", "Jackson", "patrick.jackson@gmail.com", "4504934341", "patjack");
//        launch();

    }
}