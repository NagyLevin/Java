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
        if(_matrix.length == matrix.length && matrix[0].length == _matrix[0].length){
            _matrix = matrix;
        }else{
            throw new IllegalArgumentException("A matrixok dimenzioja nem egyezik");
        }


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

    public synchronized int MrowLength(){

        return _matrix.length;
    }
    public synchronized int MColLength(){

        return _matrix[0].length;
    }


    public static void main(String[] args) {




    }


}
