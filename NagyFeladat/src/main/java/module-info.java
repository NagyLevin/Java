module com.example.nagyfeladat {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.nagyfeladat to javafx.fxml;
    exports com.example.nagyfeladat;
}