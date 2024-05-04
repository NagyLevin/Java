package org.example.hf4;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import javafx.stage.Stage;




import java.util.Random;

public class MatrixGUI extends Application {

    private final int XX = 900;     //golbális méretek
    private final int YY = 600;

    private static final int demosize = 2;
    private static Matrix matrix1 = new Matrix(demosize,demosize);//Matrix(1,1);
    private static Matrix matrix2 = new Matrix(demosize,demosize);//Matrix(1,1);
    public static Matrix matrixSol = new Matrix(demosize,demosize);   //static, hogy minden szálról el lehessen érni
    static GridPane InnerGMatrix3 = new GridPane();    //eredmánymátrix

    public synchronized void setGridText(int i,int j,int value, GridPane GP) {

        TextField tx = new TextField(""+value);
        //System.out.printf("Updated grid");
        GP.add(tx,j,i);



    }


    public synchronized int[][] getGridText(GridPane GP) throws Exception {

        int[][] tenpnatrix = new int[GP.getRowCount()][GP.getColumnCount()];
        String regex = "[0-9]+";

        for (int i = 0; i < GP.getRowCount(); i++) {

            for (int j = 0; j < GP.getColumnCount(); j++) {

                TextField tx = (TextField) GP.getChildren().get(i * GP.getColumnCount() + j); //atalakitom vissza textfielde

                String text = tx.getText();

                if(!text.isEmpty() && text.matches(regex)){
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


    public synchronized void fillmatrix(GridPane GP,boolean iseditable, boolean fillwith0, Matrix matrix){

        //System.out.println("sorhossz: " +matrix.MrowLength());
        //System.out.println("oszlophossz: " +matrix.MColLength());
        int num = 0;

        for (int i = 0; i < matrix.MrowLength(); ++i) {
            for (int j = 0; j < matrix.MColLength(); ++j) {
                if(!fillwith0){
                    num = RandomBetween(0,10);
                }

                TextField tf = new TextField(""+ num );//""+i+j);

                if(!iseditable){
                    tf.setEditable(false); //ne lehessen beleirni
                }


                GP.add(tf,i,j);


            }
        }


    }

    public void expandmatrix(boolean ismo,Matrix matrix, GridPane GP){
        if(!ismo){
            matrix.expand();
            GP.getChildren().clear();

            fillmatrix(GP,true,false,matrix);

        }

        if(ismo){

            matrix.expand();
            GP.getChildren().clear();
            fillmatrix(GP,true,true,matrix);

        }

    }
    public void resetmatrix(boolean ismo,Matrix matrix, GridPane GP){
        if(!ismo){
            matrix.reset();
            GP.getChildren().clear();

            fillmatrix(GP,true,false,matrix);

        }

        if(ismo){

            matrix.reset();
            GP.getChildren().clear();
            fillmatrix(GP,true,true,matrix);

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


    public void events(Scene sc,Button Calcgomb,Button Expand, Button Reset,Button CalcRow,Button CalcFree,GridPane InnerGMatrix1,GridPane InnerGMatrix2,GridPane outergrid,Stage stage){
        Expand.setOnAction(event -> {
            expandmatrix(false, matrix1,InnerGMatrix1);//expand matrix1 in x dirrection


            int indexOfChild = outergrid.getChildren().indexOf(InnerGMatrix1);

            outergrid.getChildren().remove(indexOfChild);

            outergrid.add(InnerGMatrix1,0,1);

            expandmatrix(false, matrix2,InnerGMatrix2);//expand matrix1 in x dirrection


            indexOfChild = outergrid.getChildren().indexOf(InnerGMatrix2);

            outergrid.getChildren().remove(indexOfChild);

            outergrid.add(InnerGMatrix2,1,0);

            //megoldas matrixot is kell
            expandmatrix(true,matrixSol,InnerGMatrix3);
            indexOfChild = outergrid.getChildren().indexOf(InnerGMatrix3);
            outergrid.getChildren().remove(indexOfChild);
            outergrid.add(InnerGMatrix3,1,1);


            ResiceStage(stage,true,true);

        });


        //sc.getWindow().setWidth(sc.getWidth() + 10);
        Reset.setOnAction(event -> {

            resetmatrix(false, matrix1,InnerGMatrix1);//mindent a default 1x1 es formatumra
            resetmatrix(false, matrix2,InnerGMatrix2);
            resetmatrix(true, matrixSol,InnerGMatrix3);


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


    }

    public synchronized void updateSolGui( ) throws InterruptedException {


        for (int i = 0; i < matrixSol.MrowLength(); i++) {
            for (int j = 0; j < matrixSol.MColLength(); j++) {
                setGridText(i,j,matrixSol.matrixshow(i,j),InnerGMatrix3);

                //System.out.println(matrixSol.matrixshow(i,j));
            }

        }

    }


    public void start(Stage GUI) {

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

        fillmatrix(InnerGMatrix1,true,false,matrix1);

        GridPane InnerGMatrix2 = new GridPane();
        InnerGMatrix2.setHgap(3);
        InnerGMatrix2.setVgap(3);

        fillmatrix(InnerGMatrix2,true,false,matrix2);


        InnerGMatrix3.setHgap(3);
        InnerGMatrix3.setVgap(3);

        fillmatrix(InnerGMatrix3,false,true,matrixSol);


        Button Calcgomb = new Button("Calculate");
        Button Expand = new Button("Expand");


        Button Reset = new Button("Reset");
        Button CalcRow = new Button("CalcRow");
        Button CalcFree = new Button("CalcFree");



        GridPane ButtonGrid = new GridPane();
        ButtonGrid.add(Calcgomb,0,0);
        ButtonGrid.add(Expand,1,0);



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

        events(scene,Calcgomb,Expand,Reset,CalcRow,CalcFree,InnerGMatrix1,InnerGMatrix2,outerGrid,GUI);









        GUI.setScene(scene);

        GUI.setTitle("Matrix Calc");
        GUI.show();

    }




    public static void main(String[] args) {
        launch();
    }
}