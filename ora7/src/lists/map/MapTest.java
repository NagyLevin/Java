package lists.map;

import java.util.*;

/**
 *
 * Osztály a különböző Map implementációk sebességkülönbségének vizsgálatára.
 *
 */


public class MapTest {
    public static int RandomBetween(int min, int max) {

        return (int) ((Math.random() * (max - min)) + min);
    }


    public static void main(String[] args) {



        Map<Integer, String> hashMap = new HashMap<Integer, String>();
        Map<Integer, String> treeMap = new TreeMap<Integer, String>();
        Map<Integer, String> linkedHashMap = new LinkedHashMap<Integer, String>();

        // Feladat: töltsd fel a fenti adatszerkezeteket random kulcs-érték
        // párokkal (mondjuk 1-10 millió elemmel) és teszteld le a feltöltés
        // sebességét


        long start = startCounter(hashMap,"hashmap");
        for (int i = 0; i < 1000000; i++) {
            hashMap.put(i,Integer.toString(RandomBetween(1,1000000)));


        }
        printTime(start);

        start = startCounter(treeMap,"treeMap");
        for (int i = 0; i < 1000000; i++) {
            treeMap.put(i,Integer.toString(RandomBetween(1,1000000)));


        }
        printTime(start);
       start = startCounter(linkedHashMap,"linkedHashMap");
        for (int i = 0; i < 1000000; i++) {
            linkedHashMap.put(i,Integer.toString(RandomBetween(1,1000000)));


        }
        printTime(start);

        // Feladat: teszteld le a kulcs alapján történő keresés sebességét




        // Feladat: teszteld le a bejárásokat, figyeld meg a sorrendezést!

    }

    private static long startCounter(Map<Integer, String> map, String message) {
        System.out.println(map.getClass().getSimpleName() + " " + message);
        return System.currentTimeMillis();
    }

    private static void printTime(long start) {
        System.out.println("idő: " + (System.currentTimeMillis() - start));
    }
}
//treemap a leglassabb