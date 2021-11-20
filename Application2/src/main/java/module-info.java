module com.example.inventorymanagementapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.inventorymanagementapplication to javafx.fxml;
    exports com.example.inventorymanagementapplication;
}