package org.example.hf4;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



import java.io.IOException;
import java.util.Random;

public class MatrixGUI extends Application {

    private final int XX = 900;     //golbális méretek
    private final int YY = 600;

    private static final int demosize = 2;
    private Matrix matrix1 = new Matrix(demosize,demosize);//Matrix(1,1);
    private Matrix matrix2 = new Matrix(demosize,demosize);//Matrix(1,1);
    public static Matrix matrixSol = new Matrix(demosize,demosize);   //static, hogy minden szálról el lehessen érni
    static GridPane InnerGMatrix3 = new GridPane();    //eredmánymátrix

    public synchronized void setGridText(int i,int j,int value, GridPane GP) throws InterruptedException {

        TextField tx = new TextField(""+value);
        //System.out.printf("Updated grid");
        GP.add(tx,j,i);



    }


    public synchronized int[][] getGridText(GridPane GP) throws Exception {

        int tenpnatrix[][] = new int[GP.getRowCount()][GP.getColumnCount()];
        String regex = "[0-9]+";

        for (int i = 0; i < GP.getRowCount(); i++) {

            for (int j = 0; j < GP.getColumnCount(); j++) {

                TextField tx = (TextField) GP.getChildren().get(i * GP.getColumnCount() + j); //atalakitom vissza textfielde

                String text = tx.getText();

                if(text.length() > 0 && text.matches(regex)){
                    tenpnatrix[i][j] = Integer.parseInt(text);
                }else{
                    throw new Exception("Hibas input a matrixban");
                }

                //System.out.println("sor " + i + ", oszlop " + j + "szoveg: " + text);

            }


        }

        return tenpnatrix;
    }

    public int RandomBetween(int min, int max){
        Random r = new Random();

        int result = r.nextInt(max-min) + min;

        return result;
    }


    public synchronized void fillmatrix(GridPane GP,boolean iseditable, Matrix matrix){

        //System.out.println("sorhossz: " +matrix.MrowLength());
        //System.out.println("oszlophossz: " +matrix.MColLength());


        for (int i = 0; i < matrix.MrowLength(); ++i) {
            for (int j = 0; j < matrix.MColLength(); ++j) {
                TextField tf = new TextField(""+ RandomBetween(0,10) );//+i+j);

                if(iseditable == false){
                    tf.setEditable(false); //ne lehessen beleirni
                }


                GP.add(tf,i,j);


            }
        }


    }

    public void expandmatrix(int chase,Matrix matrix, GridPane GP){
        if(chase == 1){
            matrix.expand(true,false);
            GP.getChildren().clear();

            fillmatrix(GP,true,matrix);

        }
        if(chase == 2){
            matrix.expand(false,true);
            GP.getChildren().clear();

            fillmatrix(GP,true,matrix);

        }
        if(chase == 0){
            matrix.reset();
            GP.getChildren().clear();
            fillmatrix(GP,true,matrix);

        }

    }
    public void ResiceStage(Stage stage, boolean w, boolean h){
        if(w){
            stage.setWidth(stage.getWidth()*1.1); //csak azert hogy ne kelljen feltetlen atmeretezni az ablakot
        }
        if(h){
            stage.setHeight(stage.getHeight()*1.2); //csak azert hogy ne kelljen feltetlen atmeretezni az ablakot
        }

    }


