import java.util.*;
import java.io.*;
import java.util.regex.*;



public class Main {


   private Vector<String> vs = new Vector<>(); //fajl beolvasasa ebbe a vektorba
   private Vector<String> filenevek = new Vector<>(); //egy vektor a fileneveknek, amelyekbe kesobb beleirjuk majd az adatokat
   private Vector<String> adattipusok = new Vector<>();//egy vektor az adattipusoknak
   private String elvjel = ";"; //elvalasztojel ide kihozva, hogy konnyebben meg lehessen valtozatni

   //erdemes lenne azt hasznalni?
   //StringBuffer sb = new StringBuffer("Szia!");



    //regex
   private static Pattern DATE = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
   private static Pattern TIME = Pattern.compile("^\\d{2}:\\d{2}:\\d{2}$");
    private static Pattern MAIL = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static Pattern NUM = Pattern.compile("[0-9]+");
    //private static Pattern STR = Pattern.compile("[A-Za-z]+");

    private static String customregex = ""; //ezt kulon a fajlbol olvassuk be felteszem azt hogy csak 1 custom regex lehet

    //regex


    /**
     *
     * @param nev a fajlnevet megkapva kiolvassuk a fajlban levo adatokat sorrol sorra, es egy vektorban taroljuk eloket
     *
     *
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


    /**
     *
     * sorrol sorra feldolgozza a vektorban tarolt fajlt elkuldi az elsosorok függvénynek, a fajl sorszamat, es magat a sort
     *
     */

   void feldolgoz() throws IOException {

       String egysor = "";
       for (int i = 0; i < vs.size(); i++) {
           egysor = vs.get(i);


           elsosorok(egysor,i);




       }




   }


    /**
     *
     * @param szavak a szavak arrayben levo szavakon vegigmegy egy for ciklussal
     *               megnezi, hogy teljesulnek a szavakra a feltetelek, es csak az elso matchet iratja ki fajlba
     *                 a kiiratas a @kiirat fv vel tortenik
     *
     *
     */

   void sortwords(String[] szavak) throws IOException {


        //esetleg switchcase-vel
       //System.outs.println(word+ "+");
       Boolean BSTR = false;
       Boolean BNUM = false;
       Boolean BMAIL = false;
       Boolean BDATE = false;
       Boolean BTIME = false;




       for (int i = 0; i < szavak.length; i++) {

            //most csak 1 szer adja hozza a sorban levo adattipust, akkor is, ha tobbszor szerepel a sorban
           if (adattipusok.contains("<S>") && !BSTR) {
               if (!NUM.matcher(szavak[i]).matches() && !MAIL.matcher(szavak[i]).matches() && !DATE.matcher(szavak[i]).matches() && !TIME.matcher(szavak[i]).matches() && !szavak[i].matches(customregex)) {
                   kiirat(szavak[i], filenevek.get(0));
                   BSTR = true;
               }
           }
           if (adattipusok.contains("<N>") && !BNUM) {
               if (NUM.matcher(szavak[i]).matches()) {
                   kiirat(szavak[i], filenevek.get(1));
                   BNUM = true;
               }
           }
           if (adattipusok.contains("<E>") && !BMAIL) {
               if (MAIL.matcher(szavak[i]).matches()) {
                   kiirat(szavak[i], filenevek.get(2));
                   BMAIL = true;
               }
           }
           if (adattipusok.contains("<D>") && !BDATE) {
               if (DATE.matcher(szavak[i]).matches()) {
                   kiirat(szavak[i], filenevek.get(3));
                   BDATE = true;
               }
           }
           if (adattipusok.contains("<T>") && !BNUM) {
               if (TIME.matcher(szavak[i]).matches()) {
                   kiirat(szavak[i], filenevek.get(4));
                   BTIME = true;
               }
           }
           if (adattipusok.size() > 5 ) {

               //System.out.println(customregex);
               if (szavak[i].matches(customregex)) {

                   kiirat(szavak[i], filenevek.get(5));

               }
           }



       }

       if (adattipusok.contains("<S>") ) {
           if (BSTR == false) {
               kiirat("", filenevek.get(0));

           }
       }
       if (adattipusok.contains("<N>") ) {
           if (BNUM== false) {
               kiirat("0", filenevek.get(1));

           }
       }
       if (adattipusok.contains("<E>") ) {
           if (BMAIL == false) {
               kiirat("nobody@example.com", filenevek.get(2));

           }
       }
       if (adattipusok.contains("<D>") ) {
           if (BDATE == false) {
               kiirat("2000-01-01", filenevek.get(3));

           }
       }
       if (adattipusok.contains("<T>") ) {
           if (BTIME == false) {
               kiirat("00:00:00", filenevek.get(4));
           }
       }










   }

    /**
     *
     * @param sor a megkapott sort elsplitteli az elvalasztojel menten
     *
     *
     * @param index az egysor tombon vegigmegy egy for ciklus, es az elso ket sornal beija az elsplittelt tomb mezoit a fajlnevek es az adattipusok adattarolokba
     *
     *
     *
     */


   void elsosorok(String sor,int index) throws IOException {




        String[] egySor = sor.split(elvjel,-2); //-2 limit annyi darabra vagja szet amennyira lehet

       for (int i = 0; i < egySor.length; i++) {



               if(index == 0){

                   filenevek.add(egySor[i]);

               }
               if(index == 1){

                   adattipusok.add(egySor[i]);
                   if(adattipusok.size() > 5){
                       customregex = egySor[i];
                   }
                   torolfajl();
               }





       }
       if(index > 1){
           // System.out.println(egySor[i]);
           // System.out.println(i);
           sortwords(egySor);

       }



   }


    /**
     *
     * @return visszaadja a beolvasott fajlnevet
     *
     */
    public static String befajlnev() throws IOException {




        Scanner input = new Scanner( System.in );
        System.out.println("Add meg a fajl nevet: ");
        String userinput = "test1.txt";//input.nextLine();


        return userinput;
    }

    /**
     *
     * torli a fajlokat, azert hogy ne appendelodjenek vegtelensegig uj adatok a fajlokba
     *
     *
     */

    public void torolfajl() throws IOException {

        for (int i = 0; i < this.filenevek.size(); i++) {

            String fajlnev =  this.filenevek.get(i);
            PrintWriter  file = new PrintWriter(new BufferedWriter((new FileWriter(fajlnev))));

            file.close();



        }


    }

    /**
     *
     *
     * @param data az adatot kiirja egy fajlba
     * @param destination ebbe a fajlba írja ki a kapott adatot
     * @throws IOException akkor ha valameylik mezo ures
     *
     *
     *
     */

    public static void kiirat(String data, String destination) throws IOException {

        //FileWriter fw = new FileWriter(destination);
        //PrintWriter pw = new PrintWriter(fw);

        FileWriter  pw = new FileWriter( destination , true);
        //System.out.println(data);
        //pw.append(data);
        pw.write(data);
        pw.write('\n'); //newline
        pw.close();

        //pw.println(data);
        //pw.close();



    }



    public static void main(String[] args) throws IOException {




        Main m = new Main();



        String fajlnev = m.befajlnev();//fajlnev beolvasas
        m.readfile(fajlnev);//fajlnevvel azonositott fajl beolvasasa
        m.feldolgoz();  //beolvasott fajl feldolgozasa

        System.out.println("");
        System.out.println("feldolgozas kesz :)");
    }





}