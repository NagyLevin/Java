module com.example.ora9 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ora9 to javafx.fxml;
    exports com.example.ora9;
}