    public void events(Scene sc,Button Calcgomb,Button EM1x,Button EM1y,Button EM2x,Button EM2y,Button Reset,Button CalcRow,Button CalcFree,GridPane InnerGMatrix1,GridPane InnerGMatrix2,GridPane outergrid,Stage stage){
        EM1x.setOnAction(event -> {
            expandmatrix(1, matrix1,InnerGMatrix1);//expand matrix1 in x dirrection


            int indexOfChild = outergrid.getChildren().indexOf(InnerGMatrix1);

            outergrid.getChildren().remove(indexOfChild);

            outergrid.add(InnerGMatrix1,0,1);
            ResiceStage(stage,true,false);

        });
        EM1y.setOnAction(event -> {
            expandmatrix(2, matrix1,InnerGMatrix1);//expand matrix1 in x dirrection


            int indexOfChild = outergrid.getChildren().indexOf(InnerGMatrix1);

            outergrid.getChildren().remove(indexOfChild);

            outergrid.add(InnerGMatrix1,0,1);

            //megoldas matrixot is kell
            expandmatrix(2,matrixSol,InnerGMatrix3);
            int indexOfChildSol = outergrid.getChildren().indexOf(InnerGMatrix3);
            outergrid.getChildren().remove(indexOfChildSol);
            outergrid.add(InnerGMatrix3,1,1);

            ResiceStage(stage,false,true);

        });
        EM2x.setOnAction(event -> {
            expandmatrix(1, matrix2,InnerGMatrix2);//expand matrix1 in x dirrection


            int indexOfChild = outergrid.getChildren().indexOf(InnerGMatrix2);

            outergrid.getChildren().remove(indexOfChild);

            outergrid.add(InnerGMatrix2,1,0);

            //megoldas matrixot is kell
            expandmatrix(1,matrixSol,InnerGMatrix3);
            int indexOfChildSol = outergrid.getChildren().indexOf(InnerGMatrix3);
            outergrid.getChildren().remove(indexOfChildSol);
            outergrid.add(InnerGMatrix3,1,1);

            ResiceStage(stage,true,false);
        });
        EM2y.setOnAction(event -> {
            expandmatrix(2, matrix2,InnerGMatrix2);//expand matrix1 in x dirrection


            int indexOfChild = outergrid.getChildren().indexOf(InnerGMatrix2);

            outergrid.getChildren().remove(indexOfChild);

            outergrid.add(InnerGMatrix2,1,0);


            ResiceStage(stage,false,true);
        });
        //sc.getWindow().setWidth(sc.getWidth() + 10);
        Reset.setOnAction(event -> {

            expandmatrix(0, matrix1,InnerGMatrix1);//expand matrix1 in x dirrection
            expandmatrix(0, matrix2,InnerGMatrix2);//expand matrix1 in x dirrection
            expandmatrix(0, matrixSol,InnerGMatrix3);//expand matrix1 in x dirrection


            int indexOfChild = outergrid.getChildren().indexOf(InnerGMatrix1);

            outergrid.getChildren().remove(indexOfChild);

            indexOfChild = outergrid.getChildren().indexOf(InnerGMatrix2);

            outergrid.getChildren().remove(indexOfChild);

            indexOfChild = outergrid.getChildren().indexOf(InnerGMatrix3);

            outergrid.getChildren().remove(indexOfChild);

            outergrid.add(InnerGMatrix1,0,1);
            outergrid.add(InnerGMatrix2,1,0);
            outergrid.add(InnerGMatrix3,1,1);



        });






        sc.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {    //egyszerübb kilépés, lásd flugigraphics
                Platform.exit();

            }

        });




        Calcgomb.setOnAction(event -> handleButtonPressed(InnerGMatrix1,InnerGMatrix2,1)); //ha egy akkor a sorszerint akarunk szamolni
        CalcRow.setOnAction(event -> handleButtonPressed(InnerGMatrix1,InnerGMatrix2,2));
        CalcFree.setOnAction(event -> handleButtonPressed(InnerGMatrix1,InnerGMatrix2,3));

    }

    private void handleButtonPressed(GridPane InnerGMatrix1, GridPane InnerGMatrix2, int chase){



            try {
                matrix1.replacematrix(getGridText(InnerGMatrix1));
                matrix2.replacematrix(getGridText(InnerGMatrix2));
                matrixSol.replacematrix(getGridText(InnerGMatrix3));

                new Thread(new MatrixMulti(matrix1,matrix2,chase)).start();


                //matrixSol.printM();

                //matrixSol.replacematrix(  MatrixMulti());
                //matrixSol.printM();




            } catch (Exception e) {

                throw new RuntimeException(e);

            }





    }


    public synchronized void UpdateSolMatrix(int i, int j, int value){
        //
        matrixSol.matrixstore(j, i, value);

        //System.out.printf("Ez a updatematrixos");
        //matrixSol.printM();
        Platform.runLater(() -> {

            try {
                updateSolGui();


            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });


    }//itt meg jo a behely

    public synchronized void updateSolGui( ) throws InterruptedException {


        for (int i = 0; i < matrixSol.MrowLength(); i++) {
            for (int j = 0; j < matrixSol.MColLength(); j++) {
                setGridText(i,j,matrixSol.matrixshow(j,i),InnerGMatrix3);

                //System.out.println(matrixSol.matrixshow(i,j));
            }

        }

    }


    public void start(Stage GUI) throws Exception {

        //Csinálok egy Canvast
        Canvas canvas = new Canvas(XX, YY);

        //Grafikus felület a canvashoz
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //beallitom hogy milyen szinre akarom festeni
        //gc.setFill(Color.rgb(100,100,255));

        //kiszinezem vele a kepernyöt teljesen
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        StackPane SP = new StackPane();  //itt tarolom el a canvasz
        SP.getChildren().add(canvas);//breakom legfelülre

        //Scene scene = new Scene(SP, XX, YY);


        GridPane outerGrid = new GridPane();
        outerGrid.setHgap(10);
        outerGrid.setVgap(10);


        GridPane InnerGMatrix1 = new GridPane();
        InnerGMatrix1.setHgap(3);
        InnerGMatrix1.setVgap(3);

        fillmatrix(InnerGMatrix1,true,matrix1);

        GridPane InnerGMatrix2 = new GridPane();
        InnerGMatrix2.setHgap(3);
        InnerGMatrix2.setVgap(3);

        fillmatrix(InnerGMatrix2,true,matrix2);


        InnerGMatrix3.setHgap(3);
        InnerGMatrix3.setVgap(3);

        fillmatrix(InnerGMatrix3,false,matrixSol);


        Button Calcgomb = new Button("Calculate");
        Button ExpandM1x = new Button("M1 addrow");
        Button ExpandM1y = new Button("M1 addcol");
        Button ExpandM2x = new Button("M2 addrow");
        Button ExpandM2y = new Button("M2 addcol");
        Button Reset = new Button("Reset");
        Button CalcRow = new Button("CalcRow");
        Button CalcFree = new Button("CalcFree");



        GridPane ButtonGrid = new GridPane();
        ButtonGrid.add(Calcgomb,0,0);
        ButtonGrid.add(ExpandM1x,1,0);
        ButtonGrid.add(ExpandM1y,2,0);
        ButtonGrid.add(ExpandM2x,1,1);
        ButtonGrid.add(ExpandM2y,2,1);
        ButtonGrid.add(Reset,5,0);
        ButtonGrid.add(CalcRow,0,1);
        ButtonGrid.add(CalcFree,0,2);



        outerGrid.add(InnerGMatrix1,0,1);
        outerGrid.add(ButtonGrid,0,0);

        outerGrid.add(InnerGMatrix2,1,0);
        outerGrid.add(InnerGMatrix3,1,1);
        outerGrid.setStyle("-fx-background-color: rgb(200, 200, 255);"); //szin kékre



        //TextField tx = new TextField("aaa");//valahogy igy kellene informaciot kinyerni
        //System.out.println(tx.getCharacters());







        Scene scene = new Scene(outerGrid);

        events(scene,Calcgomb,ExpandM1x,ExpandM1y,ExpandM2x,ExpandM2y,Reset,CalcRow,CalcFree,InnerGMatrix1,InnerGMatrix2,outerGrid,GUI);









        GUI.setScene(scene);

        GUI.setTitle("Matrix Calc");
        GUI.show();

    }




    public static void main(String[] args) {
        launch();
    }
}