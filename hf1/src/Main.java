import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

   private Vector<Integer> vi = new Vector<Integer>();

   private Vector<String> vs = new Vector<String>();
   private Vector<String> filenevek = new Vector<String>();
   private Vector<String> adattipusok = new Vector<String>();
   private Character elvjel = ';';

   //regex
   private static Pattern DATUM = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");


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
        //mutatja hogy hanyadik sornal tartunk a beolvasasban
       String egysor = "";
       for (int i = 0; i < vs.size(); i++) {
           egysor = vs.get(i);

           if(i == 0 || i == 1){  //fajlneveket elmentem amelyek majd kesobb megnyilnak
               elsosorok(egysor,i);

           }
           else{
                sortwords(egysor);

           }


       }

       for (int i = 0; i < filenevek.size(); i++) {
           System.out.println(filenevek.get(i));
           //System.out.println(adattipusok.get(i));

       }


   }


   void sortwords(String word){

        //esetleg switchcase-vel
       if(0 > adattipusok.size()){
           System.out.println("nincsenek megadva adattipusok regex meg lehet");
       }
        if(adattipusok.contains("<S>")){
            System.out.println("<S>: karakterlánc megkötés nélkül");
        }
       if(adattipusok.contains("<N>")){
           System.out.println("<N>: szám (egész, vagy lebegőpontos)");

       }
       if(adattipusok.contains("<E>")){
           System.out.println("<E>: email cím");

       }
       if(adattipusok.contains("<D>")){
           System.out.println("<D>: dátum ÉÉÉÉ-HH-MM formátumban");

       }
       if(adattipusok.contains("<T>")){
           System.out.println("<T>: időpont ÓÓ:PP:MM formátumban");

       }


       System.out.printf(String.valueOf(DATUM.matcher(word).matches()));


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
               if(index > 1){
                   sortwords(egyszo);
               }
               //System.out.printf(egyszo+ ",");
               egyszo = "";
           }
           else{
               egyszo = egyszo + sor.charAt(i);

           }

       }
       if(index == 0){

           filenevek.add(egyszo);

       }
       if(index == 1){

           adattipusok.add(egyszo);

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








                                             */


        System.out.println("");
        System.out.println("futas vege");
    }





}