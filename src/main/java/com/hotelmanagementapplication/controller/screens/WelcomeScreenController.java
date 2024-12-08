package com.hotelmanagementapplication.controller.screens;

import com.hotelmanagementapplication.controller.l10n_i18n.LocaleSingleton;
import com.hotelmanagementapplication.controller.l10n_i18n.ScreenHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class WelcomeScreenController {
    @FXML
    private RadioButton englishRadioButton;

    private static boolean isExistingUser = false;

    /**
     * Once the button is clicked it will load the manager login screen
     *
     * @param actionEvent Event
     * @throws IOException Exception
     */
    public void staffButtonHandler(ActionEvent actionEvent) throws IOException {
        if (!isExistingUser) {
            Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            ScreenHandler.switchScreens(primaryStage, "ManagerLoginScreen.fxml");
        }
    }

    /**
     * Once the button is clicked it will load the manager login screen
     *
     * @param event Event
     * @throws IOException Exception
     */
    public void customerButtonHandler(ActionEvent event) throws IOException {
        if (!isExistingUser) {
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ScreenHandler.switchScreens(primaryStage, "CustomerLoginScreen.fxml");
        }
    }

    /**
     * Handle English radio button selection and switch to English locale.
     *
     * @param actionEvent Event
     */
    public void handleEnglishRadioButton(ActionEvent actionEvent) throws IOException {
        LocaleSingleton.getInstance().setCurrentLocale(Locale.of("en", "US"));
        updateLocale();
    }

    /**
     * Handle French radio button selection and switch to French locale.
     *
     * @param actionEvent Event
     */
    public void handleFrenchRadioButton(ActionEvent actionEvent) throws IOException {
        LocaleSingleton.getInstance().setCurrentLocale(Locale.of("fr", "CA"));
        updateLocale();
    }

    private void updateLocale() throws IOException {
        Stage stage = (Stage) englishRadioButton.getScene().getWindow();
        ScreenHandler.switchScreens(stage, "WelcomeScreen.fxml");

    }

    /**
     * Handle yes radio button for if user is an existing one
     *
     * @param actionEvent The event
     * @throws IOException The exception
     */
    public void handleYesRadioButton(ActionEvent actionEvent) throws IOException {
        isExistingUser = true;
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        ScreenHandler.switchScreens(primaryStage, "ExistingUser.fxml");
    }

    /**
     * Handle no radio button for if user is an existing one
     *
     * @param actionEvent The event
     */
    public void handleNoRadioButton(ActionEvent actionEvent) {
        isExistingUser = false;
    }
}
