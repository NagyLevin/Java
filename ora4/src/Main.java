import interfaces.*;

import struktura.*;

public class Main {
   // interfaceben olyan dolgokat rakj, amelyeket minden vagy tobb osztalyban is hasznalni fogsz
    //csak általános dolgokat ne használjuk belső szerkezet meghatározásához

    /*
    Beéptett interfacek:
    Comparable : összehasonlításhoz
    Cloneable : marker interfész, engedélyezi az Object clone() metódusát
    Serializable : marker interfész, engedélyezi egy objektum „sorosítását”, azaz olyan szöveggé alakítását, amiből később visszaállítható
    Runnable : szálként futtatható
     */


    public static void main(String[] args) {
        Edible[] edibleArray = new Edible[3];
        Delicious[] deliciousArray = new Delicious[2];
        deliciousArray[0] = new Chocolate();
        deliciousArray[1] = new Apple();
        // deliciousArray[2] = new Spinach(); // Ezt nem lehet, mert a Spinach nem Delicious
        edibleArray[0] = deliciousArray[0];
        edibleArray[1] = deliciousArray[1];
        edibleArray[2] = new Spinach();

        System.out.println("Delicious foods:");
        for (int i = 0; i < deliciousArray.length; i++) {
            System.out.println(deliciousArray[i].whatToEat() + ", delicious: " + deliciousArray[i].howDelicious());
        }

        System.out.println();
        System.out.println("Edible foods:");
        for (int i = 0; i < edibleArray.length; i++) {
            // System.out.println(edibleArray[i].whatToEat() + " delicious: " + edibleArray[i].howDelicious());
            // Ezt nem lehet, mert az edibleArray[i] nem Delicious
            System.out.println(edibleArray[i].whatToEat());
        }

        System.out.println();
  /*Edible a = new Edible(){
   @Override public String whatToEat(){
     return "AnonymousFood";
   }
  };
  System.out.println(a.whatToEat());*/
    }
}