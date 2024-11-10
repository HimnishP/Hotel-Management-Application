package com.hotelmanagementapplication.controller;

import com.hotelmanagementapplication.controller.l10n_i18n.ScreenHandler;
import com.hotelmanagementapplication.model.HotelManagementSystem;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class ManagerAnalyticsController {
    @FXML
    private Label helloManagerLabel;

    private final HotelManagementSystem hotelManagementSystem = HotelManagementSystem.getInstance();

    @FXML
    public void initialize() {
        ResourceBundle resourceBundle = ScreenHandler.getResourceBundle();
        String managerName = hotelManagementSystem.getAllManagers().getLast().getFullName();
        String managerText = resourceBundle.getString("helloManager");
        String formattedManagerText = MessageFormat.format(managerText, managerName);
        helloManagerLabel.setText(formattedManagerText);
    }
}
