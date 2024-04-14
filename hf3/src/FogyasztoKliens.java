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

    int sikeresfogyaszt = 0;
    int osszkeres = 0;

    int sikertelenfogaszt =0;

    public int RandomBetween(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }



    public void run() {

        try {

            BufferedReader fromszerver = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  //ezt kuldi a szerver
            PrintWriter toszerver = new PrintWriter(clientSocket.getOutputStream());//ezt küldjük a szervernek

            toszerver.print("Hello szerver!" + "\r" + "\n");

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

                    if(szerversay.equals("prod-")){
                        sikeresfogyaszt = sikeresfogyaszt +1;
                    }
                    if(szerversay.equals("keves")){ //ha sokat termel akkor varnia kell

                        sikertelenfogaszt = sikeresfogyaszt +1;
                        Thread.sleep(RandomBetween(100,150));//esetleg megszorozva a sokak szamaval, hogy kissebb legyen az esély a megállásra
                    }

                }

                osszkeres = osszkeres +1;
                Thread.sleep(RandomBetween(minrandom,maxrandom));

            }




        } catch (IOException | InterruptedException e) {
            System.err.println("Nem sikerült kommunikállni a szerverrel");
        }finally {

            System.out.println("Sikertelen/Sikeres keres: " + sikeresfogyaszt + "/" + sikertelenfogaszt + " Osszes keres: "+sikeresfogyaszt +sikertelenfogaszt);
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