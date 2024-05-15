package org.example.ora10gepterem.pools.scheduled;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String args[]) {
        ScheduledExecutorService threadHandler = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()); //Threadaek nagjából egy időeltolással futnak le
        Timer.startTimer();
        threadHandler.schedule(new Timer("First"), 1, TimeUnit.SECONDS);
        threadHandler.scheduleAtFixedRate(new Timer("FixedRate"), 1, 2, TimeUnit.SECONDS);
        threadHandler.scheduleWithFixedDelay(new Timer("WithDelay"), 0, 1, TimeUnit.SECONDS);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadHandler.shutdown();
    }

}
