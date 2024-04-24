module java {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ora8javafx to javafx.fxml;
    exports org.example.ora8javafx;
    exports org.example.ora8javafx.demok;
    exports layouts;
    exports FXML;
    exports Examplevent;
    exports EventFilters;
    exports FxThreaddig;


}