package com.hotelmanagementapplication;

import com.hotelmanagementapplication.controller.l10n_i18n.ScreenHandler;
import com.hotelmanagementapplication.model.utildatabase.DatabaseUtil;
import javafx.application.Application;
import javafx.stage.Stage;

public class WelcomeScreen extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ScreenHandler.switchScreens(primaryStage, "WelcomeScreen.fxml");
    }

    public static void main(String[] args) {
        DatabaseUtil.createTableUser();
        DatabaseUtil.createTableManager();
        DatabaseUtil.createTableCustomer();
//        launch();
    }
}