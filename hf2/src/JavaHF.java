import java.util.Map;

public class JavaHF extends process{




    public JavaHF()  {


    }


    public void print(){ //!!!!!!!!!!!!!!!!!!felul kell deffinialni
        //System.out.println("azonositok: " + _Idents + "szin: " + _colors + "next: " + _next);

        for (int i = 0; i < _colors.size(); i++) {
            System.out.println(_colors.get(i) + " szin alatt futo feladatok: ");
            for (Map.Entry<Integer, String> entry :_Idents.entrySet()) {
                if(_colors.get(i).equals(entry.getValue())){
                    System.out.println(entry.getKey()); // es feladatneve

                }


            }
        }





    }; //elem ertekeinek kiiratasa

    public boolean isColor(String _color){

        if(_colors.contains(_color)){
            return true;
        }
        else
            return false;
    }

    public boolean isUniqeId(int ident) throws Exception {



        if(_Idents.isEmpty()){
            _firstid = ident; //elso id eltarolasa iegy elekszunk az elso ememre
        }


        for (Map.Entry<Integer, String> entry :_Idents.entrySet()) {
            //System.out.println(entry.getKey() + " : " + entry.getValue());
            if(entry.getKey().equals(ident)){
                return false;
            }


        }

        return true;
    }

    public boolean isOkPr(String name,int ident, String color, int next) throws Exception {

        if(utolsofeladat == true){
            throw new Exception("Nem lehet uj feladatot hozzaadni, mert mert mar volt utolso");
        }
        if(next == -1){
            utolsofeladat = true;
        }



        if(isColor(color)){
            // this._color = color;

        } else{
            throw new Exception("Hibas szin");
        }

        if(!_Idents.isEmpty() && ident != _next){
            //System.out.println(ident);
            //System.out.println(_next);


            throw new Exception("Nem ez a kovetkezo id");
        }else {
            _NextChain.put(ident,_next);//elmentem a lancolast

        }

        if(isUniqeId(next)){
            _next = next;
        } else{
            throw new Exception("next mar letezo elemre mutat");
        }

        if(_next == ident){
            throw new Exception("A megadott elem saját magára mutat");
        }

        if(isUniqeId(ident)){
            _Idents.put(ident,color); //elmentem hogy melyik szinehz tartozik


        } else{
            throw new Exception("Ident nem egyedi");
        }

        return true;
    }

    public void Fr(String name,int ident, String color,int next) throws Exception{
        if(0 > ident){

            throw new Exception("Az id csak egy poyitiv egesz szam lehet");
        }

        if(isOkPr(name, ident,color,next)){

            System.out.println("folyamat hozzaadva!");

        }


    }


    public void End(String name,int ident, String color) throws Exception{
        if(isOkPr(name, ident,color,-1)){

            System.out.println("folyamat hozzaadva!");

        }

    }


    public void Pr(String name,int ident, String color,int next) throws Exception {

        if(0 > ident){

            throw new Exception("Az id csak egy poyitiv egesz szam lehet");
        }

        if(isOkPr(name, ident,color,next)){

            System.out.println("folyamat hozzaadva!");

        }



    }

    public void Br(String name, String type, int[] nexts) throws Exception {

        if(nexts.length > 5){
            throw new Exception("Túl sok elem lett megadva rakovetkezonek");
        }




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




        System.out.println("elagazas hozzaadva");
    }





}

