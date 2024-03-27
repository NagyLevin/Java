package struktura;

import interfaces.Delicious;

public class Apple implements Delicious {
    @Override
    public String whatToEat() { return "Apple"; }

    @Override
    public int howDelicious() { return 10; }


}