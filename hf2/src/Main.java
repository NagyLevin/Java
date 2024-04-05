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



        Fr.Fr("elso111",1,"red",2);

        //jhf.Pr("elso",1,"red",2);
        //System.out.println(Mr._Idents.size());

        Mr.Pr("elso222",2,"red",3);

        Mr.Pr("elso333",3,"green",4);
        //Mr.NewNextPr() //elagazas utan kellene adni



        Mr.Pr("elso",4,"blue",50); //ha a nextje sajat maga akkor az a lanc vege

        Br.BrAnd("eselag",50, new int[]{5, 6});

        Br.BrOr("vagyag",5,">3", new int[]{7});

        Br.BrAnd("customag",7, new int[]{8});

        End.End("elso",8,"blue");

        //swap, remove , add
        E.swapnext("elso1",3,"red","");
        E.addnext("masodik",1,10,"green","");

        System.out.println(process._NameofE.get(10));
        //System.out.println(process._NextChain.get(4));
        //System.out.println(process._NameofE.get(1));



        jhf.print();




        System.out.println("feladatok vege");
    }


}