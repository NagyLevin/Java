import java.util.Arrays;
import java.util.Scanner;


public class gyakfel1 {


    private int hossz;
    private int[] _tomb;

    public gyakfel1(int[] tomb) {
        hossz = tomb.length;
        _tomb = tomb;
    }

    int atlag(){
        int szum = 0;
        for (int i = 0; i < hossz; i++) {
            szum = szum + _tomb[i];


        }


        return szum / _tomb.length;
    }

    int min(){
        int mini = _tomb[0];
        int max =_tomb[0];
        for (int i = 0; i < hossz; i++) {
            if (_tomb[i] < mini){
                mini = _tomb[i];

            }


        }

        return mini;
    }
    int max(){

        int maxi =_tomb[0];
        for (int i = 0; i < hossz; i++) {
            if (_tomb[i] > maxi){
                maxi = _tomb[i];

            }


        }

        return maxi;
    }

    float median(){
        float median = 0f;

        Arrays.sort(_tomb);
        /*
        for (int i = 0; i < hossz; i++) {

            System.out.println(_tomb[i]);

        }
        */
        if(hossz %2 != 0){
            int index = ((hossz-1)/2);//mert 0 tol szamolunk
            System.out.println(index);
            median = _tomb[index];

        }
        else{
            int index = ((hossz)/2);
            float s1 = _tomb[index];
            float s2 = _tomb[index+1];

            median = (s1 + s2)/2;

        }


        return median;
    }



    public static void main(String[] arg)
    {
        Scanner input = new Scanner( System.in );         // Bemeneti csatorna objektum
        /*
        System.out.println("Add meg a tomb meretet");
        int meret = input.nextInt();                       // Felhasználói input beolvasása
        int[] tomb; //deklarálás
        tomb = new int[meret];
        for (int i = 0; i < meret; i++) {
           tomb[i] = input.nextInt();        //tfh szamot ad meg


        }
        */

        int[] tomb = { 5,2,3,4,1,2 };


        gyakfel1 gyak = new gyakfel1(tomb);

        System.out.println(gyak.atlag());
        System.out.println(gyak.max());
        System.out.println(gyak.min());
        System.out.println(gyak.median());



    }
}
