package DrawGame.GUI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;


public class DrawfuLboard extends Application {


    public void events(Scene scene, Canvas canvas, GraphicsContext gc,Button bfinish,Button BColor1,Button BColor2){
        bfinish.setOnAction(e->{

            canedit = false;
            savadrawing();
            //kep elmentese


        });
        BColor1.setOnAction(event -> {

            strokeColor = Color.rgb(redColor/randcolor1,greenColor/randcolor1,blueColor/randcolor1);


        });
        BColor2.setOnAction(event -> {

            strokeColor = Color.rgb(redColor/randcolor2,greenColor/randcolor2,blueColor/randcolor2);



        });



        canvas.setOnMousePressed(event -> {

            lastX = event.getX();
            lastY = event.getY();

        });


            canvas.setOnMouseDragged(event -> {
                if(canedit == true){
                double x = event.getX();
                double y = event.getY();
                gc.setStroke(strokeColor);  //szinbeallit
                gc.strokeLine(lastX, lastY, x, y);
                lastX = x;
                lastY = y;
                }
        });


        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {    //egyszerübb kilépés, lásd flugigraphics
                Platform.exit();

            }
        });



    }
    public void savadrawing(){

        int playerid = 1;//currentplayer.playerid;
        String playeridS = String.valueOf(playerid) +".png";

        WritableImage image = new WritableImage(XX, YY);
        File file = new File(playeridS); //fajlnev

        BufferedImage bufferedImage = new BufferedImage(XX, YY, BufferedImage.TYPE_INT_ARGB);



        try {

            ImageIO.write(bufferedImage, "png", file);
            System.out.println("Immage saved");



        } catch (IOException e) {
            throw new RuntimeException("Cant savae immage");
        }

    }


    public int RandomBetween(int min, int max){ //random by https://stackoverflow.com/questions/5271598/java-generate-random-number-between-two-given-values
        Random r = new Random();

        int result = r.nextInt(max-min) + min;

        return result;
    }



    @Override
    public void start(Stage BoardStage) throws Exception {

        Canvas canvas = new Canvas(XX, YY);
        GraphicsContext gc;


        gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(10);    //vonal atmero





        AnchorPane root = new AnchorPane(canvas);

        Scene scene = new Scene(root, XX, YY);


        Label promt= new Label(currentplayer.givenpromt);
        promt.setFont(Font.font("Arial", FontWeight.BOLD,30));
        promt.setTextFill(Color.rgb(200,0,0));//currentplayer.getPlayerColoR(),currentplayer.getPlayerColoG(),currentplayer.getPlayerColoB()));

        Button bFinish = new Button("Send In");

        AnchorPane.setTopAnchor(bFinish,  YY/20- bFinish.getWidth());
        AnchorPane.setLeftAnchor(bFinish, XX / 1.2 - bFinish.getWidth() / 2);

        Font fBFinish = Font.font("ComicSans", FontWeight.BOLD,20);
        bFinish.setFont(fBFinish);

        //ColorButtons



        System.out.println(randcolor1);

        Button ColorType1 = new Button("");
        ColorType1.setStyle("-fx-background-color: rgb(" + redColor/randcolor1 + ", " + greenColor/randcolor1 + ", " + blueColor/randcolor1 + ");");
        AnchorPane.setTopAnchor(ColorType1,  YY/18- ColorType1.getWidth());

        randcolor2 = RandomBetween(1,5);
        while (randcolor1 == randcolor2){
            randcolor2 = RandomBetween(1,5);
        }

        System.out.println(randcolor2);

        Button ColorType2 = new Button("");
        ColorType2.setStyle("-fx-background-color: rgb(" + redColor/randcolor2 + ", " + greenColor/randcolor2 + ", " + blueColor/randcolor2 + ");");
        AnchorPane.setTopAnchor(ColorType2,  YY/10- ColorType2.getWidth());

        root.getChildren().add(promt);
        root.getChildren().add(bFinish);
        root.getChildren().add(ColorType1);
        root.getChildren().add(ColorType2);


        events(scene,canvas,gc,bFinish,ColorType1,ColorType2);    //eventek osszegyujtve

        BoardStage.setScene(scene);
        BoardStage.setTitle("Rajztabla");
        BoardStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }

    private final int XX = 900;
    private final int YY = 600;



    private double lastX, lastY;

    Player currentplayer = new Player();

    int redColor = 200;//currentplayer.getPlayerColoR();
    int greenColor = 0;//currentplayer.getPlayerColoG();
    int blueColor = 0;//currentplayer.getPlayerColoG();
    int randcolor1 = RandomBetween(1,5);;
    int randcolor2;



    Color strokeColor = Color.rgb(redColor/randcolor1,greenColor/randcolor1,blueColor/randcolor1); //szin
    int numofcolors = currentplayer.numofcolors;
    boolean canedit = true;

}
