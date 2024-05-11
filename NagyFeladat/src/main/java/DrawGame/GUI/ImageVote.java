package DrawGame.GUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class ImageVote extends Application {

    String playersChoice = "";
    Boolean playerPressed = false;


    @Override
    public void start(Stage VoteStage)  {


        Image image = new Image("file:1.png"); // Töltse be a képet

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(image.getWidth() / 2);
        imageView.setFitHeight(image.getHeight() / 2);



        VBox vbox = new VBox(0, new ImageView(image)); //belerakja a vboxba nem kell terköz
        vbox.setAlignment(Pos.BASELINE_CENTER);

        Label GiveaPromt = new Label("What promt fits the immage?");
        Font promz = Font.font("ComicSans", FontWeight.BOLD,20);
        GiveaPromt.setFont(promz);
        vbox.getChildren().add(GiveaPromt);

        GameMaster GM = new GameMaster();
        GM.testpromtREMOVELATER();

        GridPane GP = new GridPane();
        GP.setHgap(15); //eltolás

        for (int i = 0; i < GM.PromtsThisRound.length; i++) {


            Button button= new Button(GM.PromtsThisRound[i]);
            button.setOnAction(event -> {

                if(!playerPressed ){
                    button.setStyle("-fx-background-color: red");
                   playersChoice =  button.getText();
                   playerPressed = true;
                }



            }); //talán késöbb playercolor?

            GP.add(button,0,i);

        }
        vbox.getChildren().add(GP);
        vbox.setAlignment(Pos.TOP_LEFT);


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
