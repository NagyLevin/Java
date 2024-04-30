package hu.ppke.itk.week8.layouts;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Label root = new Label("Layout demonstration");
    root.setFont(Font.font(42));
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.setOnCloseRequest(event -> Platform.exit());
    primaryStage.setTitle("Layout Demo");

//    new BorderPaneStage();
//    new VBoxStage();
//    new FlowPaneStage();
    new GridPaneStage();
//    new ComplexLayoutStage();
//    new AnchorPaneLayout();

    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
