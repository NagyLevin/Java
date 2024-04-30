package hu.ppke.itk.week8.layouts;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AnchorPaneLayout extends Stage {

  public AnchorPaneLayout() {
    setTitle("Anchor pane");
    AnchorPane root = new AnchorPane();
    Button topLeft = new Button("Top left");
    Button bottom = new Button("Bottom");
    Button left = new Button("Left");
    Button right = new Button("Right");
    root.getChildren().addAll(topLeft,bottom,left,right);
    AnchorPane.setTopAnchor(topLeft,10d);
    AnchorPane.setLeftAnchor(topLeft,90d);
    AnchorPane.setLeftAnchor(left,20d);
    AnchorPane.setRightAnchor(right,25d);
    AnchorPane.setBottomAnchor(bottom,5d);
    AnchorPane.setLeftAnchor(bottom,15d);
    AnchorPane.setRightAnchor(bottom,15d);
    Scene scene = new Scene(root,250,200);
    setScene(scene);
    show();
  }
}
