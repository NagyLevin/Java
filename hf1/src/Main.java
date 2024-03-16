import java.util.*;
import java.io.*;
import java.util.regex.*;



public class Main {


   private Vector<String> vs = new Vector<>();
   private Vector<String> filenevek = new Vector<>();
    int adatindex = 0;//hol tartunk
   private Vector<String> adattipusok = new Vector<>();
   private String elvjel = ";";

   //erdemes lenne azt hasznalni?
   //StringBuffer sb = new StringBuffer("Szia!");



    //regex
   private static Pattern DATUM = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
   private static Pattern IDO = Pattern.compile("^\\d{2}:\\d{2}:\\d{2}$");
    private static Pattern MAIL = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static Pattern NUM = Pattern.compile("[0-9]+");
    private static Pattern STR = Pattern.compile("[A-Za-z]+");

    private static String customregex = "^[0-9]+[A-Za-z]$";
    private static Pattern CUSTOM = Pattern.compile(customregex); //custom regex


    /*
    public static String readFromFile(String source) throws IOException {
        return "";
    }
    */

    void readfile(String nev){
       try {
           File myfile = new File(nev);

           Scanner olvas = new Scanner(myfile);
           while (olvas.hasNextLine()) {
               String adat = olvas.nextLine();
               vs.add(adat);
               //System.out.println(adat);


           }

           olvas.close();

       } catch (FileNotFoundException e) {
           System.out.println("Nincs ilyen nevü fajl");
       }

   }




   void feldolgoz() throws IOException {
        //mutatja hogy hanyadik sornal tartunk a beolvasasban
       String egysor = "";
       for (int i = 0; i < vs.size(); i++) {
           egysor = vs.get(i);


           elsosorok(egysor,i);




       }

       for (int i = 0; i < filenevek.size(); i++) {
          // System.out.println(filenevek.get(i));
           //System.out.println(adattipusok.get(i));
           /*
           kiiras
           FileWriter fw = new FileWriter("a.txt");
           PrintWriter pw = new PrintWriter(fw);
           */

       }


   }

   void Irjfajlba(String melyikbe, String mit){


   }

   void sortwords(String word, int szoszam) throws IOException {

        //esetleg switchcase-vel
       //System.out.println(word+ "+");


       if(6 > adattipusok.size()){ //most abbol indultam ki, hogy minden adattipusnak kell lennie a fajlban
            //System.out.println("Nincs megadva eleg adattag");
       }

       if(5 == adattipusok.size()) {
           //new String("A").equals(new String("A")) esetleg majd igy ha kell


           if (adattipusok.contains("<S>")) {

               // System.out.println("<S>: karakterlánc megkötés nélkül");
               //System.out.printf(String.valueOf(STR.matcher(word).matches()));
               if(STR.matcher(word).matches()){
                   kiirat(word,filenevek.get(0));
                   System.out.println("lefut");
               }
               else{
                   kiirat("",filenevek.get(0));


               }
               //System.out.println(word);


           }

           if (adattipusok.contains("<N>") && szoszam == 1) {
               // System.out.println("<N>: szám (egész, vagy lebegőpontos)");
               // System.out.printf(String.valueOf(NUM.matcher(word).matches()));
               if(NUM.matcher(word).matches()){
                   kiirat(word,filenevek.get(1));

               }
               else{
                   kiirat("0",filenevek.get(1));
                   szoszam = szoszam + 1;
               }

               // System.out.println(word);


           }
           adatindex = adatindex +1;
           if (adattipusok.contains("<E>")  && szoszam == 2 ) {
               //  System.out.println("<E>: email cím");
               // System.out.printf(String.valueOf(MAIL.matcher(word).matches()));
               if(MAIL.matcher(word).matches()){
                   kiirat(word,filenevek.get(2));

               }
               else{
                   kiirat("nobody@example.com",filenevek.get(2));
                   szoszam = szoszam + 1;
               }

               //  System.out.println(word);


           }
           adatindex = adatindex +1;
           if (adattipusok.contains("<D>")  && szoszam == 3) {
               // System.out.println("<D>: dátum ÉÉÉÉ-HH-MM formátumban");
               // System.out.printf(String.valueOf(DATUM.matcher(word).matches()));
               if(DATUM.matcher(word).matches()){
                   kiirat(word,filenevek.get(3));

               }
               else{
                   kiirat("2000-01-01",filenevek.get(3));
                   szoszam = szoszam + 1;
               }

               //  System.out.println(word);

               // System.out.println(word);
           }
           adatindex = adatindex +1;
           if (adattipusok.contains("<T>") && szoszam == 4) {
               //  System.out.println("<T>: időpont ÓÓ:PP:MM formátumban"); //baj hogy a 60+ mspt is elfogadja?
               //System.out.printf(String.valueOf(IDO.matcher(word).matches()));
               if(IDO.matcher(word).matches()){
                   kiirat(word,filenevek.get(4));

               }
               else{
                   kiirat("00:00:00",filenevek.get(4));
                   szoszam = szoszam + 1;
               }

               //  System.out.println(word);
               //adatindex = adatindex +1;
               // System.out.println(word);

           }
           else {
               System.out.println("regex: " + word);
               //akkor regex?
           }
           adatindex = 0;
       }




   }




   void elsosorok(String sor,int index) throws IOException {




        String[] egySor = sor.split(elvjel,-2); //-2 limit annyi darabra vagja szet amennyira lehet

       for (int i = 0; i < egySor.length; i++) {



               if(index == 0){

                   filenevek.add(egySor[i]);

               }
               if(index == 1){

                   adattipusok.add(egySor[i]);

               }

               if(index > 1){
                  // System.out.println(egySor[i]);
                  // System.out.println(i);
                   sortwords(egySor[i],i);

               }



       }
       /*
       if(index == 0){

           filenevek.add(egySor[egySor.length-1]);

       }
       if(index == 1){

           adattipusok.add((egySor[egySor.length-1]));

       }
       else{
           sortwords(egySor[egySor.length-1],egySor.length-1);

       }
        */


   }

    public static String befajlnev() throws IOException {




        Scanner input = new Scanner( System.in );
        System.out.println("Add meg a fajl nevet: ");
        String userinput = "teszt.txt";//input.nextLine();


        return userinput;
    }

    public static void kiirat(String data, String destination) throws IOException {

        //FileWriter fw = new FileWriter(destination);
        //PrintWriter pw = new PrintWriter(fw);

        PrintWriter  file = new PrintWriter(new BufferedWriter((new FileWriter(destination))),true ); //true mert append //esetleg toroljuk a fajlok tartalmap minden uj futtataskor
        file.println(data);
        file.close();
        //pw.println(data);
        //pw.close();



    }


    public static void main(String[] args) throws IOException {




        Main m = new Main();



        String fajlnev = m.befajlnev();
        m.readfile(fajlnev);
        m.feldolgoz();

        System.out.println("");
        System.out.println("feldolgozas kesz :)");
    }





}