package hu.ppke.itk.java.bingfight;

import java.io.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Bing-fight-ot megvalósítő osztály
 * @author rudanj
 *
 */
public class BingFight {
    private String fighter1, fighter2, bingSearch ="http://www.bing.com/search?setlang=en&q=";
    private BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

    /**
     * alapértelmezett fight
     */
    public BingFight()
    {
        // állítsuk a fighter1 és fighter2 stringeket kedvenc alapértékünkre
        fighter1 = "java";
        fighter2 = "python";

        // aztán indul a verseny
        fight();
    }

    /**
     * fight indítása
     */
    public void fight()
    {
        System.out.println("A talalatok szama a '" + fighter1 + "' szora: " + numOfResults(fighter1));
        System.out.println("A talalatok szama a '" + fighter2 + "' szora: " + numOfResults(fighter2));
    }

    /**
     * Keresőkifejezések megadása
     */
    public void setStrings()
    {
        try
        {
            // kérjük be a 2 versenyző stringet, és tároljuk el a fighter1 és fighter2-ben
            System.out.println("Fighter1:");
            fighter1 = stdIn.readLine();
            System.out.println("Fighter2:");
            fighter2 = stdIn.readLine();
            System.out.printf("Select terms: f1: %s f2: %s\n", fighter1, fighter2);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Találatok számának meghatározása
     * @param fighter - a keresőszavak megfelelő formátumú kombinációja
     * @return
     */
    public String numOfResults(String fighter)
    {
        try
        {
            URL myUrl=new URL(bingSearch + fighter);

            // hozzuk létre a gConn elnevezésú ULRConnection-t
            URLConnection gConn = myUrl.openConnection();
            // majd a myUrl osztály segítségével inicializáljuk

			gConn.addRequestProperty("User-Agent", "powa user"); //ez azert kell, hogy ne tudja mr. bing, hogy egy progi hasznalja a search engine-t. (ugyanis alapbol valami javaClient szeruseg lenne)

			// vegyuk át a gConn input stream-ját, és pakoljuk egy BufferedReader-be
            BufferedReader in = new BufferedReader(new InputStreamReader(gConn.getInputStream()));

			String fullText="", inputLine;
			while ((inputLine = in.readLine()) != null) {
				fullText+=inputLine;
			}
			in.close();

			Pattern pattern = Pattern.compile("([1-9]([0-9]|,)*) results");
			Matcher matcher = pattern.matcher(fullText);

            String result = null;

			if(matcher.find()) {
			    result = matcher.group(1);
			}

			return result;

        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
			e.printStackTrace();
		}
        return "Ha ez megy vissza, akkor valszeg tobb szobol raktak ossze az url-t. Azt kulon le kell kezelni.";
    }



}