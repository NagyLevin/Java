
public class Parameterek {
    private int a;
    public Parameterek (int a)
    {
        this.a = a;
    }

    void addOne()
    {
        a++;
    }

    int get()
    {
        return a;
    }

    /**
     *
     * @param arg
     *
     *
     *
     *
     *
     *
     *
     */

    //Függvények osztályok legyenek annotálva

    public static void main(String[] arg)
    {
        Parameterek elso = new Parameterek(1);
        Parameterek masodik = elso;
        System.out.println(elso.get());
        System.out.println(masodik.get());
        masodik.addOne();
        elso.addOne();
        System.out.println(elso.get());
        System.out.println(masodik.get());
    }
}

/*
Házi konvenciók
Projektek elnevezése:

<shibboleth azonosító>_hf_<hf sorszáma>
A projektből a következő fájlokat kell beadni (NEM tömörítve!)

A teljes projektmappa tartalma (.idea mappa, src mappa, iml állomány)
Kivéve az out mappa (erre nincs szükség)
Mindegyik házit a megfelelő (hf sorszám, moodle alapján) mappába kell tenni

Minden házi JavaDoc dokumentációval kell ellátni.
 */