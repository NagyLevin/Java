package com.example.ora9.SleepVeletlenSzamok;

import com.example.ora9.oraifeladatwaitnoty.Truck;


public class main {
    public static void main(String[] args) {
        new Thread(new Randomnumber()).start();
        Randomnumber jThread = new Randomnumber();
        jThread.run();


        //jThread.interrupted();
        //ugyanez joinra is


    }



}
