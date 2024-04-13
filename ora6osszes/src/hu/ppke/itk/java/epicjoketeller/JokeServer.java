package hu.ppke.itk.java.epicjoketeller;

import java.net.ServerSocket;
import java.net.Socket;

import java.io.IOException;

public class JokeServer implements Runnable {
    public static final int PORT_NUMBER = 10143;

    public JokeServer() throws IOException {
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
                    new EpicJokeTeller(clientSocket).start();
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

        System.out.println("A Joke Szerver leáll");
    }

    public static void main(String[] args) {
        try {
            new Thread(new JokeServer()).start();
            System.out.println("A Joke Szerver elindult");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected ServerSocket serverSocket;
}