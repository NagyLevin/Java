import java.util.Arrays;
import java.util.List;

public class Main {


    //kezd elorol lancolt lista mitajara.........................

    public static void main(String[] args) throws Exception {



        EndPr End = new EndPr();
        FirstPr Fr = new FirstPr();
        BetweenPr Mr = new BetweenPr();
        BranchPr Br = new BranchPr();
        JavaHF jhf = new JavaHF();
        edit E = new edit();



        Fr.Fr("elso",1,"red",2);

        //jhf.Pr("elso",1,"red",2);
        //System.out.println(Mr._Idents.size());

        Mr.Pr("elso",2,"red",3);

        Mr.Pr("elso",3,"green",4);
        //Mr.NewNextPr() //elagazas utan kellene adni

        E.remove(3);

        Mr.Pr("elso",4,"blue",5); //ha a nextje sajat maga akkor az a lanc vege

        Br.BrAnd("eselag",5, new int[]{5, 6, 7, 8, 9});

        Br.BrOr("vagyag",6,">3", new int[]{5, 6, 7, 8, 9});

        Br.BrAnd("customag",7, new int[]{5, 6, 7, 8, 9});

        End.End("elso",8,"blue");

        //jhf.Ex(5,"red",6);

        //vagy esetében kapok pénzt vagy kólát
        //pénzt minden esetben kapok pénzt és kólát


        //jhf.print();

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