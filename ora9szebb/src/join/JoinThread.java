package join;

import java.util.Random;

public class JoinThread extends Thread {
    private int id;

    public JoinThread(int id){
        this.id = id;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            Thread.sleep((random.nextInt(19)+1)*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(id + ". szál lefutott.");
    }
}
