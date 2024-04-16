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

    /**
     * uj socket létrehozása egy szálhoz
     * @param host
     * @throws UnknownHostException
     * @throws IOException
     */
    public FogyasztoKliens(String host) throws UnknownHostException, IOException {
        clientSocket = new Socket(host, TaroloSzerver.PORT_NUMBER);
    }
    int minrandom = 300;    // minimális idő amit vár mielött fogyasztni akar
    int maxrandom = 500;    //maximális idő amennyit vár mielött fogyasztani akar

    int sikeresfogyaszt = 0;    //számolja hogy hányszor tudott az adott kliens sikeresen fogyasztani

    int sikertelenfogaszt =0; //számolja hogy hanysor akart túl sokat fogyasztani

    boolean fogyaszsak = true;  //a lent lévő while addig fut amíg a szerver azt nem mondja neki, hogy túl sokat fogyaszt


    String isnumber = "\\d+";   //ezzel tudom levágni a számot a szerver üzenetéből
    Pattern patterN = Pattern.compile(isnumber);
    protected static Vector<Integer> ProductsEaten = new Vector<Integer>(); //itt tárolom el a megevett termékeket


    /**
     * A várakozási időt randomizállja
     * @param min
     * @param max
     * @return visszaad egy értéket a min és a max param ok között
     */

    public int RandomBetween(int min, int max) {

        return (int) ((Math.random() * (max - min)) + min);
    }

    /**
     * szinkornizállja a szálakat, hiszen egyszerre akár több szál is akarhat valamit belerakni a vektorba
     * @param prodid a megevett termék id-je
     */
    public synchronized void set(int prodid) {
        ProductsEaten.add(prodid);

    }

    /**
     * ez a fogyasztokliens fő része, itt kér uj terméket a szerveről
     *
     *
     */

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

                String szerversay = "";
                szerversay = fromszerver.readLine();

                if(!szerversay.isEmpty()){
                    System.out.println("Server: " + szerversay);
                    //System.out.print(szerversay.length());

                    if(szerversay.equals("OK SENDING PRODUCT ")){

                        Matcher matcherN = patterN.matcher(szerversay);
                        if(matcherN.find()){

                            //System.out.println("test: " + matcherN.group());

                            set(Integer.parseInt(matcherN.group())); //a vegerol levagja a szamot, es belerakja a products ba
                        }

                        sikeresfogyaszt = sikeresfogyaszt +1;
                    }
                    if(szerversay.equals("NOPE TRY AGAIN")){ //ha sokat termel akkor varnia kell

                        sikertelenfogaszt = sikertelenfogaszt +1;
                        Thread.sleep(RandomBetween(100,150));//esetleg megszorozva a sokak szamaval, hogy kissebb legyen az esély a megállásra
                    }
                    if(szerversay.equals("You request to much...")){
                        fogyaszsak = false;
                    }


                }


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

    /**
     * Fogyasztókliensek itt kezdődnek, én a demohoz 4 fogyasztót állitottam be, a for ciklus segítségével
     * @param args
     */

    public static void main(String[] args) {
        try {
            for (int i = 0; i < 4; i++) {
                new Thread(new FogyasztoKliens("127.0.0.1")).start(); //ezen az ip címen probal majd csatlakozni
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}