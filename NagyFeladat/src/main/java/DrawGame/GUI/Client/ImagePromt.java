package DrawGame.GUI.Client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;

public class ImagePromt extends Application {
    int[] playercolor = {200,0,0};
    static Label timerLabel = new Label();
   Vector<String> fakepromts = new Vector<>();
   Vector<String> Images = new Vector<>();



    ImagePromt(int[] _playercolor){
            playercolor = _playercolor;

    }



    public static void TimerInClient(int countdown) {

        timerLabel.setText("Time left: " + countdown );
    }

    @Override
    public void start(Stage VoteStage) throws IOException {


        Path filepath = Paths.get(""); // az eny konyvtáram
        System.out.println(filepath);

        try (DirectoryStream<Path> dirrectory = Files.newDirectoryStream(filepath)) {

            for (Path path : dirrectory) {

                if (Files.isRegularFile(path) && path.toString().endsWith(".png")) {    //akkor ha nem mappa, es png a kiterjesztese
                    System.out.println(path.getFileName());
                    String filenev = String.valueOf(path.getFileName());
                    filenev = "file:"+filenev;
                    Images.add(filenev);
                }

            }

        } catch (IOException error) {
            System.err.println(error);
        }


        Image image = new Image(Images.getFirst()); // Töltse be a képet

        ImageView displayimage =  new ImageView(image);

        VBox vbox = new VBox(20, displayimage); //belerakja a vboxba
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

        events(scene,SendInPromt,TxPromt,displayimage);

        VoteStage.show();

    }

    private void events(Scene scene,Button SendInPromt, TextField TXpromt,ImageView displayedimage) {

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {    //egyszerübb kilépés, lásd flugigraphics
                Platform.exit();

            }
        });
        SendInPromt.setOnAction(event -> {

            fakepromts.add(TXpromt.getText());
            Images.removeFirst();
            if(Images.isEmpty()){
                System.out.println("elkuldom a kepet a playernek classnak");
                SendInPromt.setDisable(true);   //esetleg ird at ilyenre a tobbi gombot
                //System.out.println(fakepromts.getFirst());
                Player.giveFakePromts(fakepromts);


            }else{

                Image kep = new Image(Images.getFirst());
                displayedimage.setImage(kep);

                TXpromt.setText("");

            }



        });


    }


    public static void main(String[] args) {

        launch(args);
    }

}
