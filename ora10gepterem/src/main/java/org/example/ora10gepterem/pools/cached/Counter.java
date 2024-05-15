package org.example.ora10gepterem.pools.cached;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

class Counter implements Runnable {

    private static int nth = 0;

    private final int id = ++nth;
    private final int number;

    public Counter(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        System.out.println("Starting counter: " + id);
        for (int i = 0; i < number; ++i) {
            try {
                MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                // ignore
            }
            System.out.printf("counter %d, value: %d%n", id, i);
        }
        System.out.println("Finishing counter: " + id);
    }
}
