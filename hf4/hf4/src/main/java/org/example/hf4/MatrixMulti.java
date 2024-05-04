package org.example.hf4;

import javafx.application.Platform;
import javafx.concurrent.Task;

public class MatrixMulti implements Runnable {

    private Matrix _matrix1;
    private Matrix _matrix2;
    private int sleeptime = 500;
    private int _chase = 500;


    MatrixMulti(Matrix matrix1,Matrix matrix2, int chase){
        _matrix1 = matrix1;
        _matrix2 = matrix2;
        _chase = chase;

    }

    @Override
    public void run() {
        //System.out.println("isJavaFxThread?" + Platform.isFxApplicationThread()); //meg tudom vele nezni, hogy javafx thread e az adott thread
        try {
            if(_chase == 1){
                MatrixSor();
            }
            if(_chase == 2){
                MatrixOszlop();
            }
            if(_chase == 3){
                MatrixFreeStyle();
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public void MatrixSor() throws Exception {

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
                    Thread.sleep(sleeptime); //alvas
                    //mo[i][j] = sum;
                }
            }
        }else {
            throw new Exception("Matrixok dimenzioi nem egyeznek");
        }


       // System.out.println("vegiglefut?");
    }

    public void MatrixOszlop() throws Exception {

        MatrixGUI Mgui = new MatrixGUI();

        int r1 = _matrix1.MrowLength();
        int c1 = _matrix1.MColLength();
        int r2 = _matrix2.MrowLength();
        int c2 = _matrix2.MColLength();



        if (c1 == r2) {



            for (int j = 0; j < c2; j++) {
                for (int i = 0; i < r1; i++) {
                    int sum = 0;
                    for (int k = 0; k < r2; k++) {
                        sum = sum  + _matrix1.matrixshow(i,k) * _matrix1.matrixshow(k,j);
                    }
                    Mgui.UpdateSolMatrix(i,j,sum);
                    Thread.sleep(sleeptime); //alvas
                }
            }

        }else {
            throw new Exception("Matrixok dimenzioi nem egyeznek");
        }



    }


    public void MatrixFreeStyle() throws Exception {


            MatrixGUI Mgui = new MatrixGUI();

            int r1 = _matrix1.MrowLength();
            int c1 = _matrix1.MColLength();
            int r2 = _matrix2.MrowLength();
            int c2 = _matrix2.MColLength();

            if (c1 == r2) {

                for (int j = 0; j < c2; j++) {
                    //páros sorok
                    for (int i = 0; i < r1; i += 2) {
                        int sum = 0;
                        for (int k = 0; k < r2; k++) {
                            sum = sum + _matrix1.matrixshow(i, k) * _matrix2.matrixshow(k, j);
                        }
                        Mgui.UpdateSolMatrix(i, j, sum);
                        Thread.sleep(sleeptime);
                    }


                }

                //páratlan sorok
                for (int j = 0; j < c2; j++) {
                    for (int i = 1; i < r1; i += 2) {
                        int sum = 0;
                        for (int k = 0; k < r2; k++) {
                            sum = sum + _matrix1.matrixshow(i, k) * _matrix2.matrixshow(k, j);
                        }
                        Mgui.UpdateSolMatrix(i, j, sum);
                        Thread.sleep(sleeptime);
                    }
                }
                //System.out.println("isJavaFxThread?" + Platform.isFxApplicationThread());

            } else {
                throw new Exception("Matrixok dimenzioi nem egyeznek");
            }
        }






}
