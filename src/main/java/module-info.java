module com.hotelmanagementapplication.hotelmanagementapplication {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.hotelmanagementapplication to javafx.fxml;
    exports com.hotelmanagementapplication;
    exports com.hotelmanagementapplication.controller;
    opens com.hotelmanagementapplication.controller to javafx.fxml;
}