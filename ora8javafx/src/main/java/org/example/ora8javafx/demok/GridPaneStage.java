package org.example.ora8javafx.demok;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class GridPaneStage extends Stage {

  public GridPaneStage() {
    setTitle("Grid layout");
    GridPane root = new GridPane();

    ColumnConstraints col1 = new ColumnConstraints();
    col1.setPercentWidth(20);
    col1.setHgrow(Priority.ALWAYS);





    for (int i = 0; i < 4; ++i) {
      for (int j = 0; j < 5; ++j) {

        Button btn = new Button("ButtonMatrix"+i+j);

        root.add(btn,j,i);
        btn.setMaxHeight(50);

      }
    }
    Scene scene = new Scene(root);
    setScene(scene);
    show();
  }
}
