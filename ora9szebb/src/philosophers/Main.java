package philosophers;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Philosopher> philosophers = new ArrayList<>();
        ArrayList<Fork> forks = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            forks.add(new Fork());
        }
        for (int i = 0; i < 5; ++i) {
            philosophers.add(new Philosopher(i, forks.get(i), forks.get((i+1)%5)));
        }

        for (int i = 0; i < 5; ++i) {
            philosophers.get(i).start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
