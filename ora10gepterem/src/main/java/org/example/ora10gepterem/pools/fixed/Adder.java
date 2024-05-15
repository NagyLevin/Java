package org.example.ora10gepterem.pools.fixed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class Adder implements Runnable{
    static ArrayList<Integer> a;
    static ArrayList<Integer> b;
    static ArrayList<Integer> c;

    private int idx;

    Adder(int _idx){
        this.idx = _idx;


    }

    public static synchronized ArrayList<Integer> add(ArrayList<Integer> a_p, ArrayList<Integer> b_p){

        a = a_p;
        b = b_p;

        c = new ArrayList<>(Collections.nCopies(a.size(),0));
        ExecutorService threadHandler = java.util.concurrent.Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < a.size(); i++) {
            threadHandler.execute(new Adder(i));


        }
        threadHandler.shutdown();
        try {
            threadHandler.awaitTermination(6, TimeUnit.SECONDS);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        return c;
    }


    @Override
    public void run() {
        c.set(idx,a.get(idx) + b.get(idx));

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
