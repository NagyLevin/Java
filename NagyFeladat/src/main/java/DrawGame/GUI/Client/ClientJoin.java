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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Random;

public class ClientJoin extends Application {


    private final int XX = 300;
    private final int YY = 400;
    private final Random random = new Random();
    int[] playercolor = {random.nextInt(255),random.nextInt(255),random.nextInt(255)};
    public static boolean playerishost = false;
    public static boolean joined = false;
    static VBox vBox = new VBox();
    static Label timerLabel = new Label();
    static String givenpromt;
    static int numofcolors;

    public static Stage boardStage;


    public static void restart() {
        Platform.runLater(() -> {
            try {
                new ClientJoin().start(boardStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            joined = false;
            playerishost = false;
            timerLabel = new Label("");
        });
    }


    public void newColor() {


        playercolor[0] = random.nextInt(255);
        playercolor[1] = random.nextInt(255);
        playercolor[2] = random.nextInt(255);
        String backgroundColor = String.format("-fx-background-color: rgb(%d, %d, %d)", playercolor[0], playercolor[1],playercolor[2]);


        vBox.setStyle(backgroundColor);
    }

    public void events( Scene scene, TextField UserCode,TextField UserName, Button JoinButton ,Button BnewColor){   //eventek egy helyre összegyüjtve

        //public void handle(Event event) { inkább majd igy!!!!!!!!!!!!!!!!!!!!!!!

        if(!joined){
            BnewColor.setOnAction(event -> newColor());
        }


        scene.setOnKeyPressed(event -> {



            if (event.getCode() == KeyCode.ESCAPE) {    //egyszerübb kilépés, lásd flugigraphics
                Platform.exit();

            }


        });

        JoinButton.setOnAction(event -> {
            String code = UserCode.getText();
            //UserCode.setText("");
            String nev = UserName.getText();

            if(!joined){

                if(nev.isEmpty() ) {
                    nev = "Player";

                }
                if( code.isEmpty()) {

                    code = "ABCD";
                }


                new Thread(new Player(nev,playercolor,code,"127.0.0.1")).start();
                joined = true;


            }


        });


    }

    public static void opentheboard(String _promt, int _numofcolors,int[] _playercolor){

        givenpromt =_promt;
        numofcolors = _numofcolors;

        DrawfuLboard DB = new DrawfuLboard(givenpromt, numofcolors, _playercolor); //start a drawingboard
        System.out.println("sikeres board nyitas");
        try {
            DB.start(boardStage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void createStart(){

        Platform.runLater(() -> {

            System.out.println("playeris host: " +playerishost);



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

    public static void TimerInClient(int countdown){

        timerLabel.setText("Game starts in: " + countdown + " seconds");



    }

    @Override
    public void start(Stage ClientStage) {

        if(!vBox.getChildren().isEmpty()){
            vBox = new VBox();
            //vBox.getChildren().clear();
        }

        vBox.setAlignment(Pos.CENTER);

        Label Drawfullclient = new Label("DrawfulL Client");
        Font startgombfont = Font.font("Comic Sans MS", FontWeight.BOLD,30);    //nezz utana hogy van e comicsans ///TO DO
        Drawfullclient.setFont(startgombfont);
        vBox.getChildren().add(Drawfullclient);

        Label CodeZone = new Label("Enter Code:");
        Font codez = Font.font("Comic Sans MS", FontWeight.BOLD,20);    //nezz utana hogy van e comicsans ///TO DO
        CodeZone.setFont(codez);
        vBox.getChildren().add(CodeZone);

        TextField userCode = new TextField();
        userCode.setMaxWidth(XX/3);
        vBox.getChildren().add(userCode);


        Label EnterName = new Label("Enter Your Name:");
        Font namez = Font.font("Comic Sans MS", FontWeight.BOLD,20);
        EnterName.setFont(namez);
        vBox.getChildren().add(EnterName);

        TextField userName = new TextField();
        userName.setMaxWidth(XX/3);
        vBox.getChildren().add(userName);


        Button BnewColor = new Button("Get a new Color");
        Font buttonz = Font.font("Comic Sans MS", FontWeight.BOLD,20);
        BnewColor.setFont(buttonz);
        vBox.getChildren().add(BnewColor);

        Button ClientJoinButton = new Button("Join");
        ClientJoinButton.setFont(buttonz);
        vBox.getChildren().add(ClientJoinButton);

        System.out.println("isJavaFxThread? Client Joinban" + Platform.isFxApplicationThread()); //meg tudom vele nezni, hogy javafx thread e az adott thread


        timerLabel.setFont(buttonz);
        vBox.getChildren().add(timerLabel);

        String backgroundColor = String.format("-fx-background-color: rgb(%d, %d, %d)", playercolor[0], playercolor[1],playercolor[2]);

        vBox.setStyle(backgroundColor);
        Scene scene = new Scene(vBox, XX, YY);


        boardStage = ClientStage; //azert hogy tudjak ablakot váltani




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