package DrawGame.GUI.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class hosting implements Runnable {

    public static final int PORT_NUMBER = 13131;    //port szama 10k felett
    protected ServerSocket serverSocket;

    public hosting() {
        try {
            serverSocket= new ServerSocket(PORT_NUMBER);           //a szerver socketje
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void run() {
        try {
            while (! Thread.currentThread().isInterrupted()) {
                Socket clientSocket = serverSocket.accept();        // a kliensek socketjei
                try {
                    new Allplayers(clientSocket).start();
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
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


