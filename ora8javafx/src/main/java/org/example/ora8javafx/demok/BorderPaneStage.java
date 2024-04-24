package org.example.ora8javafx.demok;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BorderPaneStage extends Stage {
  public BorderPaneStage() {
    setTitle("BorderPane");
    BorderPane root = new BorderPane();
    Button top = new Button("Top");
    root.setTop(top);
    BorderPane.setAlignment(top, Pos.CENTER);

    Button right = new Button("Top");
    root.setRight(right);
    BorderPane.setAlignment(right, Pos.CENTER);

    root.setLeft(new Button("Left"));
    BorderPane.setAlignment(top, Pos.CENTER);

    root.setCenter(new Button("Center"));
    BorderPane.setAlignment(top, Pos.CENTER);

    Scene scene = new Scene(root,200,200);
    setScene(scene);
    show();
  }
}
