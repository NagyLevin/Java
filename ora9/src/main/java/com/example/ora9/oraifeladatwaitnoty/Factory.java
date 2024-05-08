package com.example.ora9.oraifeladatwaitnoty;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.io.IOException;
import java.util.Vector;


public class Factory implements Runnable {
    protected Socket clientSocket;

    /**
     *  socket keszites
     * @param host thread
     * @throws IOException
     */

    public Factory(String host) throws IOException {
        clientSocket = new Socket(host, TaroloSzerver.PORT_NUMBER);
    }

    /**
     * Ad egy random szamot a ket ertek között ezt használom fel a varkozasi idő randomizálására
     * @param min
     * @param max
     * @return visszaadok egy a két szám közözz lévő számot
     */

    public int RandomBetween(int min, int max) {

        return (int) ((Math.random() * (max - min)) + min);
    }


    /**
     * nem tudom hogy ezt kell e szinkronizállni, de a biztonság kedvéért ezt is beleraktam, hiszen ehhez is hozzáférhet több szál
     * @return random szam visszaadása termekidnek 1-100 között
     */
    public synchronized int getTermel() {

        if(!Products.isEmpty()){
            return Products.removeFirst();//RandomBetween(1,100);
        }
        else
            return 0;

    }

    int minrandom = 200;    //min idő ami alatt termelhet
    int maxrandom = 500;    //max idő ami alatt termelet
    int sikerestermeles = 0;    //sikeres termelések száma
    int sikertelentermeles = 0; //sikertelen termelések száma
    boolean termeljek = true;   //addig megy a lenti while amíg a szerver azt nem mondja neki, hogy ne termeljen, mert túl sokat termelt

    final static Vector<Integer> Products = new Vector<Integer>();   //final, azert hogy ne jojjön letre uj

    /**
     * termelő fő része, itt kommunikáll a szerverrel, és itt adja át neki az elkészült productot
     */

    public void run() {

        try {

            BufferedReader fromszerver = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  //ezt kuldi a szerver
            PrintWriter toszerver = new PrintWriter(clientSocket.getOutputStream());//ezt küldjük a szervernek

            toszerver.print("Hello szerver!" + "\r" + "\n");


            while (termeljek == true) {


                synchronized (Products){
                    for (int i = 0; i < 10; i++) {
                        Products.add(1);
                        Products.notifyAll(); // szólunk mindenkinek hogy van ujra product
                    }
                }
                System.out.flush();





                Thread.sleep(1000); //majd randommal

                toszerver.print("PUT PRODUCT: " + getTermel() + "\r" + "\n");  //a szervernek ezt az üzenetet küldöm // \r \n el kuldom el
                toszerver.flush();


                    String szerversay = "111";
                    szerversay = fromszerver.readLine();

                    if(!szerversay.isEmpty()){
                        System.out.println("Server: " + szerversay);
                        //System.out.print(szerversay.length());
                        if(szerversay.equals("OK PRODUCT STORED")){
                            sikerestermeles = sikerestermeles +1;
                        }
                        if(szerversay.equals("NOPE PRODUCT REJECTED")){ //ha sokat termel akkor varnia kell
                            sikertelentermeles = sikertelentermeles +1;
                            Thread.sleep(0); //esetleg megszorozva a sokak szamaval, hogy kissebb legyen az esély a megállásra
                        }
                        if(szerversay.equals("You produce to much...")){
                            termeljek = false;
                        }


                    }





                Thread.sleep(RandomBetween(minrandom,maxrandom));
            }

            int osszkeres = sikertelentermeles +sikerestermeles;
            System.out.println("Sikertelen/Sikeres keres: " + sikertelentermeles + "/" + sikerestermeles + " Osszes keres: "+ osszkeres);

        } catch (IOException | InterruptedException e) {
            System.err.println("Server unreachable");
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



}