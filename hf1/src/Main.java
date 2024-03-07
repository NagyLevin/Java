import java.util.*;
import java.io.*;



public class Main {

   private Vector<Integer> vi = new Vector<Integer>();

   private Vector<String> vs = new Vector<String>();

   void readfile(String nev){
       try {
           File myfile = new File("teszt.txt");

           Scanner myReader = new Scanner(myfile);
           while (myReader.hasNextLine()) {
               String adat = myReader.nextLine();
               vs.add(adat);
               //System.out.println(adat);


           }

           myReader.close();

       } catch (FileNotFoundException e) {
           System.out.println("Nincs ilyen nevü fajl");
       }

   }


   void feldolgoz(){
       int index = 0; //mutatja hogy hanyadik sornal tartunk a beolvasasban
       String egysor = "";
       for (int i = 0; i < vs.size(); i++) {
           egysor = vs.get(i);

           if(i == 0){  //fajlneveket elmentem amelyek majd kesobb megnyilnak
               elsosor(egysor);

           }
           if(i == 1){ //adattipusokat elmentem
               masodiksor(egysor);

           }

       }

   }

   void elsosor(String sor){
       for (int i = 0; i < sor.length(); i++) {
           System.out.println(sor.charAt(i));


       }


   }
    void masodiksor(String sor){


   }


    public static void main(String[] args) {


        //Scanner input = new Scanner( System.in );         // Bemeneti csatorna objektum

        Main m = new Main();
        m.readfile("teszt.txt");
        m.feldolgoz();






        //System.out.println("Add meg a tomb meretet");
        //int meret = input.nextInt();



                                            /*

                                                        <S>: karakterlánc megkötés nélkül
                                                        <N>: szám (egész, vagy lebegőpontos)
                                                        <E>: email cím
                                                        <D>: dátum ÉÉÉÉ-HH-MM formátumban
                                                        <T>: időpont ÓÓ:PP:MM formátumban


                                             */


        System.out.println("");
        System.out.println("futas vege");
    }





}