import java.util.*;
import java.io.*;



public class Main {

   private Vector<Integer> vi = new Vector<Integer>();

   private Vector<String> vs = new Vector<String>();
   private Vector<String> filenevek = new Vector<String>();
   private Vector<String> adattipusok = new Vector<String>();
   private Character elvjel = ';';

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

           if(i == 0 || i == 1){  //fajlneveket elmentem amelyek majd kesobb megnyilnak
               elsosorok(egysor,i);

           }


       }

   }

   void elsosorok(String sor,int index){
        String egyszo = "";
       for (int i = 0; i < sor.length(); i++) {
           //System.out.println(sor.charAt(i));

           if(sor.charAt(i) == elvjel){
               if(index == 0){
                   filenevek.add(egyszo);

               }
               if(index == 1){
                   adattipusok.add(egyszo);

               }
               //System.out.printf(egyszo+ ",");
               egyszo = "";
           }
           else{
               egyszo = egyszo + sor.charAt(i);

           }

       }
       filenevek.add(egyszo);

       for (int i = 0; i < filenevek.size(); i++) {
           System.out.println(filenevek.get(i));
       }


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