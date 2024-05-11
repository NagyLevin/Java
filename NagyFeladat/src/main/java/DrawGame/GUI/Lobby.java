package DrawGame.GUI;

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

import java.util.Random;


public class Lobby extends Application {
    private final int XX = 900;     //golbális méretek
    private final int YY = 600;
    boolean ishosted = false;
    String gamecode;


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

    public void events(Scene scene, StackPane sp,Button host, TextField hostcode){   //eventek egy helyre összegyüjtve

        //public void handle(Event event) { inkább majd igy!!!!!!!!!!!!!!!!!!!!!!!

        host.setOnAction(event -> {
            if(!ishosted){
                ishosted = true;
                hostcode.setEditable(false);
                String code = hostcode.getText();
                if(validatecode(code)){

                    System.out.printf("Hosting started");
                    gamecode = code;
                    //startSession ugy ertem hogy startold majd a hostolást

                }


            }



        });


        scene.setOnKeyPressed(event -> {



            if (event.getCode() == KeyCode.ESCAPE) {    //egyszerübb kilépés, lásd flugigraphics
                Platform.exit();

            }
            if (event.getCode() == KeyCode.B) { //b megnyomasara uj jatekos nev ideglenesen

                createlabel(sp,"TesztFelirat");
                //esetleg valahogy ugy rakd, hogy egy gridben kozepen van a cim körülötte meg a nevek


            }

        });


    }

    public int RandomBetween(int min, int max){ //random by https://stackoverflow.com/questions/5271598/java-generate-random-number-between-two-given-values
        Random r = new Random();

        int result = r.nextInt(max-min) + min;

        return result;
    }


    private void createlabel(StackPane sp, String nev) {
        //Button gomb = new Button(nev);
        Label szoveg = new Label(nev);
        szoveg.setRotate(RandomBetween(-45,45));
        szoveg.setFont(Font.font("Arial", FontWeight.BOLD,30));
        szoveg.setTextFill(Color.rgb(RandomBetween(0,254),RandomBetween(0,254),RandomBetween(0,254)));
        sp.getChildren().add(szoveg);


        //StackPane.setMargin(szoveg, new Insets(0,RandomBetween(-1*XX+100,XX-200) ,RandomBetween(-1*YY+100,YY-100) ,0 )); //le jobbra fel balra
        StackPane.setMargin(szoveg, new Insets(0,RandomBetween(-1*XX+150,XX-150) ,RandomBetween(-1*YY+700,YY-100) ,0 )); //le jobbra fel balra


    }


    @Override
    public void start(Stage Lobby) {
        //Csinálok egy Canvast
        Canvas canvas = new Canvas(XX, YY);

        //Grafikus felület a canvashoz
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //beallitom hogy milyen szinre akarom festeni
        gc.setFill(Color.rgb(200,255,200));

        //kiszinezem vele a kepernyöt teljesen
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        Label cim = new Label("DrawfulL");
        cim.setFont(Font.font("ComicSans", FontWeight.BOLD,100));
        cim.setTextFill(Color.rgb(200,0,0));

        TextField gamecode = new TextField();
        gamecode.setMaxWidth(XX/5);

        Button startsessionbutton = new Button("HOST");



        //Font startgombfont = Font.font("ComicSans", FontWeight.BOLD,50);    //nezz utana hogy van e comicsans ///TO DO
        //Button StartGomb = new Button("Start"); // inkább egy kliensnél legyen ez a gomb
        //StartGomb.setFont(startgombfont);



        StackPane SP = new StackPane();  //itt tarolom el a canvasz
        SP.getChildren().add(canvas);//breakom legfelülre
        SP.getChildren().add(cim);
        //SP.getChildren().add(StartGomb);
        SP.getChildren().add(gamecode);
        SP.getChildren().add(startsessionbutton);

        StackPane.setMargin(cim, new Insets(0,0 ,-1*YY*0.3 ,0 )); //közepre be a cimet
        //StackPane.setMargin(StartGomb, new Insets(0,0 ,-1*YY*0.7 ,0 )); //cim ala a gombot
        StackPane.setAlignment(gamecode,Pos.TOP_LEFT);
        StackPane.setAlignment(startsessionbutton,Pos.TOP_LEFT);
        StackPane.setMargin(startsessionbutton, new Insets(30,0 ,0,0 ));





        // Scene az ablakok tartalma
        Scene scene = new Scene(SP, XX, YY);



        events(scene,SP,startsessionbutton,gamecode); //eventek futtatasa
        // ablak kirajzolasa ez keruljon a vegere

        Lobby.setScene(scene);
        Lobby.setTitle("LOBBY");
        Lobby.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}