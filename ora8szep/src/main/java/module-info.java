module hu.ppke.itk.week8 {
    requires javafx.controls;
    requires javafx.fxml;


    opens hu.ppke.itk.week8 to javafx.fxml;
    opens hu.ppke.itk.week8.fxml to javafx.fxml;
    opens hu.ppke.itk.week8.fxml.registrationform to javafx.fxml;

    exports hu.ppke.itk.week8;
    exports hu.ppke.itk.week8.layouts;
    exports hu.ppke.itk.week8.fxml;
    exports hu.ppke.itk.week8.fxml.registrationform;
    exports hu.ppke.itk.week8.events;
    exports hu.ppke.itk.week8.example;
    exports hu.ppke.itk.week8.threading;
}