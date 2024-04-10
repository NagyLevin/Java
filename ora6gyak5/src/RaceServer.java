
import java.net.ServerSocket;
import java.net.Socket;

import java.io.IOException;

public class RaceServer implements Runnable {
    public static final int PORT_NUMBER = 10143;

    public RaceServer() throws IOException {
        serverSocket = new ServerSocket(PORT_NUMBER);
    }

    public void close() throws IOException {
        serverSocket.close();
    }

    public void run() {
        try {
            while (! Thread.currentThread().isInterrupted()) {
                Socket clientSocket = serverSocket.accept();
                try {
                    new Snails(clientSocket).start();


                } catch (IOException e) {
                    System.err.println("Failed to communicate with client!");
                }
            }
        } catch (IOException e) {
            System.out.println("Accept failed!");
        }

        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("A Race Szerver leáll");
    }

    public static void main(String[] args) {
        try {
            new Thread(new RaceServer()).start(); //egy szal
            System.out.println("A Race Szerver elindult");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected ServerSocket serverSocket;
}
