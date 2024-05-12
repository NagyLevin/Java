package DrawGame.GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Allplayers extends Thread{

    protected Socket clientSocket;
    protected BufferedReader clientReader;
    protected PrintWriter clientWriter;


    public Allplayers(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));   //reader a kommunikációhoz
        this.clientWriter = new PrintWriter(clientSocket.getOutputStream());    //writer a kommunikációhoz
    }
    protected void sendLine(String line) throws IOException {   //elküld egy sort a kliensnek
        clientWriter.print(line + "\r\n");
        clientWriter.flush();
    }
    public void run() {
        System.out.println("van egy kliens!");
        long ThreadId = Thread.currentThread().getId();
        System.out.println("Hello Kliens!" + "(" +ThreadId + ")");

    }


}
