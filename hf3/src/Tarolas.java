
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.io.IOException;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * suti obijektum amit el kell tarolni
 */
class Suti {
    private int _sutiszam;

    public Suti(int sutiszam){
        _sutiszam = sutiszam;

    }
    public int getsutiszam(){

        return _sutiszam;
    }

}


public class Tarolas extends Thread {

    protected Socket clientSocket;
    protected BufferedReader clientReader;
    protected PrintWriter clientWriter;


    protected static Vector<Suti> Products = new Vector<Suti>(); //FiFo kell hogy legyen first in first out
    protected static Integer maxprod = 10; //maximalis product mennyiseg amennyit meg fifosan tarolni tudunk
    protected static Integer sokprod = 0;   //meri hogy hanyszor termelt a termelo tul sokat

    protected static Integer kevesprod = 0; //meri hogy hanyszor kert a fogyaszto tul sokat

    String beginnwith_put = "^PUT PRODUCT.*"; //megnezi, hogy mikor kezdodik put productal a termelo kerese
    Pattern pattern1 = Pattern.compile(beginnwith_put);

    String beginnwith_get = "^GET PRODUCT.*";
    Pattern pattern2 = Pattern.compile(beginnwith_get);

    String isnumber = "\\d+";   //ezzel vagom majd le a keres szam reszet, ami maga a product id
    Pattern patterN = Pattern.compile(isnumber);

    /**
     * itt adom hozza a product idjet a productok hoz, azert szinkronizalom, mert azt egyszerre tobb szal is el akarja majd erni
     *
     * @param prodid a product idje
     */
    public synchronized void set(int prodid) {

        Suti suti = new Suti(prodid);
        Products.add(suti);

    }

    /**
     * itt adom vissza a fogyasztonak a products fifo legtetején lévő productot, azert szinkronizállom, mert egyszerre tobb szal is kerhet productot
     *
     * @return a product id jét adom vissza fifosan
     */
    public synchronized int get() {

        Suti suti = Products.get(Products.size() - 1);
        int prodid = suti.getsutiszam();

        Products.remove(Products.size() - 1);

        return prodid;
    }


    /**
     * itt nyitom meg a klienssel valo kommunikaciohoz szükséges streameket
     *
     * @param clientSocket az adott kliens socketje
     * @throws IOException
     */
    public Tarolas(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));   //reader a kommunikációhoz
        this.clientWriter = new PrintWriter(clientSocket.getOutputStream());    //writer a kommunikációhoz
    }


    /**
     * itt kuldok a kuldeni egy sort a kliensnek, fontos , hogy  "\r\n" ez ott legyen a vegen, mert ez jelzi, hogy el kellküldeni
     *
     * @param line
     * @throws IOException
     */
    protected void sendLine(String line) throws IOException {   //elküld egy sort a kliensnek
        clientWriter.print(line + "\r\n");
        clientWriter.flush();
    }

    /**
     *  itt nézem meg, hogy mit akar a kliens a szervertől
     * @param matcher atadom neki a matchert
     * @return visszaadja hogy false, ha nem talalja benne a matcherben megadott kriteriumot
     * @throws IOException
     */
    protected boolean expect(Matcher matcher) throws IOException {  //ellenorzi hogy a kliens jol vlaszolt-e
        //sendLine("Mit akarsz tőlem?");
        //System.out.println("idáig jó?");
        //beker egy sort a klienstől
        //System.out.println("ezt mondta a kliens: " +clientLine);



        if (!matcher.find()) {    //baj van akkor ha a kliens nem mond semmit
            //System.out.println("nem ezt mondta " + line + "helyett " +clientLine);
            return false;
        }

        return true;
    }




    /**
     * A szerver tarolojának fő része, itt történik a kommunikáció a kliensekkel
     *
     */

    public void run() {
        System.out.println("Klienssel kommunikáció indul");


        try {

            long ThreadId = Thread.currentThread().getId();
            sendLine("Hello Kliens!" + "(" +ThreadId + ")");

            //System.out.println("Hello Kliens!" + "(" +ThreadId + ")");

            while (true) {

                String clientLine = clientReader.readLine();
                String szerverout = "";
                System.out.println("Kliens Mondta: " + clientLine);


                //System.out.println("read után?");
                Matcher matcherTermelo = pattern1.matcher(clientLine);
                if (Products.size() < maxprod && expect(matcherTermelo)) {

                    Matcher matcherN = patterN.matcher(clientLine);
                    if(matcherN.find()){

                        //System.out.println("test: " + matcherN.group());

                       set(Integer.parseInt(matcherN.group())); //a vegerol levagja a szamot, es belerakja a products ba
                    }

                    szerverout = "OK PRODUCT STORED"; //uj produkt fel lett veve
                    //System.out.println("product felveve");
                    sokprod = 0;

                } else if (Products.size() >= maxprod && expect(matcherTermelo)) {
                    szerverout = "NOPE PRODUCT REJECTED";
                    sokprod = sokprod + 1;
                    if (sokprod >= 2) {
                        System.out.println("Kliens tul sokat termel");
                        sendLine("You produce to much...");
                        return;
                    }

                }
                Matcher matcherFogyaszto = pattern2.matcher(clientLine);
                if (!Products.isEmpty() && expect(matcherFogyaszto)) {   //ha van product es helyesen ker a kliens

                    szerverout = "OK SENDING PRODUCT " + get();


                    kevesprod = 0;


                } else if (Products.isEmpty() && expect(matcherFogyaszto)) {//különben nem kap
                    szerverout = "NOPE TRY AGAIN";       //esetleg lehet ugy onjavitova tenni, hogy akkor lassabban kereget, ha latja hogy keves van
                    kevesprod = kevesprod + 1;
                    if (kevesprod >= 3) {
                        System.out.println("You request to much...");
                        sendLine("You request to much...");
                        return;
                    }
                }

                //egy sendline a végén
                if(szerverout != null){
                    sendLine(szerverout + "\r" + "\n");
                }

                System.out.println("szerver mondja a vegen: " + szerverout);

            }

            // clientSocket.close(); //csak akkor kell lezárni, ha valami baj van nem?
        } catch (IOException e) {
            e.printStackTrace();
        }






        //System.out.println("A klienssel a kommunikáció sikeres volt!");
    }


}