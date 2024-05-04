package org.example.hf4;


public class Matrix {

    private int  _sizeX = 0;
    private int _sizeY = 0;

    private int[][] _matrix;

    /**
     *  Mátrix konstruktor
     * @param sizeX méret sor
     * @param sizeY méret oszlop
     */
    Matrix(int sizeX, int sizeY){
        _sizeX = sizeX;
        _sizeY = sizeY;
        _matrix = new int[_sizeX][_sizeY];

    }

    /**
     * Visszaadja a mátrix i,j edik elemét
     * @param i
     * @param j
     * @return
     */

    public synchronized int matrixshow(int i,int j){


        return _matrix[i][j];
    }

    /**
     * Lecseréli a mátrixot a megadott mátrixra
     * @param matrix
     */

    public synchronized void replacematrix(int[][] matrix){

            _matrix = matrix;

    }

    /**
     * kiiratja a mátrixot, csak debughoz kellett
     *
     */
    void printM(){
        for (int[] matrix : _matrix) {
            for (int j = 0; j < _matrix[0].length; j++) {
                System.out.println(matrix[j]);
            }
        }
    }

    /**
     * Belerakja a value elemet a mátrix i,j-edik poziciójára
     * @param i
     * @param j
     * @param value
     */

    public synchronized void matrixstore(int i,int j,int value){
        _matrix[i][j] = value;


    }

    /**
     * Visszaállítja a mátrixot egy 1x1 es állapotba
     */
    public synchronized void reset(){
        _matrix = new int[1][1];
    }


    /**
     * Visszaadja a mátrix sorának hosszát
     * @return
     */
    public synchronized int MrowLength(){

        return _matrix.length;
    }

    /**
     * Visszaaja a mátrix oszlopának a hosszát
     * @return
     */
    public synchronized int MColLength(){

        return _matrix[0].length;
    }

    /**
     * Megnöveli eggyel a mátrix méretét
     */
    public synchronized void expand(){

            int x = this.MrowLength();
            int y = this.MColLength();
            x = x + 1;
            y = y + 1;
            //System.out.printf("megnoveltemx: " + x);

            _matrix = new int[x][y];




    }



}
