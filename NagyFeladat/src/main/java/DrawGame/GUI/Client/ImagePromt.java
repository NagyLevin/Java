package DrawGame.GUI.Client;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ImagePromt extends Application {
    int[] playercolor;
    static Label timerLabel = new Label();

    ImagePromt(int[] _playercolor){
            playercolor = _playercolor;

    }

    public static void TimerInClient(int countdown) {

        timerLabel.setText("Time left: " + countdown );
    }

    @Override
    public void start(Stage VoteStage)  {


        Image image = new Image("file:1.png"); // Töltse be a képet

        VBox vbox = new VBox(20, new ImageView(image)); //belerakja a vboxba
        vbox.setAlignment(Pos.BASELINE_CENTER);

        Label GiveaPromt = new Label("Give a promt for the immage:");
        GiveaPromt.setTextFill(Color.rgb(playercolor[0],playercolor[1],playercolor[2]));
        Font promz = Font.font("Comic Sans MS", FontWeight.BOLD,20);
        GiveaPromt.setFont(promz);
        vbox.getChildren().add(GiveaPromt);

        TextField TxPromt = new TextField();

        TxPromt.setMaxWidth(image.getWidth()/3);
        vbox.getChildren().add(TxPromt);

        Button SendInPromt = new Button("SendPromt");
        Font buttonz = Font.font("Comic Sans MS", FontWeight.BOLD,20);
        SendInPromt.setFont(buttonz);
        vbox.getChildren().add(SendInPromt);

        timerLabel.setFont(buttonz);
        timerLabel.setTextFill(Color.rgb(playercolor[0],playercolor[1],playercolor[2]));
        vbox.getChildren().add(timerLabel);

        //System.out.println(image.getHeight());

        Scene scene = new Scene(vbox, image.getWidth(), image.getHeight()*1.4);

        VoteStage.setScene(scene);


        VoteStage.setTitle("Vote");


        VoteStage.show();

    }


    public static void main(String[] args) {

        launch(args);
    }

}
