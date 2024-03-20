import autok.*;



import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("brumm brumm");
        /*
        ArrayList<Taxi> taxis = new ArrayList<>(); //lehet csinalni egy autos listat is
        taxis.add(new Taxi())
        taxis.add(new ETaxi())
        */

        //Car autok = new Car("ford",10,100,10);

        //altipusos polimorfizmus miatt a car elfogadja a taxi konstruktorat is
        /*
        Car a = new Car("Subaru", 10, 4, 0);
        Car b = new Taxi("Honda", 5, 5, 300);
        */

        /*
        Car a = new Car("Subaru", 10, 4, 0);
        Car b = new Taxi("Honda", 5, 5, 300);
        System.out.println(a.toString());
        System.out.println(b.toString()); // A Taxi toString()-je fog lefutni
         */

        //Az instanceof operátor egy megadott objektumot hasonlít egy megadott típushoz:
        /*
        if (b instanceof Taxi) {
            Taxi c = (Taxi) b;
            c.transfer(100);
        }
        */


        try {
            Car lada = new Car("Lada", 10, 5);
            System.out.println(lada.toString());

            System.out.println("Tankolunk 40 litert.");
            lada.refuel(40);
            System.out.println("Megyünk 15 km-t.");
            lada.go(15);
            System.out.println(lada.toString());
            System.out.println("");

            Taxi daewoo = new Taxi("Daewoo", 7, 5, 200);
            System.out.println(daewoo.toString());

            System.out.println("Tankolunk a taxival 30 litert.");
            daewoo.refuel(30);
            System.out.println("Megteszönk a taxival 15 km-t.");
            daewoo.go(15);
            System.out.println("Ennyibe kerülne fejenként, ha 4-en mennénk a Taxival 200 km-ert: " + daewoo.costPerPerson(200, 4)); // 815.625
            System.out.println("Taxi fuvaroz 200 km-t.");
            daewoo.transfer(200);
            System.out.println(daewoo.toString());
            System.out.println("");

            Bus ikarus = new Bus("Ikarus", 7, 30, 100);
            System.out.println(ikarus.toString());
            System.out.println("Jegy ára: " + Bus.getTicketPrice());
            System.out.println("Tankolunk a busszal 10 litert.");
            ikarus.refuel(10);
            System.out.println("Utazunk 30-an 1 km-t.");
            ikarus.transfer(1, 30);
            System.out.println(ikarus.toString());
            System.out.println("");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        /*
        try {
            Car a = new Car("Subaru", 10, 4, 0);
            Car b = new Taxi("Honda", 5, 5, 300);
            System.out.println(a.toString());
            System.out.println(b.toString());
            a.refuel(20);
            a.go(100);
            System.out.println(a.toString());
            b.refuel(20);
            b.go(100);
            System.out.println(b.toString());
            if (b instanceof Taxi) {
                Taxi c = (Taxi) b;
                System.out.println("\n_Taxival fuvarozunk 100 km-t_");
                c.transfer(100);
            }
            System.out.println(b.toString());
            System.out.println("");
            System.out.println("\n_Jarmu osztaly hasznalata_\n");
            Vehicle[] temp = new Vehicle[4];
            temp[0] = a;
            temp[1] = b;
            temp[2] = new Bicycle("Magellan");
            temp[3] = new Bus("Ikarus", 15, 30, 200);
            for (Vehicle j : temp) {
                System.out.println(j.toString());
                j.go(10);
                System.out.println(j.toString());
                System.out.println("");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        */


    }

}
