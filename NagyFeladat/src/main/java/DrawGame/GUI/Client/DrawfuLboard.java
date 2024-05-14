package DrawGame.GUI.Client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Random;


public class DrawfuLboard extends Application {


    private final int XX = 900;
    private final int YY = 600;
    static Stage PromtStage;


    private double mouseX, mouseY; //hol van az eger eppen


    static String playersPromt ="TestPromt";
    int redColor = 200;//currentplayer.getPlayerColoR();
    int greenColor = 0;//currentplayer.getPlayerColoG();
    int blueColor = 0;//currentplayer.getPlayerColoG();

    int randcolor1 = RandomBetween(1,5);;
    int randcolor2;

    Color strokeColor = Color.rgb(redColor/randcolor1,greenColor/randcolor1,blueColor/randcolor1); //szin
    int numofcolors = 2;
    boolean canedit = true;
    static Label timerLabel= new Label();


    DrawfuLboard(String _promt, int _numofcolors, int[] _playercolors){

        numofcolors = _numofcolors;
        playersPromt = _promt;
        redColor = _playercolors[0];
        greenColor = _playercolors[1];
        blueColor = _playercolors[2];
        strokeColor = Color.rgb(redColor/randcolor1,greenColor/randcolor1,blueColor/randcolor1);

    }
    public static void TimerInClient(int countdown){

        timerLabel.setText("Time left: " + countdown );
        //System.out.println("mukszik?");


    }

    public static void openPromting(int[] palyercolor) {

        ImagePromt DB = new ImagePromt(palyercolor,playersPromt); //start a drawingboard
        System.out.println("sikeres voteinditas nyitas");
        try {
           DB.start(PromtStage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public void events(Scene scene, Canvas canvas, GraphicsContext gc,Button bfinish,Button BColor1,Button BColor2){
        bfinish.setOnAction(e->{

            canedit = false;
            savadrawing(canvas);
            //kep elmentese


        });
        BColor1.setOnAction(event -> {

            strokeColor = Color.rgb(redColor/randcolor1,greenColor/randcolor1,blueColor/randcolor1);


        });
        BColor2.setOnAction(event -> {

            strokeColor = Color.rgb(redColor/randcolor2,greenColor/randcolor2,blueColor/randcolor2);



        });



        canvas.setOnMousePressed(event -> {

            mouseX = event.getX();
            mouseY = event.getY();

        });


            canvas.setOnMouseDragged(event -> {
                if(canedit == true){
                double x = event.getX();
                double y = event.getY();
                gc.setStroke(strokeColor);  //szinbeallit
                gc.strokeLine(mouseX, mouseY, x, y);
                    mouseX = x;
                    mouseY = y;
                }
        });


        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {    //egyszerübb kilépés, lásd flugigraphics
                Platform.exit();

            }
        });



    }
    public void savadrawing(Canvas canvas) {



        WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
        canvas.snapshot(null, writableImage); //kell csinálni róla egy snapshootot


        File file = new File(playersPromt + ".png"); //fájl neve

        try {

            BufferedImage bufferedImage = WriteableToBufferedImmage(writableImage);
            ImageIO.write(bufferedImage, "png", file);
            System.out.println("Kep elmentve");
        } catch (IOException e) {
            System.err.println("Valami hiba lépett fel a kép mentése közben");
        }
    }

    private BufferedImage WriteableToBufferedImmage(Image image) {

        BufferedImage bufferedImage = new BufferedImage(XX, YY, BufferedImage.TYPE_INT_ARGB);

        int[] pixels = new int[XX * YY];
        image.getPixelReader().getPixels(0, 0, XX, YY, PixelFormat.getIntArgbInstance(), pixels, 0, XX);

        WritableRaster raster = bufferedImage.getRaster();
        raster.setDataElements(0, 0, XX, YY, pixels);

        return bufferedImage;
    }


    public int RandomBetween(int min, int max){
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





        AnchorPane Ancorroot = new AnchorPane(canvas);

        Scene scene = new Scene(Ancorroot, XX, YY);


        Label promt= new Label(playersPromt);
        promt.setFont(Font.font("Comic Sans MS", FontWeight.BOLD,30));
        promt.setTextFill(Color.rgb(redColor,greenColor,blueColor));
        Button bFinish = new Button("Send In");
        AnchorPane.setTopAnchor(bFinish,  YY/20- bFinish.getWidth());
        AnchorPane.setLeftAnchor(bFinish, XX / 1.2 - bFinish.getWidth() / 2);
        Font fBFinish = Font.font("Comic Sans MS", FontWeight.BOLD,20);
        bFinish.setFont(fBFinish);

        //ColorButtons



        //System.out.println(randcolor1);

        Button ColorType1 = new Button("");
        ColorType1.setStyle("-fx-background-color: rgb(" + redColor/randcolor1 + ", " + greenColor/randcolor1 + ", " + blueColor/randcolor1 + ");");
        AnchorPane.setTopAnchor(ColorType1,  YY/18- ColorType1.getWidth());

        randcolor2 = RandomBetween(1,5);
        while (randcolor1 == randcolor2){
            randcolor2 = RandomBetween(1,5);
        }

        //System.out.println(randcolor2);

        Button ColorType2 = new Button("");
        ColorType2.setStyle("-fx-background-color: rgb(" + redColor/randcolor2 + ", " + greenColor/randcolor2 + ", " + blueColor/randcolor2 + ");");
        AnchorPane.setTopAnchor(ColorType2,  YY/10- ColorType2.getWidth());

        AnchorPane.setTopAnchor(timerLabel,  YY/8- bFinish.getWidth());
        AnchorPane.setLeftAnchor(timerLabel, XX / 1.2 - bFinish.getWidth() / 2);
        Font timerfont = Font.font("Comic Sans MS", FontWeight.BOLD,20);
        timerLabel.setFont(timerfont);
        timerLabel.setTextFill(Color.rgb(redColor,greenColor,blueColor));

        Ancorroot.getChildren().add(promt);
        Ancorroot.getChildren().add(bFinish);
        Ancorroot.getChildren().add(ColorType1);
        Ancorroot.getChildren().add(ColorType2);
        Ancorroot.getChildren().add(timerLabel);

        PromtStage = BoardStage;

        events(scene,canvas,gc,bFinish,ColorType1,ColorType2);    //eventek osszegyujtve

        BoardStage.setScene(scene);
        BoardStage.setTitle("Rajztabla");
        BoardStage.show();



    }

    public static void main(String[] args) {
        launch(args);
    }



}
