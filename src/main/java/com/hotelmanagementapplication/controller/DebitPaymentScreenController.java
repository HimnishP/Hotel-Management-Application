package com.hotelmanagementapplication.controller;

import com.hotelmanagementapplication.controller.l10n_i18n.ScreenHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class DebitPaymentScreenController {
    @FXML
    private TextField cardHolderNameTF;
    @FXML
    private TextField expirationDateTF;
    @FXML
    private TextField securityCodeTF;
    @FXML
    private TextField debitCardNumberTF;

    public void validateButtonHandler(ActionEvent actionEvent) {
    }

    /**
     * Method will switch back to the welcome screen
     *
     * @param actionEvent The event
     */
    public void handleReturnToWelcomeScreenButton(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        ScreenHandler.switchScreens(primaryStage, "WelcomeScreen.fxml");
    }
}
