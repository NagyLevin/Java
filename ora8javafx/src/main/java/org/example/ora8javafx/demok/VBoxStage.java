package org.example.ora8javafx.demok;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VBoxStage extends Stage {

  public VBoxStage() {
    setTitle("VBox layout");
    VBox root = new VBox(10);
    for (int i = 0; i < 10; ++i) {
      root.getChildren().add(new Button("Buttonladder"));
    }
    Scene scene = new Scene(root);
    setScene(scene);
    show();
  }
}
