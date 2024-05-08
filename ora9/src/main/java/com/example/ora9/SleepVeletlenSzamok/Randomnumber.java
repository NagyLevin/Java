package com.example.ora9.SleepVeletlenSzamok;

import java.util.Random;

public class Randomnumber implements Runnable{

    Random rand = new Random();

    @Override
    public void run() {
        try {
        while(true){
            System.out.println(rand.nextInt(100));

                Thread.sleep(rand.nextInt(250));
            }


        }catch (InterruptedException e) {
            System.out.println("Thread interrupted");

        }


    }
}
