import java.util.*;

public abstract class process {


    protected static final List<String> _colors = Arrays.asList("red", "green", "blue"); //final, mert ezt nem akarjuk vlatoztatni
    protected Map<Integer, String> _Idents = new HashMap<>();

    protected Map<Integer, Integer> _NextChain = new HashMap<>();


    protected int _next; //rakovetkezo elem azonositoja //utcsonak a rekovetkezoje -1 legyen

    protected int _firstid; //elso id
    protected boolean utolsofeladat = false; //lehet e meg feladatot fuzni a lachoz?

    public abstract void print();


    public abstract boolean isColor(String _color);

    public abstract boolean isUniqeId(int ident) throws Exception;

    protected abstract boolean isOkPr(String name,int ident, String color, int next) throws Exception;

    protected abstract boolean isOkEnd(String name,int ident, String color) throws Exception;


    protected abstract void Fr(String name,int ident, String color,int next) throws Exception; //kezdoelem
    protected abstract void End(String name,int ident, String color) throws Exception; //utcsoelem




    protected abstract void Pr(String name,int ident, String color,int next) throws Exception; //tevékenység


    protected abstract void Br(String name, String type, int[] nexts) throws Exception; //elagazas




//nem kerterk, de esetleg egy valtozo, ami megmondja hogy hol tart a folyamat


}
