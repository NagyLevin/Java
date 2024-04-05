import java.util.Map;

/**
 * ez a class minden elem ose, ebben vannak azok a fvnyek amelyeket minden ra epulo osztaly hasznalni fog
 */

public class JavaHF extends process{




    public JavaHF()  {


    }

    /**
     * print fvny szinek szerint csoportositva kiirja a bele tartozo elemeket
     * kis modositassal nev vagy tipus szerint is ki lehet iratni az elemeket
     *
     */

    public void print(){ //!!!!!!!!!!!!!!!!!!felul kell deffinialni
        //System.out.println("azonositok: " + _Idents + "szin: " + _colors + "next: " + _next);

        for (int i = 0; i < _colors.size(); i++) {
            System.out.println(_colors.get(i) + " szin alatt futo feladatok: ");
            for (Map.Entry<Integer, String> entry :_ColorofE.entrySet()) {

                if(_colors.get(i).equals(entry.getValue())){

                    System.out.println(entry.getKey()); // es feladatneve

                }


            }
        }





    }; //elem ertekeinek kiiratasa

    /**
     *
     * @param _color megmonja hogy a szin amit bemenetul adtunk benne van a process classban elore deffinialt szinek kozott
     * @return ha benne van akkor igazzal ter vissza
     */

    public boolean isColor(String _color){

        if(_colors.contains(_color)){
            return true;
        }
        else
            return false;
    }

    /**
     *
     * @param ident ellenorzi hogy a parameterul kapott ident benne van-e a mar elore felvett identek kozott, amelyek egy mapban tarolodnak a process absztrkt classban
     * @return ha benne van akkor hamissal ter vissza ami ezk utan errort fog majd kivaltani
     *
     */

    public boolean isUniqeId(int ident) {



        if(_NameofE.isEmpty()){
            _firstid = ident; //elso id eltarolasa iegy elekszunk az elso ememre
        }


        for (Map.Entry<Integer, String> entry :_NameofE.entrySet()) {
            //System.out.println(entry.getKey() + " : " + entry.getValue());
            if(entry.getKey().equals(ident)){
                return false;
            }


        }

        return true;
    }

    /**
     *
     * @param ident megnezi hogy benne van e a nexts vectorban amiben a potencialis next elemeket tarolom, ebben a vektorba kerulnek azok az elemek amelyeket az elagazasok elore lefoglalnak esetleges rakovetkezöjukknet
     * @return ha van ilyen next benne akkor azt felhasznaljuk rakovetkezo id kent, es toroljuk az esetleges rakovok közül
     */

    public boolean isInNexts(int ident){
        for (int i = 0; i < _nexts.size(); i++) {
            if(_nexts.get(i).equals(ident)){
                _nexts.remove(i);
                return true;
            }

        }

        //System.out.println(_nexts);
        return false;
    }

    /**
     * ez a program fő ellenorzo resze, itt dontom el hogy az egyes paramoterek megfelelnek, illetve abban az esetben ha megfelelnek akkor hozzaadom oket a mapokhoz ahol az elemeket tarolom
     *
     * @param name
     * @param ident
     * @param color
     * @param next
     * @param type
     * @return
     * @throws Exception akkor dob Exception-t ha valamelyik parameter rosszul lett megadva
     */


    public boolean isOkPr(String name,int ident, String color, int next,int type) throws Exception {

        if(_nextisORAND == true && _nexts.size() == 0){
            _nextisORAND = false;
        }



        if(_nextisORAND == true){

           if(!isInNexts(ident)){
               //System.out.println(_nexts);

               throw new Exception("nincs az elore megadott nextek kozott az ident!");

           }


        }



        if(utolsofeladat == true){
            throw new Exception("Nem lehet uj feladatot hozzaadni, mert mert mar volt utolso");
        }
        if(next == -1){
            utolsofeladat = true;
        }
        if(next < -1){
            throw new Exception("rossz a next");

        }


        if(type == 0 ||type == 1 || type == 2){



        if(isColor(color)){
            // this._color = color;

        } else{
            throw new Exception("Hibas szin");
        }
        }


        if(!_NextChain.isEmpty() && ident != _next ){
            //System.out.println(ident);
            //System.out.println(_next);

            if(_nextisORAND == false){
                throw new Exception("Nem ez a kovetkezo id");
            }


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
            _ColorofE.put(ident,color); //elmentem hogy melyik szinehz tartozik
            _NameofE.put(ident,name);
            _TypeofE.put(ident,type);


        } else{
            throw new Exception("Ident nem egyedi");
        }

        _current = ident;

        return true;
    }













}

