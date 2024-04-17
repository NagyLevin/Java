package comperator;

import java.util.Collections;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        Table t1 = new Table(4);
        Table t2 = new Table(3);
        Table t3 = new Table(10);

        System.out.println("Table");
        LinkedList<Table> l = new LinkedList<Table>();
        l.add(t1);
        l.add(t2);
        l.add(t3);
        System.out.println("rendezés előtt:\t" + l);
        Collections.sort(l, new newTableComperator()); //nincsen szort megoldva, ezért egy extra Comperator osztalyt adunk me oda
        //lehet csinálni refaktorálással külön névtelen osztályt belöle
        //mostmár sikeresen rendezi

        System.out.println("rendezés után:\t" + l);

        System.out.println("");
        System.out.println("ComperableTable");

        ComparableTable ct1 = new ComparableTable(4);
        ComparableTable ct2 = new ComparableTable(3);
        ComparableTable ct3 = new ComparableTable(10);
        LinkedList<ComparableTable> ll = new LinkedList<ComparableTable>();
        ll.add(ct1);
        ll.add(ct2);
        ll.add(ct3);
        System.out.println("rendezés előtt:\t" + ll);
        Collections.sort(ll);
        System.out.println("rendezés után:\t" + ll);




    }
}
