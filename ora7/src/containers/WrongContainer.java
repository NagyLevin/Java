package containers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class WrongContainer {
    private Object element;
    public void add(Object element) {
        this.element = element;
    }
    public Object get() {
        return element;
    }



    public static void main(String[] args) {
        WrongContainer integerBox = new WrongContainer();
        integerBox.add(10);
        Integer someInteger = (Integer) integerBox.get();
        System.out.println("A tárolt szám:" + someInteger);
        System.out.println("Kérlek adj meg egy számot ");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            // Mivel Object-et tárol, ezért Stringet is.
            // A String az Object leszármazottja, tehát betehetem.
            WrongContainer integerBox2 = new WrongContainer();
            integerBox2.add(br.readLine());
            // A kód fordul, hisz a típuskonverzió futásidőben fog megtörténni.
            // De nincs alapertelmezett konverzió String és Integer között!!!
            // Az Integer az Object leszármazottja, tehát átkonvertálhatom!
            Integer storedInteger = (Integer) integerBox2.get();
            // Rossz feltételezéssel éltünk a belső reprezentációról!
            // Itt segítenek a Java generikusai!
            // Nézzük meg a taroló helyes megoldását
            System.out.println("A tárolt szám:" + storedInteger);
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }
}
