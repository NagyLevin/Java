import java.util.Map;

public class edit extends JavaHF{

    //add
    //modify
    //remove

    public void removenext(int index) { //torli a rakovetkezo elemet

        if (_NextChain.containsKey(index)) {
            int elozo;
            int utana;
            for (Map.Entry<Integer, Integer> entry : _NextChain.entrySet()) {


                if (entry.getKey().equals(index)) {
                    System.out.println(entry.getValue());


                }


            }

        }

    }



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

    public void addnext(String name,int ident,int newident, String color,String condition) throws Exception { //elem rakov beszuras

        if (_NextChain.containsKey(ident)&& isColor(color) && !_NextChain.containsKey(newident)) {

            int nextid = -1;

            for (Map.Entry<Integer, Integer> entry : _NextChain.entrySet()) {
                //System.out.println(entry.getKey());
                if (entry.getKey().equals(ident)) {
                    //System.out.println(entry.getValue());
                    nextid = entry.getValue();


                }


            }
            if(nextid != -1) {
                String nextcolor = "";
                if (_TypeofE.get(nextid) != 4 && _TypeofE.get(nextid) != 3) {
                    nextcolor = _ColorofE.get(nextid);
                }
                String nextcondition = "";
                if (_TypeofE.get(nextid) == 4) {
                    nextcondition = _ConditionE.get(nextid);

                }

                String nextname = _NameofE.get(nextid);

                int nextofnext = _NextChain.get(nextid);


                //replace the next of ifent

                _NextChain.replace(ident, newident);
                _NextChain.replace(newident, nextofnext);

                _ColorofE.replace(newident, color);
                _NameofE.replace(newident, name);

                //replace the next of ifent

                //replace the next of next

                _NextChain.put(nextid, nextofnext);

                System.out.println(nextid);

                _NameofE.put(nextid, nextname);

                if (_TypeofE.get(nextid) != 4 && _TypeofE.get(nextid) != 3) {
                    _ColorofE.put(nextid, nextcolor);
                }
                if (_TypeofE.get(nextid) == 4 && !condition.equals("")) {
                    _ConditionE.put(nextid, nextcondition);

                }


                //replace the next of next
            }


        }else {
            throw new Exception("nincs ilyen idjű elem, vagy rossz szint adtal meg");
        }

    }



}


