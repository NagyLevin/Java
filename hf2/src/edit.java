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

                //kulon gond van az elso az utolso es a kozepso elem eltavolitasanal
                //volt mar ilyen a lancolt listanal, vagy a binaris fanal

                if (entry.getKey().equals(index)) {
                    System.out.println(entry.getValue());


                }


            }

        }

    }


    public void modify(int index){  //elem rekov modositas


    }

    public void add(int index){ //elem rakov beszuras


    }



}


