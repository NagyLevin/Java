package DrawGame.GUI.Client;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Vector;

public class ImageVote extends Application {

    static String playersChoice = "";
    static Boolean playerPressed = false;
    static int[] playercolor = {200,0,0};
    String[] votepromts ={"A","B","C"} ;
    static Label timerLabel = new Label();
    static Vector<Image> AllImages;
    static ImageView displayedimage = new ImageView();
    static FlowPane boxofvoters = new FlowPane(Orientation.HORIZONTAL);
    static FlowPane boxofpoints = new FlowPane(Orientation.HORIZONTAL);
    static FlowPane boxofvotes = new FlowPane(Orientation.HORIZONTAL);

    ImageVote(int[] _playercolor, Vector<Image> allImagesVote){
        playercolor = _playercolor;
        AllImages = allImagesVote;
        //String firstimage = _votepromts[1];

    }

    public static void TimerInClient(int countdown) {
        timerLabel.setText("Time left: " + countdown );

    }
    public static void whoVoted (Vector<String> names, Vector<String> pontok){

        for (int i = 0; i < names.size(); i++) {
            Label txnames = (Label) boxofvoters.getChildren().get(i);
            Label txpoints = (Label) boxofpoints.getChildren().get(i);

            System.out.println("Nev a vote oldalon: " + names.get(i));

            txnames.setText(names.get(i));
            txpoints.setText(pontok.get(i));

        }


    }

    public static void nextVote(String[] promts, Vector<Image> allImagesVote) {
        if(!AllImages.isEmpty()){
            AllImages.removeFirst();
            Image kep = AllImages.getFirst();
            displayedimage.setImage(kep);

        }
        playerPressed = false;
        boxofpoints.getChildren().clear();
        boxofvoters.getChildren().clear();
        boxofvotes.getChildren().clear();
        for (int i = 1; i < promts.length; i++) {   //egytol indulok, hogy az eslőt ne vegyem figyelembe


            Button button= new Button(promts[i]);
            button.setOnAction(event -> {

                if(!playerPressed ){
                    button.setStyle("-fx-background-color: rgb(" + playercolor[0] + ", " + playercolor[1] + ", " + playercolor[2] + ");");
                    playersChoice =  button.getText();




                    playerPressed = true;


                }



            }); //talán késöbb playercolor?

            boxofvotes.getChildren().add(button);


            Font promz = Font.font("ComicSans", FontWeight.BOLD,20);
            Label egynev = new Label("");
            Label pont = new Label("");

            egynev.setFont(promz);
            pont.setFont(promz);


            boxofvoters.getChildren().add(egynev);
            boxofpoints.getChildren().add(pont);








        }


    }


    @Override
    public void start(Stage VoteStage)  {





        Image image = AllImages.getFirst();


        VBox vbox = new VBox(); //belerakja a vboxba nem kell terköz
        vbox.setAlignment(Pos.BASELINE_CENTER);
        vbox.getChildren().add(displayedimage);

        Label GiveaPromt = new Label("What promt fits the immage?");
        Font promz = Font.font("ComicSans", FontWeight.BOLD,20);
        GiveaPromt.setFont(promz);
        vbox.getChildren().add(GiveaPromt);






        boxofvotes.setAlignment(Pos.CENTER);

        boxofvoters.setAlignment(Pos.CENTER);
        boxofvoters.setHgap(10);
        boxofpoints.setAlignment(Pos.CENTER);
        boxofpoints.setHgap(10);

        vbox.getChildren().add(boxofvotes);
        vbox.getChildren().add(boxofvoters);
        vbox.getChildren().add(boxofpoints);


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
