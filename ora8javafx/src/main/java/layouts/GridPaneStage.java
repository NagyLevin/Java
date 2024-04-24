package layouts;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GridPaneStage extends Stage {

  public GridPaneStage() {
    setTitle("Grid layout");
    GridPane root = new GridPane();
    for (int i = 0; i < 4; ++i) {
      for (int j = 0; j < 5; ++j) {
        root.add(new Button("ButtonMatrix"+i+j),j,i);
      }
    }
    Scene scene = new Scene(root);
    setScene(scene);
    show();
  }
}
