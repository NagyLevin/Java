
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
    protected static Integer maxprod = 2;
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



                    while (true){



                    sendLine("Hello Kliens!");
                    //Thread.sleep(100);

                    String clientLine = clientReader.readLine();
                    System.out.println("Kliens Mondta: " + clientLine);

                        System.out.println("hello ez nem fut le");
                    }




               // clientSocket.close(); //csak akkor kell lezárni, ha valami baj van nem?
            } catch (IOException  e) {
                e.printStackTrace();
            }





        //System.out.println("A klienssel a kommunikáció sikeres volt!");
    }


}