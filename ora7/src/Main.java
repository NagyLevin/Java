import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {


        /*
        List<Integer> myIntList = new LinkedList<Integer>();
        myIntList.add(new Integer(0));
        Integer x = myIntList.iterator().next();
        */
        //HELYETT EZT HASZNÁLJUK
        List<Integer> integerList = new ArrayList<>();



        // Ebből
        List<String> words = new ArrayList<String>();
        words.add("Hello ");
        words.add("world!");
        String s = words.get(0) + words.get(1);               // "Hello world!"

        // Ez lesz
        List words2 = new ArrayList();
        words.add("Hello ");
        words.add("world!");
        String s2 = ((String) words.get(0)) + ((String) words.get(1));  // "Hello world!"
        /*
        E (Element) - Elemek (Collections Framework használja)
        K (Key) - Kulcs
        N (Number) - Szám
        T (Type) - Típus
        V (Value) - Érték
        S, U stb. - További típusok
        */

        //public class containers.Box <U extends Number> = new containers.Box<>() ;
        //Akkor ha kötelezni akarjuk erre a tipusra

        /*
        Class<?> c;
        public void printList(List<?> list1){}
        ? az wildkard olyan parameterre hasznaljuk, aminek nem tudjuk a tipusát
        */
        /*
        public void processObjectList(List<Object> list){}
        public void processWildcardList(List<?> list){}
        //...
        ArrayList<Integer> intList = new ArrayList<>();
        processObjectList(intList); //fordítási hiba
        processWildcardList(intList);
        */
        /*
        Iterable<T> list = new ArrayList<T>;
        for (T t : list) {
            t.doSomething();
        }
        */
        //foreach
        /*
        Collection<T> collection = ...;
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (removalCheck(element)) {
                iterator.remove();
            }
          }
*/
        //nincsen túlterhelés


        //Lehetőségünk van a szűrés, rendezés, stb. eredményét újra valamilyen collectionbe tenni. Az alábbi példa a lista elemei közül azt tartja meg, amely tartalmaz "a" karaktert. A szűrt streamben levő stringeket kigyűjti egy új ArrayList példányba.

        List<String> l = new ArrayList<>();
        l.add("test1");
        l.add("test2");
        l.add("test3");
        l.add("test4");
        ArrayList<String> a = l.stream()
                .distinct()
                .filter(e -> e.contains("2"))
                .collect(Collectors.toCollection(ArrayList::new));

        a.stream().forEach(e->System.out.println(e));
        //A szűréshez pedig lehetséges külső függvényt is megadni:

       // boolean pontravegzodik = se.stream().anyMatch(str -> str.endsWith("."));


        //HA STREAMET HASZNÁLSZ AKKOR AZ OBIJEKTUMOT NEM TUDOD/AKAROD KÖZBEN MÓDOSITANI

    }
}