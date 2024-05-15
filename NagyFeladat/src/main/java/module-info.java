module DrawGame.GUI {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    requires org.controlsfx.controls;
    requires com.google.gson;


    opens DrawGame.GUI to javafx.fxml;
    exports DrawGame.GUI;
    exports DrawGame.GUI.Client;
    exports DrawGame.GUI.Server;


}
