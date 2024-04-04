import java.util.Map;

public class edit extends JavaHF{

    //add
    //modify
    //remove

    public void remove(int index) { //torli a rakovetkezo elemet

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


    public void swap(String name,int ident, String color,int next){  //elem rekov modositas

        if (_NextChain.containsKey(ident)) {
            int elozo;
            int utana;
            for (Map.Entry<Integer, Integer> entry : _NextChain.entrySet()) {



                if (entry.getKey().equals(ident)) {
                    System.out.println(entry.getValue());



                }


            }

        }


    }

    public void add(String name,int ident, String color,int next){ //elem rakov beszuras

        if (_NextChain.containsKey(ident)) {
            int elozo;
            int utana;
            for (Map.Entry<Integer, Integer> entry : _NextChain.entrySet()) {

                if (entry.getKey().equals(ident)) {
                    System.out.println(entry.getValue());


                }


            }

        }

    }



}


