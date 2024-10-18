package com.hotelmanagementapplication.controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class WelcomeScreenController {
    public RadioButton englishRadioButton;
    public RadioButton frenchRadioButton;

    /**
     * Once the button is clicked it will load the manager login screen
     *
     * @param actionEvent Event
     * @throws IOException Exception
     */
    public void staffButtonHandler(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        ScreenHandler.switchScreens(primaryStage, "ManagerLoginScreen.fxml");
    }

    /**
     * Once the button is clicked it will load the manager login screen
     *
     * @param event Event
     * @throws IOException Exception
     */
    public void customerButtonHandler(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ScreenHandler.switchScreens(primaryStage, "CustomerLoginScreen.fxml");
    }

    /**
     * Handle English radio button selection and switch to English locale.
     *
     * @param actionEvent Event
     */
    public void handleEnglishRadioButton(ActionEvent actionEvent) {
        LocaleSingleton.getInstance().setCurrentLocale(Locale.of("en", "US"));
        updateLocale();
    }

    /**
     * Handle French radio button selection and switch to French locale.
     *
     * @param actionEvent Event
     */
    public void handleFrenchRadioButton(ActionEvent actionEvent) {
        LocaleSingleton.getInstance().setCurrentLocale(Locale.of("fr", "CA"));
        updateLocale();
    }

    private void updateLocale() {
        Stage stage = (Stage) englishRadioButton.getScene().getWindow();
        try {
            ScreenHandler.switchScreens(stage, "WelcomeScreen.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
