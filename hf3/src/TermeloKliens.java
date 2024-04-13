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
            BufferedReader clientbeolvas = new BufferedReader(new InputStreamReader(System.in));                   // beolvas a client konzolárol
            BufferedReader fromszerver = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  //ezt kuldi a szerver
            PrintWriter toszerver = new PrintWriter(clientSocket.getOutputStream());//ezt küldjük a szervernek

            //toszerver.print("Hello szerver!" + "\r" + "\n");
            //System.out.println("Server: " + server);
            while (! Thread.currentThread().isInterrupted()) {



                System.out.flush();



                toszerver.print("I Have Goddies" + "\r" + "\n");  //a szervernek ezt az üzenetet küldöm // \r \n el kuldom el
                toszerver.flush();
                //Thread.sleep(100); //majd randommal

                    String szerversay = "";
                    szerversay = fromszerver.readLine();


                    System.out.println("Server: " + szerversay);


                //serverOutput.flush();
            }




        } catch (IOException e) {
            System.err.println("Nem sikerült kommunikállni a szerverrel");
        }finally {
            try{
                if(clientSocket != null){
                    clientSocket.close();

                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

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