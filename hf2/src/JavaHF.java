public class JavaHF extends process{




    public JavaHF()  {


    }


    public void Pr(String name,int ident, String color,int next, String description) throws Exception {

        if(isOk(name, ident,color,next)){

            System.out.println("folyamat hozzaadva!");

        }



    }




}

