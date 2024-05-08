package sleep;

import java.util.Random;

public class SleepThread extends Thread {
    @Override
    public void run() {
        try {
            Random random = new Random();
            while (true){
                Thread.sleep(5000);
                System.out.println(random.nextInt());
            }
        } catch (InterruptedException e){
            System.out.println("Megszakítjuk a futást.");
        }

    }
}
