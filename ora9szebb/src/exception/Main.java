package exception;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Thread diceThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Kockázás indul.");
                Random random = new Random();
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    int eredmeny = random.nextInt(6) + 1;

                    if(eredmeny == 1){
                        throw new RuntimeException(eredmeny + " Kiestél!");
                    } else {
                        System.out.println(eredmeny + " Szerencse!");
                    }
                }
            }
        });
        diceThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("Kivétel történt: " + e.getMessage());
            }
        });
        diceThread.start();
    }
}
