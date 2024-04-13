import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.UnknownHostException;
import java.io.IOException;

public class TermeloKliens implements Runnable {
    protected Socket clientSocket;

    public TermeloKliens(String host) throws UnknownHostException, IOException {
        clientSocket = new Socket(host, TaroloSzerver.PORT_NUMBER);
    }



    public void run() {
        try {
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));                    //kommunikáciohoz
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter serverOutput = new PrintWriter(clientSocket.getOutputStream());                             //kommunikáciohoz

            System.out.println("Server: " + serverInput.readLine());
            System.out.print("Client: ");
            System.out.flush();




            System.out.println("I Have Goddies");
            serverOutput.print("I Have Goddies");  //a szervernek ezt az üzenetet küldöm
            serverOutput.flush();
            //Thread.sleep(100); //majd randommal





           clientSocket.close();

        } catch (IOException e) {
            System.err.println("Nem sikerült kommunikállni a szerverrel");
        }
    }

    public static void main(String[] args) {
        try {
            new Thread(new TermeloKliens("127.0.0.1")).start(); //ezen az ip címen probal majd csatlakozni
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}