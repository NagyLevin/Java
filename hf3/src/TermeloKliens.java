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

    public int RandomBetween(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    int minrandom = 200;
    int maxrandom = 500;
    int sikerestermeles = 0;
    int sikertelentermeles = 0;
    int ossztermeles = 0;



    public void run() {

        try {

            BufferedReader fromszerver = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  //ezt kuldi a szerver
            PrintWriter toszerver = new PrintWriter(clientSocket.getOutputStream());//ezt küldjük a szervernek

            toszerver.print("Hello szerver!" + "\r" + "\n");
            //System.out.println("Server: " + server);
            while (! Thread.currentThread().isInterrupted()) {



                System.out.flush();



                toszerver.print("I Have Goddies" + "\r" + "\n");  //a szervernek ezt az üzenetet küldöm // \r \n el kuldom el
                toszerver.flush();
                //Thread.sleep(100); //majd randommal

                    String szerversay = "111";
                    szerversay = fromszerver.readLine();

                    if(!szerversay.isEmpty()){
                        System.out.println("Server: " + szerversay);
                        //System.out.print(szerversay.length());
                        if(szerversay.equals("prod+")){
                            sikerestermeles = sikerestermeles +1;
                        }
                        if(szerversay.equals("sok")){ //ha sokat termel akkor varnia kell
                            sikertelentermeles = sikertelentermeles +1;
                            Thread.sleep(250); //esetleg megszorozva a sokak szamaval, hogy kissebb legyen az esély a megállásra
                        }



                    }





                Thread.sleep(RandomBetween(minrandom,maxrandom));
            }




        } catch (IOException | InterruptedException e) {
            System.err.println("Nem sikerült kommunikállni a szerverrel");
        }finally {
            System.out.println("Sikertelen/Sikeres keres: " + sikertelentermeles + "/" + sikerestermeles + " Osszes keres: "+sikertelentermeles +sikerestermeles);
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