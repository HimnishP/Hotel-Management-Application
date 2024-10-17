package com.hotelmanagementapplication.guis;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerAnalyticsScreen {
    /**
     * This method will load the manager's analytics screen
     *
     * @param stage in which it will be loaded
     * @throws IOException Exception handling
     */
    public static void launchManagerAnalyticsScreen(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CustomerLoginScreen.class.getResource("/com/hotelmanagementapplication/view/ManagerAnalyticsScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hotel Management Application - Manager Analytics");
        stage.setScene(scene);
        stage.show();
    }
}
