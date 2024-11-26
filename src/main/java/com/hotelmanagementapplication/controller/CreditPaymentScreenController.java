package com.hotelmanagementapplication.controller;

import com.hotelmanagementapplication.controller.l10n_i18n.ScreenHandler;
import com.hotelmanagementapplication.model.payment.utility.PaymentValidationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CreditPaymentScreenController {
    @FXML
    private TextField creditCardNumberTF;
    @FXML
    private TextField cardHolderNameTF;
    @FXML
    private TextField expirationDateTF;
    @FXML
    private TextField securityCodeTF;

    /**
     * Method will switch back to the welcome screen
     *
     * @param actionEvent The event
     */
    public void handleReturnToWelcomeScreenButton(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        ScreenHandler.switchScreens(primaryStage, "WelcomeScreen.fxml");
    }

    /**
     * Validates the entered credit card details and processes the payment.
     *
     * @param actionEvent The event
     */
    public void validateButtonHandler(ActionEvent actionEvent) {
        String creditCardNumber = creditCardNumberTF.getText();
        String cardHolderName = cardHolderNameTF.getText();
        String expirationDate = expirationDateTF.getText();
        String securityCode = securityCodeTF.getText();
        if (!PaymentValidationUtil.isValidCardNumber(creditCardNumber)) {
            showAlert(Alert.AlertType.ERROR, "Invalid Card Number", "Please enter a valid 16-digit card number.");
            return;
        }
        if (!PaymentValidationUtil.isValidExpirationDate(expirationDate)) {
            showAlert(Alert.AlertType.ERROR, "Invalid Expiration Date", "Please enter a valid expiration date in MM/YY format.");
            return;
        }
        if (!PaymentValidationUtil.isValidSecurityCode(securityCode)) {
            showAlert(Alert.AlertType.ERROR, "Invalid Security Code", "Please enter a valid 3-digit security code.");
            return;
        }
        if (cardHolderName == null || cardHolderName.trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Invalid Cardholder Name", "Please enter the cardholder's name.");
            return;
        }
        showAlert(Alert.AlertType.INFORMATION, "Payment Successful", "Your payment was successfully processed.");
    }

    /**
     * Helper method to display alert messages.
     *
     * @param alertType The type of the alert
     * @param title     The title of the alert
     * @param message   The message to display
     */
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
