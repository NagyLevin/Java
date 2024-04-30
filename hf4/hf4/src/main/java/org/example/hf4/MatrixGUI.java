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
import javafx.scene.paint.Color;
import javafx.stage.Stage;



import java.io.IOException;

public class MatrixGUI extends Application {

    private final int XX = 900;     //golbális méretek
    private final int YY = 600;

    private Matrix matrix1 = new Matrix(3,3);
    private Matrix matrix2 = new Matrix(3,3);
    private Matrix matrixSol = new Matrix(3,3);


    public void setGridText(int i,int j,int value, GridPane GP){

        TextField tx = new TextField(""+value);
       // (TextField) GP.getChildren().get(i * GP.getColumnCount() + j) = tx;
        GP.add(tx,i,j);


    }


    public int[][] getGridText(GridPane GP) throws Exception {

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


    public void fillmatrix(GridPane GP,boolean iseditable){

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                TextField tf = new TextField("10");//+i+j);

                if(iseditable == false){
                    tf.setEditable(false); //ne lehessen beleirni
                }


                GP.add(tf,j,i);


            }
        }


    }
    public void events(Scene sc){
        sc.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {    //egyszerübb kilépés, lásd flugigraphics
                Platform.exit();

            }

        });
    }

    public void UpdateSolMatrix(int i, int j, int value){
        matrixSol.matrixstore(i,j,value);

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

        fillmatrix(InnerGMatrix1,true);

        GridPane InnerGMatrix2 = new GridPane();
        InnerGMatrix2.setHgap(3);
        InnerGMatrix2.setVgap(3);

        fillmatrix(InnerGMatrix2,true);

        GridPane InnerGMatrix3 = new GridPane();    //eredmánymátrix
        InnerGMatrix3.setHgap(3);
        InnerGMatrix3.setVgap(3);

        fillmatrix(InnerGMatrix3,false);


        Button Calcgomb = new Button("Calculate");

        outerGrid.add(InnerGMatrix1,0,1);
        outerGrid.add(Calcgomb,0,0);
        outerGrid.add(InnerGMatrix2,1,0);
        outerGrid.add(InnerGMatrix3,1,1);
        outerGrid.setStyle("-fx-background-color: rgb(200, 200, 255);"); //szin kékre



        //TextField tx = new TextField("aaa");//valahogy igy kellene informaciot kinyerni
        //System.out.println(tx.getCharacters());







        Scene scene = new Scene(outerGrid);

        events(scene);
        Calcgomb.setOnAction(event -> {


            try {
                new Thread(new MatrixMulti(matrix1,matrix2)).start();


                matrix1.replacematrix(getGridText(InnerGMatrix1));
                matrix2.replacematrix(getGridText(InnerGMatrix2));
                matrixSol.replacematrix(getGridText(InnerGMatrix3));
                //matrixSol.replacematrix(  MatrixMulti());
                //matrixSol.printM();
                //meg nem szep
                for (int i = 0; i < matrixSol.MrowLength(); i++) {
                    for (int j = 0; j < matrixSol.MColLength(); j++) {
                        setGridText(i,j,matrixSol.matrixshow(i,j),InnerGMatrix3);

                    }

                }
                //meg nem szep



            } catch (Exception e) {

                throw new RuntimeException(e);

            }


        });





        GUI.setScene(scene);
        GUI.setTitle("Matrix Calc");
        GUI.show();

    }




    public static void main(String[] args) {
        launch();
    }
}