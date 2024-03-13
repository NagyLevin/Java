import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
class MyException extends Exception {

}
class ExceptionTest1 {
    public static void main(String[] args) {
        try {
            throw new Exception("Exception");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            throw new MyException("MyException");
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }
}
*/

public class Main {

    public String readFirstLineFromFile(String path) throws IOException {
        //Bájt alapú	InputStream	OutputStream
        //Karakter alapú	Reader	Writer

        BufferedReader br = new BufferedReader(new FileReader(path));
        try { //lehet try blockot generaltatni is
            return br.readLine();
        } finally {
            if (br != null) br.close();
        }
    }

    public static String readFromKeyboard() throws IOException {
        /*...*/
        return "s";
    }
    //ha rossz szamot adsz meg, akkor exceptiont ad ra
    public class AssertionSample {
        public static void main(String[] args) {
            // beolvasunk a billentyűzetről
            Scanner input = new Scanner(System.in);
            System.out.println("Adj meg egy számot 0 és 10 között: ");
            // feltételezzük, hogy integerrel lesz dolgun
            int number = input.nextInt();
            // megköveteljük, hogy mikor lesz jó a number-ünk:
            // ha nem 0-10 között adunk meg egész számot, akkor kivétel váltódik ki
            assert (number >= 0 && number <= 10) : "rossz szám: " + number + "! A számnak 0-10 között KELL lennie!";
            System.out.printf("Az általad megadott szám: %d\n", number);
            input.close();
        }
    }


    public static void main(String[] args) {


        //kodolasi hiba
        //külsö hiba
        //adathiba string helyett int
        //throw exceptions
        //kell majd sajat kivetel osztaly leszarmaztatva az exceptions osztályból

        //ne igy csinaljuk, hanem
        Boolean semmi = new String("Hello world") == new String("Hello world");
        //ket sztringet igy kell osszehasonlitani
        new String("A").equals(new String("A"));
        /*
        String fajlmuveletek
        substring():  String egy része kivágható
        split(): a String-et egy reguláris kifejezéssel darabokra vágja, String tömbben kapjuk vissza a darabokat.
                trim(): levághatók a whitespace-ek a String elejéről és végéről
        toLowerCase(), toUpperCase()
        */

        /*
            pythonszeru kiiratas
            System.out.printf("Local time: %tT", Calendar.getInstance());
            Eredmény: Local time: 15:49:25
            System.out.printf("%4$2s, %3$2s, %2$2s, %1$2s", "a" ,"b", "c", "d");
            Eredmény: " d,  c,  b,  a"
            //https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/io/PrintStream.html#printf(java.lang.String,java.lang.Object...)

         */

        /*
        Stringek jobb kezelese
        StringBuffer sb = new StringBuffer("Szia!");
        sb.append(" Hi!").append(" Üdv!").reverse();
        //https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/lang/StringBuilder.html
         */



    }
}