

import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.io.IOException;

public class Snails extends Thread {
    public Snails(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.clientWriter = new PrintWriter(clientSocket.getOutputStream());
    }

    protected void sendLine(String line) throws IOException {
        clientWriter.print(line + "\r\n");
        clientWriter.flush();
    }

    protected boolean expect(String line) throws IOException {
        String clientLine = clientReader.readLine();
        if (! clientLine.equals(line)) {
            return false;
        }

        return true;
    }

    public void run() {
        System.out.println("Klienssel kommunikáció indul");

        try {
            sendLine("Race started!, Cheer on your snail!");


            if (! expect("GO")) {
                sendLine("You're supposed to say \"GO\"");
                clientSocket.close();
                System.out.println("A klienssel a kommunikáció lezárult!");
                return;
            }
            if(expect("GO")){
                sendLine("Your snail moved 1 mm");
                sendLine("Cheer on!");
            }







            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("A klienssel a kommunikáció sikeres volt!");
    }

    protected Socket clientSocket;
    protected BufferedReader clientReader;
    protected PrintWriter clientWriter;



}

