public class EndPr extends JavaHF{

    public void End(String name, int ident, String color) throws Exception{

        if(isOkPr(name, ident,color,-1,2)){ //ha type 2 akkor csak a vegehez trtozo reszeket ellenorzi
            _TypeofE.put(ident,2);
            //System.out.println("folyamat hozzaadva!");

        }

    }


}
