package DrawGame.GUI;

import DrawGame.ReadPromts;

public class GameMaster {
    int maxplayers = 8;
    int currentplayers = 8; //test input változtasd
    String[] allRealpromts = new String[currentplayers];
    String[] PromtsThisRound = new String[currentplayers+1];
    String GoodPromtThisRound = "";


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
