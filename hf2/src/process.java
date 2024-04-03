import java.util.*;

public abstract class process {


    protected static final List<String> _colors = Arrays.asList("red", "green", "blue"); //final, mert ezt nem akarjuk vlatoztatni
    protected static Map<Integer, String> _Idents = new HashMap<>();

    protected static Map<Integer, Integer> _NextChain = new HashMap<>();


    protected static int _next; //rakovetkezo elem azonositoja //utcsonak a rekovetkezoje -1 legyen

    protected int _firstid; //elso id
    protected boolean utolsofeladat = false; //lehet e meg feladatot fuzni a lachoz?

    public abstract void print();


    public abstract boolean isColor(String _color);

    public abstract boolean isUniqeId(int ident) throws Exception;

    protected abstract boolean isOkPr(String name,int ident, String color, int next) throws Exception;



//nem kerterk, de esetleg egy valtozo, ami megmondja hogy hol tart a folyamat


}
