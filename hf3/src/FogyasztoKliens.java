import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.UnknownHostException;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    boolean fogyaszsak = true;

    String isnumber = "\\d+";
    Pattern patterN = Pattern.compile(isnumber);
    protected static Vector<Integer> ProductsEaten = new Vector<Integer>();


    public int RandomBetween(int min, int max) {

        return (int) ((Math.random() * (max - min)) + min);
    }



    public void run() {

        try {

            BufferedReader fromszerver = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  //ezt kuldi a szerver
            PrintWriter toszerver = new PrintWriter(clientSocket.getOutputStream());//ezt küldjük a szervernek

            toszerver.print("Hello szerver!" + "\r" + "\n");

            while (fogyaszsak == true) {



                System.out.flush();



                toszerver.print("GET PRODUCT" + "\r" + "\n");  //a szervernek ezt az üzenetet küldöm // \r \n el kuldom el
                toszerver.flush();
                //Thread.sleep(100); //majd randommal

                String szerversay = "111";
                szerversay = fromszerver.readLine();

                if(!szerversay.isEmpty()){
                    System.out.println("Server: " + szerversay);
                    //System.out.print(szerversay.length());

                    if(szerversay.equals("OK SENDING PRODUCT ")){

                        Matcher matcherN = patterN.matcher(szerversay);
                        if(matcherN.find()){

                            //System.out.println("test: " + matcherN.group());

                            ProductsEaten.add(Integer.parseInt(matcherN.group())); //a vegerol levagja a szamot, es belerakja a products ba
                        }

                        sikeresfogyaszt = sikeresfogyaszt +1;
                    }
                    if(szerversay.equals("NOPE TRY AGAIN")){ //ha sokat termel akkor varnia kell

                        sikertelenfogaszt = sikertelenfogaszt +1;
                        Thread.sleep(RandomBetween(100,150));//esetleg megszorozva a sokak szamaval, hogy kissebb legyen az esély a megállásra
                    }
                    if(szerversay.equals("You produce to much...")){
                        fogyaszsak = false;
                    }


                }

                osszkeres = osszkeres +1;
                Thread.sleep(RandomBetween(minrandom,maxrandom));

            }


            int osszkeres = sikeresfogyaszt +sikertelenfogaszt;
            System.out.println("Sikertelen/Sikeres keres: " + sikeresfogyaszt + "/" + sikertelenfogaszt + " Osszes keres: "+osszkeres);

        } catch (IOException | InterruptedException e) {
            System.err.println("Cant reach server");
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