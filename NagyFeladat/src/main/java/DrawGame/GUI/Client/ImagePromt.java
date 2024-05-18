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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Vector;

public class ImagePromt extends Application {
    int[] playercolor = {200,0,0};
    static Label timerLabel = new Label();
    Vector<String> fakepromts = new Vector<>();
    String playerpromt = "";
    static Stage VoteStage;
    static Vector<Image> Images = new Vector<>();

    ImagePromt(int[] _playercolor, String _playerpromt, Vector<Image> bimmages){
            playercolor = _playercolor;
            playerpromt = _playerpromt +".png";
            Images =bimmages;

    }



    public static void TimerInClient(int countdown) {


        timerLabel.setText("Time left: " + countdown );
    }

    public static void openVoting(int[] _palyercolor, Vector<Image> allImagesVote) {
        ImageVote IV = new ImageVote(_palyercolor,allImagesVote); //start a drawingboard
        System.out.println("sikeres voteinditas nyitas");
        try {
            IV.start(VoteStage);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


    @Override
    public void start(Stage PromtStage) throws IOException {




        Image image = Images.getFirst(); // Töltse be a képet

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

        VoteStage=PromtStage;



        Scene scene = new Scene(vbox, image.getWidth(), image.getHeight()*1.4);


        events(scene,SendInPromt,TxPromt,displayimage);


        PromtStage.setScene(scene);

        PromtStage.setTitle("Promt");



        PromtStage.show();

    }

    private void events(Scene scene,Button SendInPromt, TextField TXpromt,ImageView displayedimage) {

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {    //egyszerübb kilépés, lásd flugigraphics
                Platform.exit();

            }
        });
        SendInPromt.setOnAction(event -> {

            String promtstring = TXpromt.getText();

            if(!promtstring.isEmpty() && !Images.isEmpty()){

                fakepromts.add(promtstring);
                Images.removeFirst();
                if(Images.isEmpty()){
                    System.out.println("elkuldom a kepet a playernek classnak");
                    SendInPromt.setDisable(true);   //esetleg ird at ilyenre a tobbi gombot
                    Player.giveFakePromts(fakepromts);


                }else{

                    Image kep = Images.getFirst();
                    displayedimage.setImage(kep);

                    TXpromt.setText("");

                }
            }



        });


    }


    public static void main(String[] args) {

        launch(args);
    }

}
