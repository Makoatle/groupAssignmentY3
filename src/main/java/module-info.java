module com.example.demo10 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demo10 to javafx.fxml;
    exports com.example.demo10;
    exports com.example.demo;
    opens com.example.demo to javafx.fxml;
}