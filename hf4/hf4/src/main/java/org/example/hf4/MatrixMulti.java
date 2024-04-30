package org.example.hf4;

import javafx.application.Platform;
import javafx.concurrent.Task;

public class MatrixMulti implements Runnable {

    private Matrix _matrix1;
    private Matrix _matrix2;



    MatrixMulti(Matrix matrix1,Matrix matrix2){
        _matrix1 = matrix1;
        _matrix2 = matrix2;


    }

    @Override
    public void run() {
        //System.out.println("isJavaFxThread?" + Platform.isFxApplicationThread()); //meg tudom vele nezni, hogy javafx thread e az adott thread
        try {
            MatrixMulti();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public void MatrixMulti() throws Exception {

        MatrixGUI Mgui = new MatrixGUI();

        int r1 = _matrix1.MrowLength();
        int c1 = _matrix1.MColLength();
        int r2 = _matrix2.MrowLength();
        int c2 = _matrix2.MColLength();



        if (c1 == r2) {



            for (int i = 0; i < r1; i++) {

                for (int j = 0; j < c2; j++) {
                    int sum = 0;
                    for (int k = 0; k < c1; k++) {


                        sum = sum +  _matrix1.matrixshow(i,k) * _matrix2.matrixshow(k,j);

                    }
                    //System.out.println(sum);
                    /*
                    Platform.runLater(new Runnable() {

                    });
                    */

                    Mgui.UpdateSolMatrix(i,j,sum);
                    Thread.sleep(250); //alvas
                    //mo[i][j] = sum;
                }
            }
        }else {
            throw new Exception("Matrixok dimenzioi nem egyeznek");
        }


       // System.out.println("vegiglefut?");
    }



}
