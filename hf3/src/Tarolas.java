
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.io.IOException;

import java.util.Vector;

public class Tarolas extends Thread {

    protected Socket clientSocket;
    protected BufferedReader clientReader;
    protected PrintWriter clientWriter;


    protected static Vector<Integer> Products = new Vector<Integer>(); //FiFo kell hogy legyen first in first out
    protected static Integer maxprod = 10;
    protected static Integer sokprod = 0;

    protected static Integer kevesprod = 0;


    public Tarolas(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));   //reader a kommunikációhoz
        this.clientWriter = new PrintWriter(clientSocket.getOutputStream());    //writer a kommunikációhoz
    }

    protected void sendLine(String line) throws IOException {   //elküld egy sort a kliensnek
        clientWriter.print(line + "\r\n");
        clientWriter.flush();
    }

    protected boolean expect(String line,String clientLine) throws IOException {  //ellenorzi hogy a kliens jol vlaszolt-e
        //sendLine("Mit akarsz tőlem?");
        //System.out.println("idáig jó?");
        //beker egy sort a klienstől
        //System.out.println("ezt mondta a kliens: " +clientLine);



        if (! clientLine.equals(line)) {    //baj van akkor ha a kliens nem mond semmit
            //System.out.println("nem ezt mondta " + line + "helyett " +clientLine);
            return false;
        }

        return true;
    }

    public void run() {
        System.out.println("Klienssel kommunikáció indul");


        try {

            sendLine("Hello Kliens!");
            System.out.println("Hello Kliens!");

            while (true) {

                String clientLine = clientReader.readLine();
                String szerverout = "11";
                System.out.println("Kliens Mondta: " + clientLine);


                //System.out.println("read után?");

                if (Products.size() < maxprod && expect("I Have Goddies", clientLine)) {
                    Products.add(1);
                    szerverout = "prod+"; //uj produkt fel lett veve
                    //System.out.println("product felveve");
                    sokprod = 0;

                } else if (Products.size() >= maxprod && expect("I Have Goddies", clientLine)) {
                    szerverout = "sok";
                    sokprod = sokprod + 1;
                    if (sokprod >= 3) {
                        System.out.println("Kliens tul sokat termel");
                        sendLine("Túl sokat termelsz...");
                        return;
                    }

                }

                if (!Products.isEmpty() && expect("Give me goddies", clientLine)) {   //ha van product es helyesen ker a kliens

                    szerverout = "prod-";
                    Products.get(Products.size() - 1);
                    Products.remove(Products.size() - 1);
                    kevesprod = 0;


                } else if (Products.isEmpty() && expect("Give me goddies", clientLine)) {//különben nem kap
                    szerverout = "keves";       //esetleg lehet ugy onjavitova tenni, hogy akkor lassabban kereget, ha latja hogy keves van
                    kevesprod = kevesprod + 1;
                    if (kevesprod >= 3) {
                        System.out.println("Kliens tul sokat kér");
                        sendLine("Túl sokat kérsz...");
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