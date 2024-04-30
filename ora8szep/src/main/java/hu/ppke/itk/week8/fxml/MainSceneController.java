package hu.ppke.itk.week8.fxml;

import hu.ppke.itk.week8.fxml.registrationform.AlertHelper;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainSceneController {

    @FXML
    private VBox root;

    @FXML
    private Menu mFile, mJavaFX;


    @FXML
    public void exit(ActionEvent actionEvent) {
        Platform.exit();
    }
    @FXML
    public void changePaneToLoginMenu(ActionEvent actionEvent) throws IOException {
        root.getChildren().set(1,FXMLLoader.load(getClass().getResource("registrationform/registrationForm.fxml")));
    }

    @FXML
    public void changePaneToCSS(ActionEvent actionEvent) throws IOException {
        root.getChildren().set(1,FXMLLoader.load(getClass().getResource("cssexample/CSSDemo.fxml")));
    }

    @FXML
    public void alert(ActionEvent actionEvent) {
        AlertHelper.showAlert(Alert.AlertType.INFORMATION,root.getScene().getWindow(), "You clicked here", "message.....");
    }
}
