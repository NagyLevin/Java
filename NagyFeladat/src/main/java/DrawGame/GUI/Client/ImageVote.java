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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Vector;

public class ImageVote extends Application {

    String playersChoice = "";
    Boolean playerPressed = false;
    int[] playercolor = {200,0,0};
    String[] votepromts ={"A","B","C"} ;
    String firstimage = "Hurdle";


    ImageVote(int[] _playercolor, String[] _votepromts){
        playercolor = _playercolor;
        votepromts = _votepromts;

    }



    @Override
    public void start(Stage VoteStage)  {


        firstimage = "file:" + firstimage + ".png";
        Image image = new Image(firstimage); // Töltse be a képet

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(image.getWidth() / 2);
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
        vbox.getChildren().add(boxofvotes);




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
