package containers;

/**
 *
 * Generikus tároló doboz, a példányosításkor megadott típusú elemet lehet
 * beletenni. Például: {@code containers.Box<Integer> integerBox = new containers.Box<Integer>();}
 * Ekkor {@link Integer} típusú elemeket fogad el az objektum.
 *
 * @param <T>
 *
 *            Fontos észrevenni: - Az {@link #inspect(Object) inspect}
 *            metódusnak más a formális paramétere, mint az osztálynak - A
 *            {@code getClass().getName()} segítsegével megtudhatjuk, hogy mi
 *            behelyettesített típus, illetve ha adott metódus neve fölé állunk
 *            az IDE-ben, akkor megmutatja, hogy jelenleg milyen
 *            behelyettesített típusa van az adott metódusnak.
 *
 * @author zoltuz
 */
public class Box<T> {

    private T t;

    public void add(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    // Szintaxis: láthatóságikör <formális típus> [visszatérési érték]
    // metódusnév [kivételek]
    public <U> void inspect(U u) {
        System.out.println("T jelenlegi típusa: " + t.getClass().getName());
        System.out.println("U jelenlegi típusa: " + u.getClass().getName());
    }

    public static void main(String[] args) {
        Box<Integer> integerBox = new Box<Integer>();
        integerBox.add(10);
        // Álljunk az inspect szó fölé és nyomjunk F2-t!
        //
        // A metódus hívásakor nem kell paraméter típust átadni,
        // ehelyett a fordító automatikusan meghatározza
        // aktuális paraméter típusát véve alapul.
        integerBox.inspect("ez most egy String");
        System.out.println("------------------");
        Double dd = 54.2;
        integerBox.inspect(dd);

    }
}
