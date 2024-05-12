package DrawGame.GUI.Client;

import DrawGame.GUI.Server.hosting;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

class LOCALPlayer{
     int[] palyercolor = new int[3];
     boolean amIhost = false;
     String playername = "";
    String givenpromt = "";
    String fakepromt = "";
    int numofcolors = 2;
    int playerid ;
    int points =0;

    LOCALPlayer(String _playername,int[] _playercolor){
        playername = _playername;
        palyercolor = _playercolor;

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

}


public class Player implements Runnable{


    String playername;
    int[] palyercolor = new int[3];
    String joincode ="";
    String ip = "";
    protected Socket clientSocket;



    Player(String _playername,int[] _playercolor, String _joincode,String _ip){

        //System.out.println(_playername);
        playername = _playername;
        palyercolor = _playercolor;


        joincode = _joincode;
        ip = _ip;
        try {
            clientSocket = new Socket(ip, hosting.PORT_NUMBER);
        } catch (IOException e) {
            throw new RuntimeException("Nem találtam akív szervert!");
        }
    }

    public String fromserver(){
        try {
            BufferedReader serversays = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  //ezt kuldi a szerver
            return serversays.readLine();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void toServer(String message) throws IOException {
        PrintWriter toszerver = new PrintWriter(clientSocket.getOutputStream());//ezt küldjük a szervernek
        toszerver.println(message);
        toszerver.flush();

    }




    @Override
    public void run() {

        LOCALPlayer Lplayer = new LOCALPlayer(playername,palyercolor);
        System.out.println("client join is running");


        System.out.println("isJavaFxThread? Playerben" + Platform.isFxApplicationThread()); //meg tudom vele nezni, hogy javafx thread e az adott thread
        if(fromserver().equals("Givestats")){
            //System.out.println("siker");

            try {
                toServer("firstStats");
                toServer(joincode);
                toServer(String.valueOf( Lplayer.getPlayerColoR()));//szinR
                toServer(String.valueOf( Lplayer.getPlayerColoG()));//szinG
                toServer(String.valueOf( Lplayer.getPlayerColoB()));//szinB
                toServer(Lplayer.playername);//nev beállít
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(fromserver().equals("true")){
                Lplayer.amIhost = true;

                Platform.runLater(() -> {
                    //System.out.println("runs?");
                    ClientJoin.playerishost = true;
                    ClientJoin.createStart();
                });



            }
            else if(fromserver().equals("false")){
                Lplayer.amIhost = false;
            }else{
                System.out.println("hiba az adatok szinkronizállása közben");

            }


        }





    }
}
