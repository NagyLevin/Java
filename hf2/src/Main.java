import java.util.Arrays;
import java.util.List;

public class Main {




    public static void main(String[] args) throws Exception {



        JavaHF jhf = new JavaHF();

        jhf.Pr("elso",1,"red",2);

        //jhf.Pr("elso",1,"red",2);

        jhf.Pr("elso",2,"red",3);

        jhf.Pr("elso",3,"green",4);

        jhf.Pr("elso",4,"blue",4); //ha a nextje sajat maga akkor az a lanc vege

        jhf.branch("eselag","AND", new int[]{5, 6, 7, 8, 9});

        jhf.branch("vagyag","OR", new int[]{5, 6, 7, 8, 9});

        jhf.branch("customag","CUSTOM", new int[]{5, 6, 7, 8, 9});


        //jhf.Ex(5,"red",6);




        jhf.print();

        //otletek holnapra
        /*
        jhf.folyamat(nev,id,szin,
        jhf.elagazas(nev, krit(ersd es vagy egyedi,ha egyedi akkor hozzaad(ez mondjuk lehet olyan harmadlagos csoportositos kulcs cucc))
            next, de honnan tudom hogy mi a nextje?
            elagazas utan nezd hogy mikor volt a legutobbi elagazas max 5
         */




        System.out.println("feladatok vege");
    }


}