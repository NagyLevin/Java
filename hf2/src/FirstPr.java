public class FirstPr extends JavaHF {

    public void Fr(String name,int ident, String color,int next) throws Exception{
        if(0 > ident){

            throw new Exception("Az id csak egy poyitiv egesz szam lehet");
        }

        if(isOkPr(name, ident,color,next)){

            //System.out.println("folyamat hozzaadva!");

        }


    }


}
