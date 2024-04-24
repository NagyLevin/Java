package org.example.ora8javafx.demok;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class FlowPaneStage extends Stage {

  public FlowPaneStage() {
    setTitle("Flow layout (horizontal)");
    FlowPane root = new FlowPane(Orientation.HORIZONTAL);
    for (int i = 0; i < 10; ++i) {
      root.getChildren().add(new Button("ButtonOverFlow"));
    }
    Scene scene = new Scene(root);
    setScene(scene);
    show();
  }
}
