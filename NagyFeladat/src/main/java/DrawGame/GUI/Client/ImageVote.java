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

    static Label timerLabel = new Label();
    static Vector<Image> AllImages;
    static ImageView displayedimage = new ImageView();
    static FlowPane boxofvoters = new FlowPane(Orientation.VERTICAL);
    static FlowPane boxofvotes = new FlowPane(Orientation.HORIZONTAL);
    static Stage BackToClient;


    /**
     * Itt van az immagevoter konstruktora
     * @param _playercolor itt adom át a player színét, és
     * @param allImagesVote a képeket, amelyekre lehet szavazni
     */
    ImageVote(int[] _playercolor, Vector<Image> allImagesVote){
        playercolor = _playercolor;
        AllImages = allImagesVote;
        //String firstimage = _votepromts[1];
        if(!boxofvoters.getChildren().isEmpty()){
            boxofvoters.getChildren().clear();
        }
        if(!boxofvotes.getChildren().isEmpty()){
            boxofvotes.getChildren().clear();
        }



    }

    /**
     * Itt updatelem a timert
     * @param countdown a timeren fennmaradó idő
     *  Igen tudom hogy ez sok helyen előjön, de nem akartam külön fv-be rakni, mert mi van, ha mas feliratot akarok rakni
     */
    public static void TimerInClient(int countdown) {
        timerLabel.setText("Time left: " + countdown );

    }

    /**
     * Itt kapja meg a class, a szervertől, hogy melyik player mire szavazott
     * @param names egy string a playerek nevei és pontjaival, szebb lenne ha külön egyenként írná ki a neveket és a pointokat
     */
    public static void whoVoted (Vector<String> names){

        boxofvoters.getChildren().clear();

        for (int i = 0; i < names.size(); i++) {
            Font promz = Font.font("ComicSans", FontWeight.BOLD,20);
            Label egynev = new Label(names.get(i));


            egynev.setFont(promz);
            boxofvoters.getChildren().add(egynev);
        }


    }


    /**
     *
     * Egy uj kor kezdetén itt updatelem a gombokat az új promtokra
     * @param promts ezek az uj promtok
     */
    public static void nextVote(String[] promts) {

        System.out.println("Ennyi kep van: " +AllImages.size());


            if(!AllImages.isEmpty()){

                    AllImages.removeFirst();


                if(!AllImages.isEmpty()) {
                    Image kep = AllImages.getFirst();
                    displayedimage.setImage(kep);
                }
            }


        playerPressed = false;

        boxofvoters.getChildren().clear();
        boxofvotes.getChildren().clear();
        for (int i = 1; i < promts.length; i++) {   //egytol indulok, hogy az eslőt ne vegyem figyelembe


            Button button= new Button(promts[i]);
            Font buttonz = Font.font("Comic Sans MS", FontWeight.BOLD,20);
            button.setFont(buttonz);


            button.setOnAction(event -> {

                if(!playerPressed ){
                    button.setStyle("-fx-background-color: rgb(" + playercolor[0] + ", " + playercolor[1] + ", " + playercolor[2] + ");");
                    playersChoice =  button.getText();




                    playerPressed = true;


                }



            });

            boxofvotes.getChildren().add(button);



        }


    }

    /**
     * A játék végén itt írja ki a nyertesek neveit
     * @param winner a nyertes/ek nevei
     */

    public static void EndGame(String winner) {
        boxofvoters.getChildren().clear();
        boxofvotes.getChildren().clear();

        Label Winneris = new Label("The Winner is:");

        Label Winner = new Label(winner);
        Font wfont = Font.font("ComicSans", FontWeight.BOLD,20);
        Winner.setFont(wfont);
        Winneris.setFont(wfont);
        boxofvoters.getChildren().add(Winneris);
        boxofvoters.getChildren().add(Winner);




    }


    /**
     * Itt setupolom a staget
     * @param VoteStage
     */
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
        BackToClient = VoteStage;

        VoteStage.setTitle("Vote");


        VoteStage.show();

    }


    public static void main(String[] args) {

        launch(args);
    }

}
