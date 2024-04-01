import java.util.*;
public abstract class process {



    protected static final List<String> _colors = Arrays.asList("red", "green", "blue"); //final, mert ezt nem akarjuk vlatoztatni
    protected Map<Integer, String> _Idents = new HashMap<>();


    protected int _next; //rakovetkezo elem azonositoja //utcsonak a rekovetkezoje -1 legyen

    protected int _firstid; //elso id
    protected boolean utolsofeladat = false; //lehet e meg feladatot fuzni a lachoz?

    public abstract void print();


    public abstract boolean isColor(String _color);

    public abstract boolean isUniqeId(int ident);

    protected abstract boolean isOkPr(String name,int ident, String color, int next) throws Exception;



    protected abstract void Pr(String name,int ident, String color,int next) throws Exception;


    protected abstract void branch(String name, String type, int[] nexts) throws Exception;




//nem kerterk, de esetleg egy valtozo, ami megmondja hogy hol tart a folyamat


}
