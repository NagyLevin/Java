package org.example.ora10gepterem.pools.cached;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String args[]) {
        System.out.println("Starting Cached Thread Pool");
        ExecutorService executor = Executors.newCachedThreadPool(); //newFIXEDCachedThreadPool() //mindig csak akkor indul uj szál, ha az előző befejeződött
        for (int i = 1; i <= 16; ++i) {
            executor.execute(new Counter(3));
        }
        executor.shutdown();
    }
}
