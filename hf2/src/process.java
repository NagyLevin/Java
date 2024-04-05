import java.util.*;

/**
 * ez egy absztrkt class, az absztrakt függvenyeket es a tombokat tartalmazza amelyekben az adatokat tarolni fogom
 */

public abstract class process {



    protected static final List<String> _colors = Arrays.asList("red", "green", "blue"); //final, mert ezt nem akarjuk vlatoztatni

    protected static Map<Integer, String> _ColorofE = new HashMap<>();

    protected static Map<Integer, String> _NameofE = new HashMap<>();
    protected static Map<Integer, Integer> _TypeofE = new HashMap<>();

    protected static Map<Integer, String> _ConditionE = new HashMap<>();

    protected static Map<Integer, Integer> _NextChain = new HashMap<>();


    protected static int _next; //rakovetkezo elem azonositoja //utcsonak a rekovetkezoje -1 legyen

    protected  static boolean _nextisORAND = false;   //voltelagazas


    protected static Vector<Integer> _nexts = new Vector<Integer>();


    protected static int _current; //mostani elem


    protected int _firstid; //elso id
    protected boolean utolsofeladat = false; //lehet e meg feladatot fuzni a lachoz?



    public abstract void print();


    public abstract boolean isColor(String _color);

    public abstract boolean isUniqeId(int ident) throws Exception;

    protected abstract boolean isOkPr(String name,int ident, String color, int next, int type) throws Exception;




//nem kerterk, de esetleg egy valtozo, ami megmondja hogy hol tart a folyamat


}
