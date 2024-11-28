package com.hotelmanagementapplication;

import com.hotelmanagementapplication.controller.l10n_i18n.ScreenHandler;
import javafx.application.Application;
import javafx.stage.Stage;

public class WelcomeScreen extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ScreenHandler.switchScreens(primaryStage, "WelcomeScreen.fxml");
    }

    public static void main(String[] args) {
        launch();
//        Payment creditCardPayment = new CreditCardPayment(1001, 250.75, "2024-11-27", 12345, "4111111111111111", "John Doe", "12/25", "123");
//
//        // Add Credit Card Payment asynchronously
//        HotelManagementSystem.getInstance().addPayment(creditCardPayment);
//
//        // Example of adding a Debit Card Payment
//        Payment debitCardPayment = new DebitCardPayment(1002, 150.00, "2024-11-27", 67890, "5105105105105100", "Jane Smith", "11/24", "456");
//
//        // Add Debit Card Payment asynchronously
//        HotelManagementSystem.getInstance().addPayment(debitCardPayment);
    }
}
