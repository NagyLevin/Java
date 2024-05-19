package DrawGame.GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;


public class ReadPromts {


    private Vector<String> vPromts = new Vector<>();
    Random Rand = new Random();

    /**
     * Beolvas egy fájlt és elmenti a benne található promtokat, ezzel adok majd promtot a playereknek
     * @param nev a beolvasandó fájl neve
     */
    public void readfile(String nev){
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

    /**
     * Az összes promt kiiratása, csak tesztelésre
     */
    public void readpromts(){

        for (int i = 0; i < vPromts.size(); i++) {
            System.out.println(vPromts.get(i));

        }

    }

    /**
     * Visszaad egy random promtot a a tároltak közül, és törli, hogy ne lehessen véletlenül sem kétszer ugyanazt a promtot kapni
     * @return a random promt a promtos file-ból
     */
    public synchronized String getOnePromt(){

        int id =Rand.nextInt(0, vPromts.size()-1);
        String promt = vPromts.get(id); //2x ne legyen ugyan az, esetleg torold ki utána
        vPromts.remove(id);

        return promt;
    }





    public ReadPromts(){}


}
