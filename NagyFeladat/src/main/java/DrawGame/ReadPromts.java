package DrawGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

public class ReadPromts {


    private  String[] promts;


    public void readfile(String nev){   //esetleg felesleges spacek eltavolitasat ird meg
        try {
            File myfile = new File(nev);

            String adat = "";
            Scanner olvas = new Scanner(myfile);
            while (olvas.hasNextLine()) {

                //promts.add(adat);
                //System.out.println(adat);
                adat = olvas.nextLine();

            }
            promts = adat.split(",",-2); //-2 limit annyi darabra vagja szet amennyira lehet

            olvas.close();

        } catch (FileNotFoundException e) {
            System.out.println("Nincs ilyen nevü fajl");
        }

    }
    public void readpromts(){

        for (int i = 0; i < promts.length; i++) {
            System.out.println(promts[i]);

        }

    }





    public ReadPromts(){}


}
