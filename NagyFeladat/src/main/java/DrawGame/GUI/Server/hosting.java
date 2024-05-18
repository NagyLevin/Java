package DrawGame.GUI.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class hosting implements Runnable {

    public static final int PORT_NUMBER = 13131;    //port szama 10k felett
    protected ServerSocket serverSocket;
    public static String gamecode = "";

    public static CyclicBarrier barrier = new CyclicBarrier(1);
    public static CountDownLatch latch = new CountDownLatch(1);

    //esetleg megegy es felvaltva?

    static int maxplayers = 8;
    static int currentplayers = 0;




    public hosting(String _gamecode) {
        try {
            serverSocket= new ServerSocket(PORT_NUMBER);           //a szerver socketje
            gamecode = _gamecode;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void resetlatch() {
        latch = new CountDownLatch(1);
    }

    public static void resetbarrier() {
        currentplayers = 0;
        barrier = new CyclicBarrier(1);
    }


    @Override
    public void run() {
        try {
            while (! Thread.currentThread().isInterrupted()) {
                Socket clientSocket = serverSocket.accept();        // a kliensek socketjei
                try {
                    new Allplayers(clientSocket).start();
                    currentplayers = currentplayers +1;
                    barrier = new CyclicBarrier(currentplayers);
                    System.out.println("Egy uj player csatlakozott:" +currentplayers);

                } catch (IOException e) {
                    System.err.println("Cant communicate with the client");
                }
            }


        } catch (IOException e) {
            System.out.println("Accept failed!");
        }

        try {
            if(serverSocket != null){
                serverSocket.close();
                currentplayers = currentplayers-1;
                barrier = new CyclicBarrier(currentplayers);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


