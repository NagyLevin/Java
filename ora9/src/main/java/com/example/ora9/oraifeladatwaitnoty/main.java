package com.example.ora9.oraifeladatwaitnoty;

import java.io.IOException;

public class main {
        public static void main(String[] args) {

        new Thread(new TaroloSzerver()).start();    //a szerver szála
        System.out.println("BUSINESS IS UP");


        try {
            for (int i = 0; i < 10; i++) {
                new Thread(new Truck("127.0.0.1")).start(); //ezen az ip címen probal majd csatlakozni
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            for (int i = 0; i < 1; i++) {
                new Thread(new Factory("127.0.0.1")).start(); //ezen az ip címen probal majd csatlakozni
            }



        } catch (IOException e) {
            e.printStackTrace();
        }







    }






}
