import java.util.*;
public class process {



    protected List<String> _colors = Arrays.asList("red", "green", "blue");
    protected Map<Integer, String> _Idents = new HashMap<>();


    protected int _next; //rakovetkezo elem azonositoja //utcsonak a rekovetkezoje -1 legyen
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



        for (Map.Entry<Integer, String> entry :_Idents.entrySet()) {
            //System.out.println(entry.getKey() + " : " + entry.getValue());
            if(entry.getKey().equals(ident)){
                return false;
            }


        }

        return true;
    }

    public boolean isOk(int ident, String color) throws Exception {




        if(isColor(color)){
           // this._color = color;

        } else{
            throw new Exception("Hibas szin");
        }



        if(isUniqeId(ident)){
            _Idents.put(ident,color);

        } else{
            throw new Exception("Ident nem egyedi");
        }

        return true;
    }


//nem kerterk, de esetleg egy valtozo, ami megmondja hogy hol tart a folyamat


}
