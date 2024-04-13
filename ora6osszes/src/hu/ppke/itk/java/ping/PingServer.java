package hu.ppke.itk.java.ping;

import java.net.ServerSocket;
import java.net.Socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.io.IOException;

public class PingServer implements Runnable {
    public static final int PORT_NUMBER = 55567;

    public PingServer() throws IOException {
        serverSocket = new ServerSocket(PORT_NUMBER);
    }

    public void close() throws IOException {
        serverSocket.close();
    }

    public void run() {
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                // Egy bejovo klienssel elkezdunk foglalkozni.
                // Ha jelenleg nincs kapcsolodo kliens, akkor a szerver blokkol, var addig, amig akad valaki, aki velunk akar csevegni.
                PingClientHandler clientHandler = new PingClientHandler(clientSocket);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace(); // Eleg sok helyen elszallhat a kapcsolat, erdemes a java doksit egyszer vegigolvasni, hogy mennyi problema lephet fel halozati kommunikacio soran.
        }

        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("A Szerver leáll");
    }

    public static void main(String[] args) {
        try {
            new Thread(new PingServer()).start();
            System.out.println("A Szerver elindult");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected ServerSocket serverSocket;
}