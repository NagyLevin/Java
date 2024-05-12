package DrawGame.GUI;

import DrawGame.GUI.hosting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player implements Runnable{
    private int[] palyercolor = new int[3];
    private boolean amIhost = false;
    private String playername = "";
    String givenpromt = "TesztPromt";
    private String fakepromt = "";
    int numofcolors = 2;
    int playerid ;
    String joincode ="";
    String ip = "";
    protected Socket clientSocket;

    Player(String _playername,int[] _playercolor, String _joincode,String _ip){

        playername = _playername;
        palyercolor = _playercolor;
        joincode = _joincode;
        ip = _ip;
        try {
            clientSocket = new Socket(ip, hosting.PORT_NUMBER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




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


    @Override
    public void run() {
        System.out.println("client join is running");
        try {
            BufferedReader fromszerver = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  //ezt kuldi a szerver
            PrintWriter toszerver = new PrintWriter(clientSocket.getOutputStream());//ezt küldjük a szervernek
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
}
