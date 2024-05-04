package org.example.hf4;


public class Matrix {

    private int  _sizeX = 0;
    private int _sizeY = 0;

    private int[][] _matrix;

    Matrix(int sizeX, int sizeY){
        _sizeX = sizeX;
        _sizeY = sizeY;
        _matrix = new int[_sizeX][_sizeY];

    }

    public synchronized int matrixshow(int i,int j){


        return _matrix[i][j];
    }

    public synchronized void replacematrix(int[][] matrix){

            _matrix = matrix;



    }

    void printM(){
        for(int i = 0;i < _matrix.length;i++){
            for(int j = 0;j < _matrix[0].length;j++){
                System.out.println(_matrix[i][j]);
            }
        }
    }


    public synchronized void matrixstore(int i,int j,int value){
        _matrix[i][j] = value;


    }
    public synchronized void reset(){
        _matrix = new int[1][1];
    }

    public synchronized int MrowLength(){

        return _matrix.length;
    }
    public synchronized int MColLength(){

        return _matrix[0].length;
    }
    public synchronized void expand(){

            int x = this.MrowLength();
            int y = this.MColLength();
            x = x + 1;
            y = y + 1;
            //System.out.printf("megnoveltemx: " + x);

            _matrix = new int[x][y];




    }


    public static void main(String[] args) {




    }


}
