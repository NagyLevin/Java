public class EndPr extends JavaHF{

    /**
     * ezzel a fugvennyel veszek fel uj utolso elemet, a fuggverny csak egyszer fut l, hiszen csak egy utolso elem lehet
     * @param name
     * @param ident
     * @param color
     * @throws Exception az ellenorzos resz miatt
     */

    public void End(String name, int ident, String color) throws Exception{

        if(isOkPr(name, ident,color,-1,2)){ //ha type 2 akkor csak a vegehez trtozo reszeket ellenorzi
            _TypeofE.put(ident,2);
            //System.out.println("folyamat hozzaadva!");

        }

    }


}
