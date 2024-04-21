package DrawGame.GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TextBoxteszt extends Application {


    private String data;
    @Override
    public void start(Stage primaryStage) {
        
        TextField textField = new TextField();
        textField.setPromptText("Teszt");


        VBox root = new VBox();
        root.getChildren().add(textField);


        Scene scene = new Scene(root, 300, 200);


        primaryStage.setScene(scene);


        primaryStage.setTitle("TextBox");


        primaryStage.show();

        textField.setOnAction(event -> {
            data = textField.getText();
            textField.setText("");
            System.out.println("Data collected: " + data); //az eltarolt adat
        });

    }

    public static void main(String[] args) {

        launch(args);
    }
}