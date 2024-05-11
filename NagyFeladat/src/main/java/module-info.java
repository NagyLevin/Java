module DrawGame.GUI {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;


    opens DrawGame.GUI to javafx.fxml;
    exports DrawGame.GUI;
}
