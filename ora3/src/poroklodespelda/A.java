package poroklodespelda;

public class A {

    static
    {
        System.out.println("A osztály static inicializáló blokkja lefutott.");
    }

    {
        System.out.println("A osztály példány inicializáló blokkja lefutott.");
    }

    A(){
        System.out.println("A konstruktora lefutott.");
    }

}

