import java.util.HashMap;
import java.util.Map;

public class BranchPr extends JavaHF {

    protected static Map<Integer, String> _Branches = new HashMap<>();

    public void BrOr(String name,int ident, String condition, int[] nexts) throws Exception {

        if(nexts.length > 5){
            throw new Exception("Túl sok elem lett megadva rakovetkezonek");
        }
        if(nexts.length == 0){
            throw new Exception("Nem lett next megadva");
        }

        isOkPr(name,ident,"",nexts[0],3); //elagazas ellenorzese


        //a branchnek az identjehez kellene eltarolni a potencialis rekovetkezo elemek szamat


        for (int i = 0; i < nexts.length; i++) {

            for (int j = 0; j < nexts.length; j++) {
                if(nexts[j] == nexts[i] && j != i){
                    throw new Exception("Az egyik Ident nem egyedi a megadottak közt");

                }
            }

            if( !isUniqeId(nexts[i])){
                throw new Exception("Az egyik Ident nem egyedi");
            }



            //System.out.println(nexts[i]);

        }
        //ha minden okes akkor elmentjuk az elagazas nevet es az elotte levo indexet
        _Branches.put(_current,name);
        _nextisORAND = true;
        for (int i = 0; i < nexts.length; i++) {
            _nexts.add(nexts[i]);

        }
        _next = _nexts.get(0);



       // System.out.println("elagazas hozzaadva");
    }

    public void BrAnd(String name,int ident, int[] nexts) throws Exception {
        if(nexts.length > 5){
            throw new Exception("Túl sok elem lett megadva rakovetkezonek");
        }

        isOkPr(name,ident,"",-10,3); //elagazas ellenorzese


        //a branchnek az identjehez kellene eltarolni a potencialis rekovetkezo elemek szamat


        for (int i = 0; i < nexts.length; i++) {

            for (int j = 0; j < nexts.length; j++) {
                if(nexts[j] == nexts[i] && j != i){
                    throw new Exception("Az egyik Ident nem egyedi a megadottak közt");

                }
            }

            if( !isUniqeId(nexts[i])){
                throw new Exception("Az egyik Ident nem egyedi");
            }



            //System.out.println(nexts[i]);

        }
        //ha minden okes akkor elmentjuk az elagazas nevet es az elotte levo indexet
        _Branches.put(_current,name);
        _nextisORAND = true;
        for (int i = 0; i < nexts.length; i++) {
            _nexts.add(nexts[i]);

        }
        _next = _nexts.get(0);


    }




}
