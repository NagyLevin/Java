module org.example.ora10gepterem {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ora10gepterem to javafx.fxml;
    exports org.example.ora10gepterem;
}