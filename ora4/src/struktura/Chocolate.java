package struktura;

import interfaces.Delicious;

public class Chocolate implements Delicious {
    @Override
    public String whatToEat() { return "Chocolate"; }

    @Override
    public int howDelicious() { return 2; }
}
