package hu.ppke.itk.week8.threading;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI extends Application {
  @Override
  public void start(Stage primaryStage) throws Exception {
    Button button = new Button("Calculate!");
    button.setFont(Font.font(20));
//    button.setOnAction(new EventHandler<ActionEvent>() {
//      @Override
//      public void handle(ActionEvent actionEvent) {
//        button.setText("...");
//        Thread calc = new Thread(new PiCalculation2(button));
//        calc.setDaemon(true);
//        calc.start();
//      }
//    });
    button.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        button.setText("....");
        new Thread(new PiTask(button)).start();
      }
    });

    primaryStage.setTitle("JavaFX concurrency demo");
    primaryStage.setScene(new Scene(button));
    primaryStage.setOnCloseRequest(event -> System.exit(0));
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }


}
