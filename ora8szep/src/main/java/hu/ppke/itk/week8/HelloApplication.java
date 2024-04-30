package hu.ppke.itk.week8;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FlowPane root = new FlowPane(Orientation.VERTICAL);
        Scene scene = new Scene(root, 320, 240);

        Button myButton = new Button("Click here");
        myButton.setPrefWidth(200);
        Label myLabel = new Label("This is some text");
//        myLabel.setLayoutX(100);
//        myLabel.setLayoutY(0);

        root.getChildren().add(myButton);
        root.getChildren().add(myLabel);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}