package containers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CorrectContainer<E> {
    private E element;
    public void add(E element) {
        this.element = element;
    }
    public E get() {
        return element;
    }
    public static void main(String[] args) {
        // Ez a tároló csak Integereket enged tárolni!
        CorrectContainer<Integer> integerBox = new CorrectContainer<Integer>();
        // Java SE 7-től diamond operátort is használhatunk:
        // containers.CorrectContainer<Integer> integerBox = new containers.CorrectContainer<>();
        // Próbáld meg kicselezni!
        // integerBox.add("10"); // Stringet próbálunk belerakni
        // Beteszünk egy számot a dobozba
        integerBox.add(10);
        Integer someInteger = integerBox.get();
        System.out.println("A tárolt szám:" + someInteger);
        System.out.println("Kérlek adj meg egy számot ");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            // Létrehozunk egy Interek tárolására alkalmas tarolót.
            CorrectContainer<Integer> integerBox2 = new CorrectContainer<Integer>();
            // Viszont Stringet nem fogad el, már fordítási időben sem!
            // integerBox2.add(br.readLine());
            // Helyes megoldás: Integer bementet adunk neki
            integerBox2.add(Integer.parseInt(br.readLine()));   //parse int átkonvertálja számmá
            // Visszatérésnél sem kell már konvertálni.
            Integer tarolt2 = integerBox2.get();
            System.out.println("A tárolt szám:" + tarolt2);
            // Természetesen lehet mást is tárolni, mint Integert
            CorrectContainer<Double> doubleBox = new CorrectContainer<Double>();
            // Az alábbi miért nem jó?
            // doubleBox.add(10);
            /*
             * Mert generikus tipuskent csak objektumokat használhatunk,
             * a 10 egy new Integer(10) objektumba csomagolódik,
             * Integert pedig nem lehet Double-é castolni, csak primitív esetben.
             */
            doubleBox.add(10d);
            /*
             * Mivel a parameter típus Double, ettol nem enged eltérni a Java és
             * ezt már fordítási időben ellenőrzi!
             */
            // doubleBox.add(new Integer(10));
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }
}
