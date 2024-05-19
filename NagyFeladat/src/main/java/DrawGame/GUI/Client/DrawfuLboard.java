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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;


public class DrawfuLboard extends Application {


    private final int XX = 900;
    private final int YY = 600;
    static Stage PromtStage;    //itt masolom le a staget, hogy todjak majd indítan egy promtstaget


    private double mouseX, mouseY; //hol van az eger eppen


    static String playersPromt ="TestPromt";
    int redColor;
    int greenColor;
    int blueColor;

    int randcolor1 = RandomBetween(1,5);
    int randcolor2;

    Color strokeColor;
    int numofcolors;
    boolean canedit = true;
    static Label timerLabel= new Label();
    static ByteArrayOutputStream playersDrawing = new ByteArrayOutputStream();

    /**
     * Drawfulboard konstruktora, itt kapja meg a paramétereket, amivel elindul
     * @param _promt
     * @param _numofcolors
     * @param _playercolors
     */
    DrawfuLboard(String _promt, int _numofcolors, int[] _playercolors){


        numofcolors = _numofcolors;
        playersPromt = _promt;
        redColor = _playercolors[0];
        greenColor = _playercolors[1];
        blueColor = _playercolors[2];
        strokeColor = Color.rgb(redColor/randcolor1,greenColor/randcolor1,blueColor/randcolor1);

    }

    /**
     * Itt updatelem a timert
     * @param countdown a timeren fennmaradó idő
     */
    public static void TimerInClient(int countdown){

        timerLabel.setText("Time left: " + countdown );
        //System.out.println("mukszik?");


    }

    /**
     * Itt nyitom meg az uj promt ablakot, ahol a kepekhez adhat a player egy promtot
     * @param palyercolor atadom a playercolor
     * @param bimmages es a képeket, amiket a kliensek rajzoltak(persze csak miután a szerver visszaküldte az összeset)
     */
    public static void openPromting(int[] palyercolor, Vector<Image> bimmages) {

        ImagePromt DB = new ImagePromt(palyercolor,playersPromt,bimmages); //start a drawingboard
        System.out.println("sikeres promting nyitas");
        try {
           DB.start(PromtStage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Itt vannak a eventek összegyüjtve
     * @param scene a scene
     * @param canvas a canvas, amire rajzolok
     * @param gc a rajzoláshoz
     * @param bfinish ezzel a gombbal tudja leadni a kliens a kepet, ha nem nyomja le nem fogja beküldeni a szervernek a képét
     * @param BColor1 Az első szín árnyalat
     * @param BColor2 A második szín árnyalat
     */
    public void events(Scene scene, Canvas canvas, GraphicsContext gc,Button bfinish,Button BColor1,Button BColor2){
        bfinish.setOnAction(e->{

            bfinish.setDisable(true);
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
                if(canedit){
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

    /**
     * Itt mentem el a képet
     * @param canvas
     */
    public void savadrawing(Canvas canvas) {



        WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
        canvas.snapshot(null, writableImage); //kell csinálni róla egy snapshootot


        //File file = new File(playersPromt + ".png"); //fájl neve

        try {

            BufferedImage bufferedImage = WriteableToBufferedImmage(writableImage);
            ByteArrayOutputStream immagebytearry = new ByteArrayOutputStream();

            //ImageIO.write(bufferedImage, "png", file);
            ImageIO.write(bufferedImage, "png", immagebytearry);
            playersDrawing = immagebytearry;

            System.out.println("Kep elmentve");
        } catch (IOException e) {
            System.err.println("Valami hiba lépett fel a kép mentése közben");
        }
    }

    /**
     * Itt akaítom át a writable immaget egy bufferedimmage-re
     * @param image
     * @return és visszaküldöm a savean immagenek
     */
    private BufferedImage WriteableToBufferedImmage(Image image) {

        BufferedImage bufferedImage = new BufferedImage(XX, YY, BufferedImage.TYPE_INT_ARGB);

        int[] pixels = new int[XX * YY];
        image.getPixelReader().getPixels(0, 0, XX, YY, PixelFormat.getIntArgbInstance(), pixels, 0, XX);

        WritableRaster raster = bufferedImage.getRaster();
        raster.setDataElements(0, 0, XX, YY, pixels); //belerakja a bufferedimmagebe a 0,0 tól a pixeleket

        return bufferedImage;
    }

    /**
     * Itt ket szam között tudok számolni egy random számot
     * @param min
     * @param max
     * @return
     */
    public int RandomBetween(int min, int max){
        Random r = new Random();

        int result = r.nextInt(max-min) + min;

        return result;
    }


    /**
     * Itt tortenik a stage setupolása, itt rakom bele a stacpane-be a gombokat, és a feliratokat
     * @param BoardStage
     * @throws Exception
     */
    @Override
    public void start(Stage BoardStage) throws Exception {

        Canvas canvas = new Canvas(XX, YY);
        savadrawing(canvas); //ures lap elmentese, ha nem ad le semmit, akkor ezt adja le
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
