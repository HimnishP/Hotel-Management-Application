package com.hotelmanagementapplication.controller.l10n_i18n;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ScreenHandler {
    /**
     * Switches screens by loading the specified FXML file and setting the stage title.
     *
     * @param primaryStage The primary stage to set the new scene.
     * @param fxmlFileName The name of the FXML file to load (without the path).
     * @throws IOException If there is an error loading the FXML file.
     */
    public static void switchScreens(Stage primaryStage, String fxmlFileName) throws IOException {
        ResourceBundle resourceBundle = ScreenHandler.getResourceBundle();
        FXMLLoader fxmlLoader = new FXMLLoader(ScreenHandler.class.getResource("/com/hotelmanagementapplication/view/" + fxmlFileName), resourceBundle);
        Parent root = fxmlLoader.load();
        String windowTitle = resourceBundle.getString("title.label_main");
        primaryStage.setTitle(windowTitle);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Method will retrieve the current ResourceBundle
     *
     * @return ResourceBundle
     */
    public static ResourceBundle getResourceBundle() {
        Locale locale = LocaleSingleton.getInstance().getCurrentLocale();
        String baseName = "messages." + locale.getLanguage() + "_" + locale.getCountry().toLowerCase();
        return ResourceBundle.getBundle(baseName, locale);
    }
}
