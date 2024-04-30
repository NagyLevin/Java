package hu.ppke.itk.week8.fxml.registrationform;

import javafx.scene.control.Alert;
import javafx.stage.Window;

public class AlertHelper {

    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText("Hello world!");
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
