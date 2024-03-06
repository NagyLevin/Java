import java.util.TreeMap;
import java.util.*; //mindent
import java.util.LinkedList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);

        }


        int i = 10;
        float f = 10.0f;
        long l = 0xFA;
        long octal = 071;

        boolean b = (f == i);

        System.out.println(l);
        System.out.println(b);


        int[] anArray; //deklarálás
        anArray = new int[10]; //inicializalas
        //int[] anArray = { 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000 };
        anArray[0] = 100;
        anArray[1] = 200;
        anArray[2] = 300;
        anArray[3] = 400;
        anArray[4] = 500;
        anArray[5] = 600;
        anArray[6] = 700;
        anArray[7] = 800;
        //anArray[8] = 900; //dafault 0
        anArray[9] = 1000;

        System.out.println("Element at index 0: " + anArray[0]);
        System.out.println("Element at index 1: " + anArray[1]);
        System.out.println("Element at index 2: " + anArray[2]);
        System.out.println("Element at index 3: " + anArray[3]);
        System.out.println("Element at index 4: " + anArray[4]);
        System.out.println("Element at index 5: " + anArray[5]);
        System.out.println("Element at index 6: " + anArray[6]);
        System.out.println("Element at index 7: " + anArray[7]);
        System.out.println("Element at index 8: " + anArray[8]);
        System.out.println("Element at index 9: " + anArray[9]);

        double d1 = 10d;
        Double d2 = d1;
        Double d3 = d2;

        d1 = 20d;
        System.out.println((d1 + " " + d2 +" " + d3));
        d2 = 40d;
        System.out.println((d1 + " " + d2 +" " + d3)); //nem valtozik meg immutable

       //var aotumatikusam megondja a vegeredményt

        final Main m1 = new Main(); //final valtozo nem megvaltoztatható
        //m1 = new Main(); // HIBA
        //m1.i = 20;




    }
}
class SwitchDemo {
    public static void main(String[] args) {
        int month = 8;
        switch (month)
        {
            case 1: System.out.println("January"); break;
            case 2: System.out.println("February"); break;
            case 3: System.out.println("March"); break;
            case 4: System.out.println("April"); break;
            case 5: System.out.println("May"); break;
            case 6: System.out.println("June"); break;
            case 7: System.out.println("July"); break;
            case 8: System.out.println("August"); break;
            case 9: System.out.println("September"); break;
            case 10: System.out.println("October"); break;
            case 11: System.out.println("November"); break;
            case 12: System.out.println("December"); break;
            default: System.out.println("Invalid month."); break;
        }
    }
}
enum Day {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
}

//int[] tomb = { 5,2,3,4,1,2 };
//gyakfel1 f1 = new gyakfel1(tomb);