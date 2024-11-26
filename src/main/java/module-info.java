module com.hotelmanagementapplication.hotelmanagementapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.sql;
    requires javafx.graphics;


    exports com.hotelmanagementapplication.controller;
    opens com.hotelmanagementapplication.controller to javafx.fxml;
    exports com.hotelmanagementapplication;
    opens com.hotelmanagementapplication to javafx.fxml;
    exports com.hotelmanagementapplication.controller.l10n_i18n;
    opens com.hotelmanagementapplication.controller.l10n_i18n to javafx.fxml;

}