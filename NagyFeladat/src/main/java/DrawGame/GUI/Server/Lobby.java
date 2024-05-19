package DrawGame.GUI.Server;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;


public class Lobby extends Application {
    private static final int XX = 900;     //golbális méretek
    private static final int YY = 600;
    static boolean ishosted = false;
    static String gamecode;
    static int playercount = 0;
    static StackPane SP = new StackPane();  //itt tarolom el a canvasz
    static Button host = new Button();
    static TextField hostcode = new TextField();

    /**
     * Visszaállítja a lobbyt
     */
    public static void reset() {

        Platform.runLater(() -> {
            gamecode = "";
            ishosted = false;
            for (int i = 0; i < playercount; i++) {
                SP.getChildren().remove(SP.getChildren().size() - 1);
            }
            playercount =0;
            hostcode.setEditable(true);
            host.setDisable(false);

        });

    }

    /**
     * Ha klilép egy player akkor törli a tábláról
     */
    public static void playerleft() {
        Platform.runLater(() -> {
            for (int i = 0; i < playercount; i++) {
                SP.getChildren().remove(SP.getChildren().size() - 1);
            }
            for (OnlinePlayer player : Allplayers.players) {
                addPlayerName(player.playername,player.palyercolor);

            }

        });

    }


    /**
     * Ellenörzi a
     * @param code ot, hogy mefelel e a feladat által adott szabványnak
     * @return ha igen akkor trueval tér vissza
     */
    public boolean validatecode(String code){
       code = code.toLowerCase();

        if(code.length() < 4){

            return false;
        }
        for (int i = 0; i < code.length(); i++) {
            char betu = code.charAt(i);
            if(!Character.isLetter(betu)){


                return false;

            }


        }



        return true;
    }

    /**
     * Ide gyüjtöttem össze az event-et itt belátom hogy nincs olyan sok event, de inkább így hagyom, ha mégis kell valami akkor egyszerübb bövíteni
     * @param scene
     */
    public void events(Scene scene){   //eventek egy helyre összegyüjtve


        host.setOnAction(event -> {

            if(!ishosted){


                String code = hostcode.getText();
                if(validatecode(code)){


                    //gamecode = "ABCD";//code;
                    System.out.println(code);
                    hostcode.setEditable(false);
                    host.setDisable(true);
                    new Thread(new hosting(code)).start();
                    System.out.printf("Hosting started");
                    ishosted = true;
                }



            }



        });


        scene.setOnKeyPressed(event -> {



            if (event.getCode() == KeyCode.ESCAPE) {    //egyszerübb kilépés, lásd flugigraphics
                Platform.exit();                        //bennehagytam egyszerübb debugra, de ha éles program lenne usereknek akkor ki kellene venni, hogy ne tudjanak ígyí kilépni

            }


        });


    }

    /**
     * Hozzáad egy
     * @param name nevet a playereknek a
     * @param colors megfelelő színben
     */
    public static synchronized void addPlayerName(String name, int[] colors){

        Platform.runLater(() -> { //azert hogy fx szálat kapja
        createlabel(name,colors);
        playercount = playercount +1;
        });

    }

    /**
     * Random szám
     * @param min és
     * @param max között
     * @return itt kapom vissza a számot
     */
    public static int RandomBetween(int min, int max){
        Random r = new Random();

        int result = r.nextInt(max-min) + min;

        return result;
    }

    /**
     * Itt rakja rá a player nevét a táblára, random helyre és random dölésszöggel rakja rá, szerintem ez illika  rajzolós játék feelingjéhez
     * @param nev a név
     * @param color a szin
     */
    private static void createlabel(String nev, int[] color) {
        //Button gomb = new Button(nev);
        Label szoveg = new Label(nev);
        szoveg.setRotate(RandomBetween(-45,45));
        szoveg.setFont(Font.font("Comic Sans MS", FontWeight.BOLD,40));
        szoveg.setTextFill(Color.rgb(color[0],color[1],color[2]));
        SP.getChildren().add(szoveg);


        //StackPane.setMargin(szoveg, new Insets(0,RandomBetween(-1*XX+100,XX-200) ,RandomBetween(-1*YY+100,YY-100) ,0 )); //le jobbra fel balra
        StackPane.setMargin(szoveg, new Insets(0,RandomBetween(-1*XX+150,XX-150) ,RandomBetween(-1*YY+700,YY-100) ,0 )); //le jobbra fel balra


    }

    /**
     * Itt setupolom a lobbyt, a gombokat,és a szövegeket a helyükre rakom
     * @param Lobby
     */
    @Override
    public void start(Stage Lobby) {




        System.out.println("isJavaFxThread? Lobbyban" + Platform.isFxApplicationThread()); //meg tudom vele nezni, hogy javafx thread e az adott thread
        //hosting();
        //Csinálok egy Canvast
        Canvas canvas = new Canvas(XX, YY);

        //Grafikus felület a canvashoz
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //beallitom hogy milyen szinre akarom festeni
        gc.setFill(Color.rgb(200,255,200));

        //kiszinezem vele a kepernyöt teljesen
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        Label cim = new Label("DrawfulL");
        cim.setFont(Font.font("Comic Sans MS", FontWeight.BOLD,100));
        cim.setTextFill(Color.rgb(200,0,0));

        hostcode = new TextField();
        hostcode.setMaxWidth(XX/5);

        host = new Button("HOST");



        //Font startgombfont = Font.font("ComicSans", FontWeight.BOLD,50);    //nezz utana hogy van e comicsans ///TO DO
        //Button StartGomb = new Button("Start"); // inkább egy kliensnél legyen ez a gomb
        //StartGomb.setFont(startgombfont);




        SP.getChildren().add(canvas);//breakom legfelülre
        SP.getChildren().add(cim);
        //SP.getChildren().add(StartGomb);
        SP.getChildren().add(hostcode);
        SP.getChildren().add(host);

        StackPane.setMargin(cim, new Insets(0,0 ,-1*YY*0.3 ,0 )); //közepre be a cimet
        //StackPane.setMargin(StartGomb, new Insets(0,0 ,-1*YY*0.7 ,0 )); //cim ala a gombot
        StackPane.setAlignment(hostcode,Pos.TOP_LEFT);
        StackPane.setAlignment(host,Pos.TOP_LEFT);
        StackPane.setMargin(host, new Insets(30,0 ,0,0 ));




        // Scene az ablakok tartalma
        Scene scene = new Scene(SP, XX, YY);



        events(scene); //eventek futtatasa
        // ablak kirajzolasa ez keruljon a vegere

        Lobby.setScene(scene);
        Lobby.setTitle("LOBBY");
        Lobby.show();
    }



    public static void main(String[] args) {

        launch(args);
    }
}