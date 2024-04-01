import java.util.*;
public class process {



    protected List<String> _colors = Arrays.asList("red", "green", "blue");
    protected Map<Integer, String> _Idents = new HashMap<>();


    private int _next; //rakovetkezo elem azonositoja //utcsonak a rekovetkezoje -1 legyen

    protected int _firstid; //elso id
    private boolean utolsofeladat = false; //lehet e meg feladatot fuzni a lachoz?

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

    public boolean isUniqeId(int ident){

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

    public boolean isOk(String name,int ident, String color, int next) throws Exception {

        if(utolsofeladat == true){
            throw new Exception("Nem lehet uj feladatot hozzaadni, mert mert mar volt utolso");
        }


        if(isColor(color)){
           // this._color = color;

        } else{
            throw new Exception("Hibas szin");
        }

        if(isUniqeId(next)){
           _next = next;
        } else{
            throw new Exception("next mar letezo elemre mutat");
        }

        if(_next == ident){
            utolsofeladat = true;
        }

        if(isUniqeId(ident)){
            _Idents.put(ident,color);

        } else{
            throw new Exception("Ident nem egyedi");
        }

        return true;
    }



    protected void Pr(String name,int ident, String color,int next) throws Exception {

        if(isOk(name, ident,color,next)){

            System.out.println("folyamat hozzaadva!");

        }



    }


    protected abstract void branch(){


    }




//nem kerterk, de esetleg egy valtozo, ami megmondja hogy hol tart a folyamat


}
