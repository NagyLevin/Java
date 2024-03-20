package poroklodespelda;

public class C extends B {

    static
    {
        System.out.println("C osztály static inicializáló blokkja lefutott.");
    }

    {
        System.out.println("C osztály példány inicializáló blokkja lefutott.");
    }

    C(){
        System.out.println("C konstruktora lefutott.");
    }

    public static void main(String[] args) {
        C c=new C();
    }

}
