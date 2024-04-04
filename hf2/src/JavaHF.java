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

    public boolean isOkPr(String name,int ident, String color, int next,int type) throws Exception {

        if(utolsofeladat == true){
            throw new Exception("Nem lehet uj feladatot hozzaadni, mert mert mar volt utolso");
        }
        if(next == -1){
            utolsofeladat = true;
        }

        if(type == 0 ||type == 1 || type == 2){



        if(isColor(color)){
            // this._color = color;

        } else{
            throw new Exception("Hibas szin");
        }
        }

        //mindenképpen
        if(!_Idents.isEmpty() && ident != _next){
            //System.out.println(ident);
            //System.out.println(_next);


            throw new Exception("Nem ez a kovetkezo id");
        }else {
            _NextChain.put(ident,next);//elmentem a lancolast

        }


        //mindenkeppen
        if(isUniqeId(next)){
            _next = next;

        } else{
            throw new Exception("next mar letezo elemre mutat");
        }

        //mindenkeppen
        if(_next == ident){
            throw new Exception("A megadott elem saját magára mutat");
        }

        if(isUniqeId(ident)){
            _Idents.put(ident,color); //elmentem hogy melyik szinehz tartozik


        } else{
            throw new Exception("Ident nem egyedi");
        }

        _current = ident;

        return true;
    }













}

