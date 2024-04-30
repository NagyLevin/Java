package hu.ppke.itk.week8.layouts;

import javafx.geometry.Pos;
import javafx.scene.Group;
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
    Group centerGroup = new Group();
    centerGroup.prefHeight(10000);
    centerGroup.prefWidth(10000);
    Button centerButton1 = new Button("Center1");
    Button centerButton2 = new Button("Center2");

    BorderPane.setAlignment(centerButton1, Pos.CENTER);
    BorderPane.setAlignment(centerButton2, Pos.CENTER_LEFT);
    centerGroup.getChildren().add(centerButton1);
    centerGroup.getChildren().add(centerButton2);

    root.setCenter(centerGroup);
    Scene scene = new Scene(root,200,200);
    setScene(scene);
    show();
  }
}
