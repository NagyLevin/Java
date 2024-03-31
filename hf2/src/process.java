import java.util.*;
public class process {

    protected int _ident; //azonositok kod

    protected String _color; //szin

    protected List<String> _colors = Arrays.asList("red", "green", "blue");

    Vector<Integer> _Idents = new Vector<>();

    protected int _next; //rakovetkezo elem azonositoja //utcsonak a rekovetkezoje -1 legyen
    public void print(){
        System.out.println("azonositok: " + _ident + "szin: " + _color + "next: " + _next);
    }; //elem ertekeinek kiiratasa

    public boolean isColor(String _color){

        if(_colors.contains(_color)){
            return true;
        }
        else
            return false;
    }

    public boolean isUniqeId(int _ident){
        for (int i = 0; i < _Idents.size(); i++) {
            if(_Idents.get(i) == _ident){

                return false;
            }
        }
        return true;
    }

    //nem kerterk, de esetleg egy valtozo, ami megmondja hogy hol tart a folyamat


}
