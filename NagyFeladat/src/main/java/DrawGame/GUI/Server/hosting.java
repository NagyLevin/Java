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
    static int minplayers = 3;
    public static CyclicBarrier barrier = new CyclicBarrier(minplayers);
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

    public synchronized static void resetlatch() {
        latch = new CountDownLatch(1);
    }

    public synchronized static void resetbarrier() {
        currentplayers = 0;
        barrier = new CyclicBarrier(1);
    }

    public synchronized static void playerleft() {
        currentplayers = currentplayers -1;
        if(currentplayers < 0){
            currentplayers = 0;
        }
        else if(currentplayers == 0){
            barrier = new CyclicBarrier(minplayers);
        }else {
            barrier = new CyclicBarrier(currentplayers);
        }

        Lobby.playerleft();

    }


    @Override
    public void run() {
        try {
            while (! Thread.currentThread().isInterrupted()) {
                Socket clientSocket = serverSocket.accept();        // a kliensek socketjei
                try {
                    new Allplayers(clientSocket).start();

                    if(currentplayers > minplayers){
                        currentplayers = currentplayers +1;
                        barrier = new CyclicBarrier(currentplayers);
                    }



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


