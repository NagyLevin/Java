package DrawGame.GUI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Random;


public class Lobby extends Application {
    private final int XX = 900;     //golbális méretek
    private final int YY = 600;
    public void events(Canvas canvas,GraphicsContext gc,Scene scene, StackPane sp){   //eventek egy helyre összegyüjtve

        //public void handle(Event event) { inkább majd igy!!!!!!!!!!!!!!!!!!!!!!!

        scene.setOnKeyPressed(event -> {



            if (event.getCode() == KeyCode.ESCAPE) {    //egyszerübb kilépés, lásd flugigraphics
                Platform.exit();

            }
            if (event.getCode() == KeyCode.B) { //b megnyomasara uj jatekos nev ideglenesen

                createbutton(sp,"TesztFelirat");
                //esetleg valahogy ugy rakd, hogy egy gridben kozepen van a cim körülötte meg a nevek


            }

        });


    }

    public int RandomBetween(int min, int max){ //random by https://stackoverflow.com/questions/5271598/java-generate-random-number-between-two-given-values
        Random r = new Random();

        int result = r.nextInt(max-min) + min;

        return result;
    }


    private void createbutton(StackPane sp, String nev) {
        //Button gomb = new Button(nev);
        Label szoveg = new Label(nev);
        szoveg.setRotate(RandomBetween(0,180));
        szoveg.setFont(Font.font("Arial", FontWeight.BOLD,30));
        sp.getChildren().add(szoveg);


        StackPane.setMargin(szoveg, new Insets(0,RandomBetween(-1*XX+50,XX-50) ,RandomBetween(-1*YY+50,YY-50) ,0 )); //le jobbra fel balra


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

        /*
        TextField szovegdoboz = new TextField();
        szovegdoboz.setPromptText("Teszt");
        szovegdoboz.setLayoutX(XX/2);
        szovegdoboz.setLayoutX(YY/2);
        //szovegdoboz.setPrefSize(10, 10);
        //szovegdoboz.setPrefWidth(100);
        szovegdoboz.autosize();

        szovegdoboz.setEditable(false);
        */




        //gomb.setLayoutX(RandomBetween(0,XX));
        //gomb.setLayoutY(RandomBetween(0,YY));



        StackPane SP = new StackPane();  //itt tarolom el a canvasz
        SP.getChildren().add(canvas);//breakom legfelülre
        //SP.getChildren().add(szovegdoboz);//breakom legfelülre




        // Letrehozom az ablakot
        Scene scene = new Scene(SP, XX, YY);



        events(canvas,gc,scene,SP); //eventek futtatasa
        // ablak kirajzolasa ez keruljon a vegere

        Lobby.setScene(scene);
        Lobby.setTitle("GUI test");
        Lobby.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}