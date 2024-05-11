package DrawGame.GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;


public class PointsAfterRound extends Application {

    private final int XX = 400;
    private final int YY = 400;


    @Override
    public void start(Stage PointStage)  {

        GameMaster gameMaster = new GameMaster();




        GridPane PointGrid= new GridPane();

        for (int i = 0; i < gameMaster.PromtsThisRound.length; i++) {
            Label promtok = new Label(gameMaster.PromtsThisRound[i]);

            PointGrid.add(promtok,1,i);


        }

        Scene scene = new Scene(PointGrid, XX,YY);

        PointStage.setScene(scene);
        PointStage.setTitle("Points");
        PointStage.show();

    }


    public static void main(String[] args) {

        launch(args);
    }

}
