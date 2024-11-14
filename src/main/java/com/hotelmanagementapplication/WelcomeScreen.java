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
        // Create managers
        int managerId1 = DatabaseUtil.insertUser("Alice", "Smith", "alice.smith@example.com", "111-111-1111", "passwordAlice");
        if (managerId1 != -1) {
            DatabaseUtil.insertManager(managerId1);
        }

        int managerId2 = DatabaseUtil.insertUser("Bob", "Brown", "bob.brown@example.com", "222-222-2222", "passwordBob");
        if (managerId2 != -1) {
            DatabaseUtil.insertManager(managerId2);
        }

// Create customers
        int customerId1 = DatabaseUtil.insertUser("Charlie", "Davis", "charlie.davis@example.com", "333-333-3333", "passwordCharlie");
        if (customerId1 != -1) {
            DatabaseUtil.insertCustomer(customerId1);
        }

        int customerId2 = DatabaseUtil.insertUser("Dana", "Evans", "dana.evans@example.com", "444-444-4444", "passwordDana");
        if (customerId2 != -1) {
            DatabaseUtil.insertCustomer(customerId2);
        }

        int customerId3 = DatabaseUtil.insertUser("Evan", "Ford", "evan.ford@example.com", "555-555-5555", "passwordEvan");
        if (customerId3 != -1) {
            DatabaseUtil.insertCustomer(customerId3);
        }

        int customerId4 = DatabaseUtil.insertUser("Fiona", "Garcia", "fiona.garcia@example.com", "666-666-6666", "passwordFiona");
        if (customerId4 != -1) {
            DatabaseUtil.insertCustomer(customerId4);
        }

        int customerId5 = DatabaseUtil.insertUser("George", "Harris", "george.harris@example.com", "777-777-7777", "passwordGeorge");
        if (customerId5 != -1) {
            DatabaseUtil.insertCustomer(customerId5);
        }

        int customerId6 = DatabaseUtil.insertUser("Hannah", "Ivy", "hannah.ivy@example.com", "888-888-8888", "passwordHannah");
        if (customerId6 != -1) {
            DatabaseUtil.insertCustomer(customerId6);
        }

        int customerId7 = DatabaseUtil.insertUser("Ian", "Jones", "ian.jones@example.com", "999-999-9999", "passwordIan");
        if (customerId7 != -1) {
            DatabaseUtil.insertCustomer(customerId7);
        }

        int customerId8 = DatabaseUtil.insertUser("Julia", "King", "julia.king@example.com", "000-000-0000", "passwordJulia");
        if (customerId8 != -1) {
            DatabaseUtil.insertCustomer(customerId8);
        }

//        launch();
    }
}