package hu.ppke.itk.java.ping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PingClientHandler implements Runnable {

    private Socket clientSocket;

    public PingClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            // A szukseges IO cuccok
            BufferedReader clientInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter clientOutput = new PrintWriter(clientSocket.getOutputStream());


            String line = "";
            // String osszehasonlitas MINDIG equals-zal!
            while (!(line = clientInput.readLine()).equals("QUIT")) {
                if (line.equals("PING")) {
                    // Halozati kommunikacioban szokas, hogy a sorveget CRLF jelzi, ezt a konvenciot tartsuk meg!
                    clientOutput.print("PONG\r\n"); // Hasznalhatnank a println() metodust is. De mivel a sorvege jel operaciosrendszertol fugg (unix/linuxon: \n, windows: \r\n), ezert biztosabb, ha kiirjuk
                    clientOutput.flush();
                }
            }
            // CLEANUP
            // Sose felejtsetek el lezarni! Magatoknak okoztok vele fejfajast.
            clientSocket.close();
        }catch (IOException e){
            e.printStackTrace();
        }


    }
}
