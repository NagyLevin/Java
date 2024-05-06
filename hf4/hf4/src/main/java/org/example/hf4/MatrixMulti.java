package org.example.hf4;


public class MatrixMulti implements Runnable {

    private Matrix _matrix1;
    private Matrix _matrix2;
    private int sleeptime = 500; //ennyit vár a lépések között
    private int _chase = -1;       //melyik eset

    /**
     * Mátrixszorzás osztály konstruktora
     * @param matrix1 az elő mátrix, ami itt másodiknak van véve, a mátrixszorzás sorendje miatt
     * @param matrix2 a második mátrix ami elsőnek van véve a mátrixszorzás sorrendje miatt
     * @param chase megmondja hogy melyik gombot nyomták meg, 1 ha sor szerint szeretnénk, 2 ha oszlop, ill 3 ha a freestlyle opció
     */
    MatrixMulti(Matrix matrix1,Matrix matrix2, int chase){
        _matrix1 = matrix2;
        _matrix2 = matrix1;
        _chase = chase;

    }

    /**
     * Futtatás illetve eldöntjük hogy melyik ágat szeretnénk futtatni
     */
    @Override
    public void run() {
        //System.out.println("isJavaFxThread?" + Platform.isFxApplicationThread()); //meg tudom vele nezni, hogy javafx thread e az adott thread


        if(!MatrixGUI.calcrunning){
            MatrixGUI.calcrunning = true;
        try {
            if (_chase == 1) {


                MatrixSor();
            }
            if (_chase == 2) {
                MatrixOszlop();
            }
            if (_chase == 3) {
                MatrixFreeStyle();
            }
            MatrixGUI.calcrunning = false;


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        }

    }

    /**
     * Itt soronként összeszorozva adja vissza az eredményeket
     * @throws Exception
     */
    public void MatrixSor() throws Exception {



        int r1 = _matrix1.MrowLength();
        int c1 = _matrix1.MColLength();
        int r2 = _matrix2.MrowLength();




        if (c1 == r2) {



            for (int i = 0; i < r1; i++) {
                for (int j = 0; j < r2; j++) {
                    int sum = 0;
                    for (int k = 0; k < c1; k++) {
                       sum += _matrix1.matrixshow(i,k) * _matrix2.matrixshow(k,j);
                       // System.out.println(_matrix1.matrixshow(i,k) + " * "+_matrix2.matrixshow(k,j) + " = " +sum);
                    }
                    UpdateMatrix(i,j,sum);

                }
            }



        }else {
            throw new Exception("Matrixok dimenzioi nem egyeznek");
        }



    }

    //System.out.println(_matrix1.matrixshow(i,k) + " * "+_matrix2.matrixshow(k,j) + " = " +sum);

    /**
     * Itt oszloponként adja vissza az eredményeket az elsőtől kezdve
     * @throws Exception
     */

    public void MatrixOszlop() throws Exception {



        int r1 = _matrix1.MrowLength();
        int c1 = _matrix1.MColLength();
        int r2 = _matrix2.MrowLength();
        int c2 = _matrix2.MColLength();



        if (c1 == r2) {



            for (int j = 0; j < c2; j++) {
                for (int i = 0; i < r1; i++) {
                    int sum = 0;
                    for (int k = 0; k < r2; k++) {
                        sum += _matrix1.matrixshow(i, k) * _matrix2.matrixshow(k, j);
                    }

                    UpdateMatrix(i,j,sum);
                }
            }

        }else {
            throw new Exception("Matrixok dimenzioi nem egyeznek");
        }



    }

    /**
     * Összegyüjtve szebben a mátrix update, hogy ne legyen mindez 4x leírva
     * Updateli a solutionmatrixot az aktuális eredménnyel
     * @param i
     * @param j
     * @param value
     * @throws InterruptedException
     */
    public synchronized void UpdateMatrix(int i, int j,int value) throws InterruptedException {
        MatrixGUI Mgui = new MatrixGUI();

        Mgui.UpdateSolMatrix(i, j, 0);
        Thread.sleep(sleeptime); //alvas
        Mgui.UpdateSolMatrix(i, j, value);
        Thread.sleep(sleeptime);


    }


    /**
     * Itt elsőnek a páratlan számú oszlopok értékeit adja vissza, majd  a párosokét
     * @throws Exception
     */

    public void MatrixFreeStyle() throws Exception {




            int r1 = _matrix1.MrowLength();
            int c1 = _matrix1.MColLength();
            int r2 = _matrix2.MrowLength();
            int c2 = _matrix2.MColLength();

            if (c1 == r2) {

                for (int j = 0; j < c2; j++) {
                    //páros oszlopok
                    for (int i = 0; i < r1; i += 2) {
                        int sum = 0;
                        for (int k = 0; k < r2; k++) {
                            sum = sum + _matrix1.matrixshow(i, k) * _matrix2.matrixshow(k, j);
                        }
                        UpdateMatrix(i,j,sum);

                    }


                }

                //páratlan oszlopok
                for (int j = 0; j < c2; j++) {
                    for (int i = 1; i < r1; i += 2) {
                        int sum = 0;
                        for (int k = 0; k < r2; k++) {
                            sum = sum + _matrix1.matrixshow(i, k) * _matrix2.matrixshow(k, j);
                        }
                        UpdateMatrix(i,j,sum);
                    }
                }
                //System.out.println("isJavaFxThread?" + Platform.isFxApplicationThread());

            } else {
                throw new Exception("Matrixok dimenzioi nem egyeznek");
            }
        }






}
