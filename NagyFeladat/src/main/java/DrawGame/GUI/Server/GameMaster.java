package DrawGame.GUI.Server;

import DrawGame.GUI.ReadPromts;

import java.util.Vector;

public class GameMaster extends Thread {
    int maxplayers = 8;
    static int  currentplayers = 8; //test input változtasd
    String[] allRealpromts = new String[currentplayers];
    static String[] PromtsThisRound = new String[currentplayers+1];
    String GoodPromtThisRound = "";
    Vector<Integer> players = new Vector<>();
    Vector<Integer> playersColor = new Vector<>();
    Vector<Integer> playersPoints = new Vector<>();

    GameMaster(int playercount){
        currentplayers = playercount;
    }


    //itt induljanak a gamestagek
    public void gamestage1(){



    }

    public void testpromtREMOVELATER(){
        ReadPromts RP = new ReadPromts();
        RP.readfile("promts.txt");


        for (int i = 0; i < currentplayers; i++) {
            PromtsThisRound[i] = RP.getOnePromt(); //KÉSÖBB NE FELEJSD EL KEVERNI

        }
        PromtsThisRound[currentplayers] = RP.getOnePromt();



    }



}
