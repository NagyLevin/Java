import java.util.Map;

public class edit extends JavaHF{

    //add
    //modify
    //remove

    /**
     * Ez a fuggveny torli a megadott elemu elem nextjet, akkor ha az elem nem az utolso elem
     *
     * @param index
     * @throws Exception akkor dobja, ha az utolso elem idjet adjuk meg, amiunek nincsen rakovetkezoje
     */

    public void removenext(int index) throws Exception { //torli a rakovetkezo elemet

        if (_NextChain.containsKey(index)) {
            int nextid = -2;
            int nextofnext = -2;

            for (Map.Entry<Integer, Integer> entry : _NextChain.entrySet()) {

                if(_NextChain.get(index) == -1){
                    throw new Exception("az utolso elemnek nincsen rakovetkezoje");
                }


                if (entry.getKey().equals(index)) {
                   // System.out.println(entry.getValue());
                    nextid =entry.getValue();
                    if(nextid > -1){
                        nextofnext = _NextChain.get(nextid);
                    }





                }


            }

            if(nextofnext == -1){
                throw new Exception("az utolso elemnek nincsen rakovetkezoje");
            }

            _NextChain.replace(index,nextofnext);
            if(_NextChain.containsKey(nextid)){
                _NextChain.remove(nextid);
            }
            if(_ConditionE.containsKey(nextid)){
                _ConditionE.remove(nextid);
            }
            if(_NameofE.containsKey(nextid)){
                _NameofE.remove(nextid);
            }
            if(_TypeofE.containsKey(nextid)){
                _TypeofE.remove(nextid);
            }
            if(_ColorofE.containsKey(nextid)){
                _ColorofE.remove(nextid);
            }


        }

    }


    /**
     * Az @param ident  rakovetkezo elem adatait kicsereli a tobbi megadott parameterre
     * @param name
     * @param ident
     * @param color
     * @param condition
     * @throws Exception abban az esetben dob errort, ha valamelyik parameter nem megfelelő
     */
    public void swapnext(String name,int ident, String color,String condition) throws Exception {  //elem rekov modositas

        if (_NextChain.containsKey(ident) && isColor(color)) {


            for (Map.Entry<Integer, Integer> entry : _NextChain.entrySet()) {



                if (entry.getKey().equals(ident)) {

                    //System.out.println();
                    int nextid = entry.getValue();

                    //_ColorofE.get(nextid);
                    if(_TypeofE.get(nextid) != 4 && _TypeofE.get(nextid) != 3){


                    _ColorofE.replace(nextid,color);
                    }else {
                        System.out.println("A megadott elem nem elagazas nincsen szin mezoje");
                        System.out.println("szin resz kihagyva, tobbi beallitva");


                    }

                    _NameofE.replace(nextid,name);


                    if(_TypeofE.get(nextid) == 4){
                        _ConditionE.replace(nextid,condition);

                    }
                    else {
                        System.out.println("A megadott elem nem elagazas vagy nincsen condition mezoje");
                        System.out.println("condition resz kihagyva, tobbi beallitva");

                    }







                }



            }


        }else {
            throw new Exception("nincs ilyen idjű elem, vagy rossz szint adtal meg");
        }


    }

    /**
     * Berak egy uj rakovetkezo elemet az ident utan
     *
     * @param name
     * @param ident ez utan szurja be
     * @param newident ezzel az idexxel
     * @param color ez lesz az uj elem szine
     * @param condition ez lesz az uj elem feltetele, ha egy elagazas
     * @throws Exception  ha minden igaz akkor minden szélsoséges elsetet lefedtem, ahol mondjuk rossz helyre vagy rossz elemet akarunk beszúrni
     */

    public void addnext(String name,int ident,int newident, String color,String condition) throws Exception { //elem rakov beszuras

        if (_NextChain.containsKey(ident)&& isColor(color) && !_NextChain.containsKey(newident)) {

            int nextid = -2;

            for (Map.Entry<Integer, Integer> entry : _NextChain.entrySet()) {
                //System.out.println(entry.getKey());
                if (entry.getKey().equals(ident)) {
                    //System.out.println(entry.getValue());
                    nextid = entry.getValue();


                }


            }
            if(nextid != -2) {


                String nextname = _NameofE.get(nextid);

                int nextofnext = _NextChain.get(nextid);


                //replace the next of ifent

                _NextChain.replace(ident, newident);

                _NextChain.replace(newident, nextid);


                _NameofE.put(newident, name);

                //replace the next of ifent

                //replace the next of next

                _NextChain.put(nextid, nextofnext);


                _NameofE.put(nextid, nextname);

                if (_TypeofE.get(nextid) != 4 && _TypeofE.get(nextid) != 3) {
                    _ColorofE.put(newident, color);
                }
                if (_TypeofE.get(nextid) == 4 && !condition.equals("")) {
                    _ConditionE.put(newident, condition);

                }


                //replace the next of next
            }


        }else {
            throw new Exception("nincs ilyen idjű elem, vagy rossz szint adtal meg");
        }

    }



}


