public class BetweenPr extends JavaHF{

    public void Pr(String name,int ident, String color,int next) throws Exception {

        if(0 > ident){

            throw new Exception("Az id csak egy poyitiv egesz szam lehet");
        }

        if(isOkPr(name, ident,color,next,1)){ //ha type 1 akkor csak a kozepso reszhez tartozo dolgokat ellenorzi

           // System.out.println("folyamat hozzaadva!");

        }



    }


}
