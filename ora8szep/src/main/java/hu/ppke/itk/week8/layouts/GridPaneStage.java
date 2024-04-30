package hu.ppke.itk.week8.layouts;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GridPaneStage extends Stage {

  public GridPaneStage() {
    setTitle("Grid layout");
    GridPane root = new GridPane();

    ColumnConstraints columnConstraints = new ColumnConstraints();
    columnConstraints.setPercentWidth(20);
    columnConstraints.setHgrow(Priority.ALWAYS);
    RowConstraints rowConstraints = new RowConstraints();
    rowConstraints.setPercentHeight(25);
    rowConstraints.setVgrow(Priority.ALWAYS);

    for (int i = 0; i < 4; ++i) {
      for (int j = 0; j < 5; ++j) {
        VBox node = new VBox(2);
        Button b = new Button("ButtonMatrix"+i+j);
        Button b2 = new Button("ButtonMatrix"+i+j);
        b.setMaxHeight(100);
        b.setMaxWidth(200);
        b.setPrefHeight(100);
        b2.setPrefWidth(100);
        b2.setMaxHeight(100);
        b2.setMaxWidth(200);
        node.setMaxHeight(200);
        node.setMaxWidth(200);
        node.getChildren().add(b);
        node.getChildren().add(b2);
        root.add(node,j,i);
      }
      root.getRowConstraints().add(rowConstraints);
    }
    for (int i = 0; i < 5; i++){
      root.getColumnConstraints().add(columnConstraints);
    }

    Scene scene = new Scene(root);
    setScene(scene);
    show();
  }
}
