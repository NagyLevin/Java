package org.example.hf4;


import java.util.Vector;

public class Matrix {

    private int  sizeX = 0;
    private int sizeY = 0;

    private int[][] matrix = new int[sizeX][sizeY];

    public int matrixshow(int i,int j){


        return matrix[i][j];
    }

    public void matrixstore(int i,int j,int value){
        matrix[i][j] = value;


    }

    public static void main(String[] args) {




    }


}
