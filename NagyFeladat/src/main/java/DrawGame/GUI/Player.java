package DrawGame.GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player {
    private int[] palyercolor = new int[3];
    private boolean amIhost = false;
    private String playername = "";
    String givenpromt = "TesztPromt";
    private String fakepromt = "";
    int numofcolors = 2;
    int playerid ;




    public void setPlayerName(String name){

        playername = name;
    }
    public int getPlayerColoR(){

        return palyercolor[0];

    }
    public int getPlayerColoB(){

        return palyercolor[2];

    }
    public int getPlayerColoG(){

        return palyercolor[1];

    }
    public void setPlayerColoR(int coloR){

        palyercolor[0] = coloR;

    }
    public void setPlayerColoB(int coloB){

        palyercolor[2] = coloB;

    } public void setPlayerColoG(int coloG){

        palyercolor[1] = coloG;

    }





}
