package DrawGame.GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

public class ReadPromts {


    private Vector<String> vPromts = new Vector<>();
    Random Rand = new Random();

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
            String[] promts;
            promts = adat.split(",",-2); //-2 limit annyi darabra vagja szet amennyira lehet

            olvas.close();

            vPromts.addAll(Arrays.asList(promts));

        } catch (FileNotFoundException e) {
            System.out.println("Nincs ilyen nevü fajl");
        }

    }
    public void readpromts(){

        for (int i = 0; i < vPromts.size(); i++) {
            System.out.println(vPromts.get(i));

        }

    }

    public synchronized String getOnePromt(){

        String promt = vPromts.get(Rand.nextInt(0, vPromts.size()-1)); //2x ne legyen ugyan az, esetleg torold ki utána

        return promt;
    }





    public ReadPromts(){}


}
