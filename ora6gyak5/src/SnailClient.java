import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.UnknownHostException;
import java.io.IOException;

public class SnailClient implements Runnable {

    public SnailClient(String host) throws UnknownHostException, IOException {
        clientSocket = new Socket(host, RaceServer.PORT_NUMBER);
    }

    public void close() throws IOException {
        clientSocket.close();
    }

    public void run() {
        try {
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter serverOutput = new PrintWriter(clientSocket.getOutputStream());

            System.out.println("Server: " + serverInput.readLine());
            System.out.print("Client: ");
            System.out.flush();
            serverOutput.print(consoleReader.readLine() + "\r\n");
            serverOutput.flush();

            System.out.println("Server: " + serverInput.readLine());
            System.out.print("Client: ");
            System.out.flush();
            serverOutput.print(consoleReader.readLine() + "\r\n");
            serverOutput.flush();

            System.out.println("Server: " + serverInput.readLine());

            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Failed to communicate with server!");
        }
    }

    public static void main(String[] args) {
        try {
            new Thread(new SnailClient("127.0.0.1")).start(); //uj klies szal letrehozasa //itt kell megadni az ipt amire csatlakozonak
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Socket clientSocket;
}
