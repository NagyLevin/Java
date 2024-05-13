module org.example.fxtester {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.fxtester to javafx.fxml;
    exports org.example.fxtester;
}