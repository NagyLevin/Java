package poroklodespelda;

public class B extends A {

    static
    {
        System.out.println("B osztály static inicializáló blokkja lefutott.");
    }

    {
        System.out.println("B osztály példány inicializáló blokkja lefutott.");
    }

    B(){
        System.out.println("B konstruktora lefutott.");
    }

}
