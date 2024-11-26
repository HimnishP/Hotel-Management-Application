package com.hotelmanagementapplication.controller;

import com.hotelmanagementapplication.controller.l10n_i18n.ScreenHandler;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class PaymentSelectionScreenController {
    /**
     * Method will switch to the debit payment screen
     *
     * @param actionEvent The event
     * @throws IOException The exception
     */
    public void handleDebitButton(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        ScreenHandler.switchScreens(primaryStage, "DebitPaymentScreen.fxml");
    }

    /**
     * Method will switch to the debit payment screen
     *
     * @param actionEvent The event
     * @throws IOException The exception
     */
    public void handleCreditButton(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        ScreenHandler.switchScreens(primaryStage, "CreditPaymentScreen.fxml");
    }
}
