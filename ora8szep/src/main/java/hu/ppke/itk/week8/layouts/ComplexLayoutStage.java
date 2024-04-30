package hu.ppke.itk.week8.layouts;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class ComplexLayoutStage extends Stage {

  public ComplexLayoutStage() {
    setTitle("Complex layout");
    FlowPane root = new FlowPane();

    GridPane leftPane = new GridPane();
    leftPane.addColumn(0,
        new Button("Pane"),
        new Button("in a"),
        new Button("pane")
                                 );

    BorderPane rightPane = new BorderPane();
    rightPane.setTop(new Button("Layouts"));
    rightPane.setRight(new Button("layouts"));
    rightPane.setLeft(new Button("in"));

    root.getChildren().addAll(leftPane,rightPane);
    Scene scene = new Scene(root,200,150);
    setScene(scene);
    show();
  }
}
