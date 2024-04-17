package containers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Példa osztály típusparaméter nélküli osztályban lévő generikus metódusra,
 * illetve a wildcard operátorra.
 *
 * @author kozcs
 *
 */
public class Printer {

    /**
     * Az osztálynak nincs generikus típusparamétere. Viszont a metódusainak még
     * lehet! Ez a metódus kiírja a konzolra a bementi objektumot leíró
     * információkat.
     *
     * @param t
     *            a kiírandó objektum
     */
    public <T> void print(T t) {
        System.out.println(t.toString()); 	// a toString() hívást megtehetjük,
                                            // mivel az Object-nek,
                                            // így minden Java osztálynak van
    }

    /**
     * Ez a metódus olyan listát fogad, melynek bármilyen típusparamétere lehet.
     * Kiírja az összes elemét a konzolra.
     *
     * @param l
     *            a lista a kiírandó elemekkel
     */
    public void printList(List<?> l) {
        for (Object e : l)
            System.out.println(e);
        System.out.println();
    }

    /**
     * Korlátot is adhatunk a típushalmaznak (jelen esetben felső korlátot). Ez
     * a fv. kiírja a listában szereplő szám double értékét.
     *
     * @param l
     *            a lista a kiírandó elemekkel
     */
    public void printDoubleValues(List<? extends Number> l) {
        for (Number e : l)
            System.out.println(e.doubleValue()); 	// a Number definiálja a
        // doubleValue metódust,
        // tehát a leszármazottjai
        // is öröklik
        System.out.println();
    }

    /**
     * Természetesen van alsó korlát is.
     *
     * Ez a függvény átmásolja a számokat az egyik listából a másik végére.
     *
     * @param from
     *            a lista a másolandó elemekkel
     * @param to
     *            a lista, melynek végére kerülnek az elemek
     */
    public void copyNumbers(List<? extends Number> from, List<? super Number> to) {
        for (Number number : from) {				// Ehhez kellett az extends
            to.add(number);							// Ehhez kellett a super
        }
    }

    public static void main(String[] args) {
        Printer p = new Printer();

        p.print(2);
        p.print("Hello world!");

        List<Integer> l = Arrays.asList(1, 2, 3);

        p.printList(l);
        p.printDoubleValues(l);

        List<Double> l2 = Arrays.asList(2.3,4d);
        ArrayList<Number> result = new ArrayList<>();
        p.copyNumbers(l,result);
        p.copyNumbers(l2,result);
        p.printDoubleValues(result);
    }
}
