module org.example.hf4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.hf4 to javafx.fxml;
    exports org.example.hf4;
}