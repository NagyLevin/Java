public class JavaHF extends process{




    public JavaHF(int ident, String color) throws Exception {
        this._ident = ident;

        //while (!isColor(color)){


        if(isColor(color)){
            this._color = color;

        }
        /*
        if(!isColor(color)){
            System.out.println("nincs iylen szin a rendszerben, probald ujra");

        }
        */

        else{
            throw new Exception("Hibas szin");
        }

        //}

        if(isUniqeId(_ident)){
            _Idents.add(_ident);

        }
        else{
            throw new Exception("Ident nem egyedi");
        }


    }



}

