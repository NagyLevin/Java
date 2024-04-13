package hu.ppke.itk.java.ping;

import java.net.Socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.io.IOException; // Ha a kommunikacioban valami balul sul el
import java.net.UnknownHostException; // Ha rossz cimre probalunk csatlakozni

public class PingClient implements Runnable {
    public PingClient() throws UnknownHostException, IOException {
        clientSocket = new Socket("localhost", PingServer.PORT_NUMBER);
        clientSocket.setTcpNoDelay(true);
    }

    public void close() throws IOException {
        clientSocket.close();
    }

    public void run() {
        try {
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter serverOutput = new PrintWriter(clientSocket.getOutputStream());

            for (int i = 0; i < 5; ++i) {
                long startTime = System.currentTimeMillis();
                serverOutput.print("PING\r\n");
                serverOutput.flush(); // Buffer uritese
                if (serverInput.readLine().equals("PONG")) {
                    long endTime = System.currentTimeMillis();
                    System.out.println("Az uzenet visszaert " + (endTime - startTime) + " ms alatt");
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted while sleeping, shutting down...");
                        clientSocket.close(); // Minden lehetseges ponton le kell zarni a socket-okat
                        return;
                    }
                }
            }

            serverOutput.print("QUIT\r\n");
            serverOutput.flush();

            // Itt se felejtsetek lezarni a kapcsolatot!
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            new Thread(new PingClient()).start();
        } catch (IOException e) { // Az UnknownHostException az IOException leszarmazottja. Mivel most egyikre se tudnank ertelmes hibakezelest csinalni, egyszerre lekezeljuk mindkettot.
            e.printStackTrace();
        }
    }

    // Csatlakozunk a sajat gepunkon futo szerverhez. A sajat gepunk hostneve localhost, ip cime 127.0.0.1.
    protected Socket clientSocket;

}