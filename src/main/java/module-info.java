module com.hotelmanagementapplication.hotelmanagementapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    exports com.hotelmanagementapplication.controller;
    opens com.hotelmanagementapplication.controller to javafx.fxml;
    exports com.hotelmanagementapplication;
    opens com.hotelmanagementapplication to javafx.fxml;

}