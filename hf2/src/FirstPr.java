public class FirstPr extends JavaHF {

    /**
     * Azzel a fuggvennyel veszek fel elso elemet
     *
     * @param name
     * @param ident
     * @param color
     * @param next
     * @throws Exception az isOkPr miatt dob , ami a JavaHF.java fajlban talalhato
     */

    public void Fr(String name,int ident, String color,int next) throws Exception{
        if(0 > ident){

            throw new Exception("Az id csak egy poyitiv egesz szam lehet");
        }

        if(isOkPr(name, ident,color,next,0)){ //ha type 0 akkor csak az elsohoz tartozo reszeket ellenorzi
            _TypeofE.put(ident,0);
            //System.out.println("folyamat hozzaadva!");

        }


    }


}
