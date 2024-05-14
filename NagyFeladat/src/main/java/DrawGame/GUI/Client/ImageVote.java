package DrawGame.GUI.Client;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Vector;

public class ImageVote extends Application {

    static String playersChoice = "";
    Boolean playerPressed = false;
    int[] playercolor = {200,0,0};
    String[] votepromts ={"A","B","C"} ;
    String firstimage = "shoe";
    static Label timerLabel = new Label();

    ImageVote(int[] _playercolor, String[] _votepromts){
        playercolor = _playercolor;
        votepromts = _votepromts;
        //String firstimage = _votepromts[1];

    }

    public static void TimerInClient(int countdown) {
        timerLabel.setText("Time left: " + countdown );

    }

    public static void nextVote() {
    }


    @Override
    public void start(Stage VoteStage)  {


        firstimage = "file:" + firstimage + ".png";
        Image image = new Image(firstimage); // Töltse be a képet

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(image.getWidth() / 2);    //lehet nem is mukszik
        imageView.setFitHeight(image.getHeight() / 2);



        VBox vbox = new VBox(0, new ImageView(image)); //belerakja a vboxba nem kell terköz
        vbox.setAlignment(Pos.BASELINE_CENTER);

        Label GiveaPromt = new Label("What promt fits the immage?");
        Font promz = Font.font("ComicSans", FontWeight.BOLD,20);
        GiveaPromt.setFont(promz);
        vbox.getChildren().add(GiveaPromt);




        FlowPane boxofvotes = new FlowPane(Orientation.HORIZONTAL);
        for (int i = 1; i < votepromts.length; i++) {   //egytol indulok, hogy az eslőt ne vegyem figyelembe


            Button button= new Button(votepromts[i]);
            button.setOnAction(event -> {

                if(!playerPressed ){
                    button.setStyle("-fx-background-color: red");
                   playersChoice =  button.getText();


                   playerPressed = true;
                }



            }); //talán késöbb playercolor?

            boxofvotes.getChildren().add(button);

        }
        boxofvotes.setAlignment(Pos.CENTER);

        FlowPane boxofvoters = new FlowPane(Orientation.HORIZONTAL);
        vbox.getChildren().add(boxofvotes);
        vbox.getChildren().add(boxofvoters);

        Font Flable = Font.font("Comic Sans MS", FontWeight.BOLD,20);
        timerLabel.setFont(Flable);
        timerLabel.setAlignment(Pos.CENTER);
        timerLabel.setTextFill(Color.rgb(playercolor[0],playercolor[1],playercolor[2]));
        vbox.getChildren().add(timerLabel);


        vbox.setAlignment(Pos.CENTER);


        //System.out.println(image.getHeight());

        Scene scene = new Scene(vbox, image.getWidth(), image.getHeight()*1.5);

        VoteStage.setScene(scene);


        VoteStage.setTitle("Vote");


        VoteStage.show();

    }


    public static void main(String[] args) {

        launch(args);
    }

}
