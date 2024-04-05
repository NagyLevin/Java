public class BetweenPr extends JavaHF{

    /**
     * itt lehet koztes elemeket hozzaadni
     *
     * @param name
     * @param ident
     * @param color
     * @param next
     * @throws Exception az isOkPr ellenorzo resz miatt
     */

    public void Pr(String name,int ident, String color,int next) throws Exception {

        if(0 > ident){

            throw new Exception("Az id csak egy poyitiv egesz szam lehet");
        }

        if(isOkPr(name, ident,color,next,1)){ //ha type 1 akkor csak a kozepso reszhez tartozo dolgokat ellenorzi


                _TypeofE.put(ident,1);



        }



    }


}
