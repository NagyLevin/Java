import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.UnknownHostException;
import java.io.IOException;

public class FogyasztoKliens implements Runnable {
    protected Socket clientSocket;

    public FogyasztoKliens(String host) throws UnknownHostException, IOException {
        clientSocket = new Socket(host, TaroloSzerver.PORT_NUMBER);
    }
    int minrandom = 300;
    int maxrandom = 500;


    public int RandomBetween(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
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



                toszerver.print("Give me goddies" + "\r" + "\n");  //a szervernek ezt az üzenetet küldöm // \r \n el kuldom el
                toszerver.flush();
                //Thread.sleep(100); //majd randommal

                String szerversay = "111";
                szerversay = fromszerver.readLine();

                if(!szerversay.isEmpty()){
                    System.out.println("Server: " + szerversay);
                    //System.out.print(szerversay.length());

                    if(szerversay.equals("prod+")){

                    }
                    if(szerversay.equals("sok")){ //ha sokat termel akkor varnia kell

                        Thread.sleep(RandomBetween(100,150));//esetleg megszorozva a sokak szamaval, hogy kissebb legyen az esély a megállásra
                    }

                }


                Thread.sleep(RandomBetween(minrandom,maxrandom));

            }




        } catch (IOException | InterruptedException e) {
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
            new Thread(new FogyasztoKliens("127.0.0.1")).start(); //ezen az ip címen probal majd csatlakozni
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}