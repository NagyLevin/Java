module DrawGame.GUI {
    requires javafx.controls;
    requires javafx.fxml;


    opens DrawGame.GUI to javafx.fxml;
    exports DrawGame.GUI;
}
