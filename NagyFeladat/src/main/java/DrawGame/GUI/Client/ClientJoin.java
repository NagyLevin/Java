package DrawGame.GUI.Client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Random;

public class ClientJoin extends Application {


    private final int XX = 300;
    private final int YY = 400;
    private final Random random = new Random();
    int[] playercolor = new int[3];
    public static boolean playerishost = false;
    public boolean joined = false;
    static VBox vBox = new VBox();


    public void newColor() {
        playercolor[0] = random.nextInt(255);
        playercolor[1] = random.nextInt(255);
        playercolor[2] = random.nextInt(255);
        String backgroundColor = String.format("-fx-background-color: rgb(%d, %d, %d);", playercolor[0], playercolor[1],playercolor[2]);


        vBox.setStyle(backgroundColor);
    }

    public void events( Scene scene, TextField UserCode,TextField UserName, Button JoinButton ,Button BnewColor){   //eventek egy helyre összegyüjtve

        //public void handle(Event event) { inkább majd igy!!!!!!!!!!!!!!!!!!!!!!!


        BnewColor.setOnAction(event -> newColor());

        scene.setOnKeyPressed(event -> {



            if (event.getCode() == KeyCode.ESCAPE) {    //egyszerübb kilépés, lásd flugigraphics
                Platform.exit();

            }


        });

        JoinButton.setOnAction(event -> {
            String code = UserCode.getText();
            //UserCode.setText("");
            String nev = UserName.getText();

            if(nev.isEmpty() && code.isEmpty() && !joined){
                //System.out.println("Kod: " + code); //az eltarolt adat
                //System.out.println("Nev: " + nev); //az eltarolt adat
                nev = "tesztplayer";
                code = "ABCD";
                joined = true;

                new Thread(new Player(nev,playercolor,code,"127.0.0.1")).start();



            }


        });


    }

    public static void createStart(){

        Platform.runLater(() -> {

            //System.out.println("playeris host: " +playerishost);
            if(playerishost){


                Button StartButton = new Button("Start");
                Font buttons = Font.font("ComicSans", FontWeight.BOLD,20);
                StartButton.setFont(buttons);

                vBox.getChildren().add(StartButton);

                StartButton.setOnAction(event -> {
                    //System.out.println("mukszik");
                    Player.startgame();

                });


            }
        });


    }


    @Override
    public void start(Stage ClientStage) {

        vBox.setAlignment(Pos.CENTER);

        Label Drawfullclient = new Label("DrawfulL Client");
        Font startgombfont = Font.font("ComicSans", FontWeight.BOLD,30);    //nezz utana hogy van e comicsans ///TO DO
        Drawfullclient.setFont(startgombfont);
        vBox.getChildren().add(Drawfullclient);

        Label CodeZone = new Label("Eneter Code:");
        Font codez = Font.font("ComicSans", FontWeight.BOLD,20);    //nezz utana hogy van e comicsans ///TO DO
        CodeZone.setFont(codez);
        vBox.getChildren().add(CodeZone);

        TextField userCode = new TextField();
        userCode.setMaxWidth(XX/3);
        vBox.getChildren().add(userCode);


        Label EnterName = new Label("Eneter Your Name:");
        Font namez = Font.font("ComicSans", FontWeight.BOLD,20);
        EnterName.setFont(namez);
        vBox.getChildren().add(EnterName);

        TextField userName = new TextField();
        userName.setMaxWidth(XX/3);
        vBox.getChildren().add(userName);


        Button BnewColor = new Button("Get a new Color");
        Font buttonz = Font.font("ComicSans", FontWeight.BOLD,20);
        BnewColor.setFont(buttonz);
        vBox.getChildren().add(BnewColor);

        Button ClientJoinButton = new Button("Join");
        ClientJoinButton.setFont(buttonz);
        vBox.getChildren().add(ClientJoinButton);

        System.out.println("isJavaFxThread? Client Joinban" + Platform.isFxApplicationThread()); //meg tudom vele nezni, hogy javafx thread e az adott thread







        vBox.setStyle("-fx-background-color: rgb(200, 255, 200);"); //esetleg playercolorra?
        Scene scene = new Scene(vBox, XX, YY);

        events(scene,userCode,userName,ClientJoinButton,BnewColor);

        ClientStage.setScene(scene);


        ClientStage.setTitle("ClientJoin");


        ClientStage.show();









    }



    public static void main(String[] args) {

        //minden uj lobbyt egy uj threadként kell elmentenünk egy új playerként

        launch(args);
    }
}