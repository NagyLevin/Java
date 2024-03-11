import java.util.*;
import java.io.*;
import java.util.regex.*;



public class Main {


   private Vector<String> vs = new Vector<>();
   private Vector<String> filenevek = new Vector<>();
   private Vector<String> adattipusok = new Vector<>();
   private Character elvjel = ';';

   //regex
   private static Pattern DATUM = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
   private static Pattern IDO = Pattern.compile("^\\d{2}:\\d{2}:\\d{2}$");
    private static Pattern MAIL = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static Pattern NUM = Pattern.compile("[0-9]+");
    private static Pattern STR = Pattern.compile("[A-Za-z]+");



    void readfile(String nev){
       try {
           File myfile = new File(nev);

           Scanner olvas = new Scanner(myfile);
           while (olvas.hasNextLine()) {
               String adat = olvas.nextLine();
               vs.add(adat);
               //System.out.println(adat);


           }

           olvas    .close();

       } catch (FileNotFoundException e) {
           System.out.println("Nincs ilyen nevü fajl");
       }

   }




   void feldolgoz(){
        //mutatja hogy hanyadik sornal tartunk a beolvasasban
       String egysor = "";
       for (int i = 0; i < vs.size(); i++) {
           egysor = vs.get(i);


           elsosorok(egysor,i);




       }

       for (int i = 0; i < filenevek.size(); i++) {
          // System.out.println(filenevek.get(i));
           //System.out.println(adattipusok.get(i));

       }


   }

   void Irjfajlba(String melyikbe, String mit){


   }

   void sortwords(String word){

        //esetleg switchcase-vel
       //System.out.println(word+ "+");


       if(0 > adattipusok.size()){
          // System.out.println("nincsenek megadva adattipusok regex meg lehet");
       }
        if(adattipusok.contains("<S>")){
           // System.out.println("<S>: karakterlánc megkötés nélkül");
            //System.out.printf(String.valueOf(STR.matcher(word).matches()));
            //System.out.println(word);

        }
       if(adattipusok.contains("<N>")){
          // System.out.println("<N>: szám (egész, vagy lebegőpontos)");
          // System.out.printf(String.valueOf(NUM.matcher(word).matches()));
          // System.out.println(word);

       }
       if(adattipusok.contains("<E>")){
         //  System.out.println("<E>: email cím");
           // System.out.printf(String.valueOf(MAIL.matcher(word).matches()));
          //  System.out.println(word);

       }
       if(adattipusok.contains("<D>")){
          // System.out.println("<D>: dátum ÉÉÉÉ-HH-MM formátumban");

          // System.out.printf(String.valueOf(DATUM.matcher(word).matches()));
          // System.out.println(word);
       }
       if(adattipusok.contains("<T>")){
         //  System.out.println("<T>: időpont ÓÓ:PP:MM formátumban"); //baj hogy a 60+ mspt is elfogadja?
           //System.out.printf(String.valueOf(IDO.matcher(word).matches()));
           //System.out.println(word);

       }
       else{
           //akkor regex?
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

               if(index > 1){
                   //System.out.println(egyszo);
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
           egyszo = "";
       }
       if(index == 1){

           adattipusok.add(egyszo);
           egyszo = "";
       }
       else{
           sortwords(egyszo);
           egyszo = "";
       }



   }



    public static void main(String[] args) {


        Scanner input = new Scanner( System.in );         // Bemeneti csatorna objektum

        Main m = new Main();

        System.out.println("Add meg a fajl nevet: ");
        String fajlnev = "teszt.txt";//input.nextLine();
        m.readfile(fajlnev);
        m.feldolgoz();






        //System.out.println("Add meg a tomb meretet");
        //int meret = input.nextInt();


        System.out.println("");
        System.out.println("feldolgozas kesz :)");
    }





}