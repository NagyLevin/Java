package struktura;

import interfaces.Edible;

public class Spinach implements Edible {
    @Override
    public String whatToEat() { return "Spinach"; }
    public int howDelicious() { return 0; }

}