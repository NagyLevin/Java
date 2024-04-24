package FxThreaddig;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI extends Application {
  @Override
  public void start(Stage primaryStage) throws Exception {
    Button button = new Button("Calculate!");
    button.setFont(Font.font(20));
    //button.setOnAction(new FxThreaddig.PiCalculation(button));
    button.setOnAction(event-> new PiCalculation2(button));


    primaryStage.setTitle("JavaFX concurrency demo");
    primaryStage.setScene(new Scene(button));
    primaryStage.setOnCloseRequest(event -> System.exit(0));
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }


}
