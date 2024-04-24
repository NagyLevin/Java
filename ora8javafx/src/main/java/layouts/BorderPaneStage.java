package layouts;

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
    root.setRight(new Button("Right"));
    root.setLeft(new Button("Left"));
    root.setCenter(new Button("Center"));
    Scene scene = new Scene(root,200,200);
    setScene(scene);
    show();
  }
}
