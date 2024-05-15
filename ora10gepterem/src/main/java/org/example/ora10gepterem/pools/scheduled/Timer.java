package org.example.ora10gepterem.pools.scheduled;

public class Timer implements Runnable {
    static private long startTime;
    private String name;

    public static void startTimer() {
        startTime = System.currentTimeMillis();
    }

    public Timer(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + ": " + (System.currentTimeMillis() - startTime) + " ms");
    }
}
