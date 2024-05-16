package DrawGame.GUI.Server;

import java.io.IOException;
import java.util.Vector;
/*
public class GameMaster extends Thread {



    GameMaster(){

    }


    public synchronized void makeHost(OnlinePlayer Oplayer, Vector<OnlinePlayer> players) throws IOException {
        if(players.isEmpty()){
            Oplayer.amIhost = true;
            Allplayers.sendLine("true");
            // System.out.println("lefut a true");
        }
        else{
            // System.out.println("lefut a fase");
            Oplayer.amIhost = false;
            Allplayers.sendLine("false");
        }


    }

    public synchronized void startGame(OnlinePlayer Oplayer, Vector<OnlinePlayer> players, int minplayer) throws IOException {

        if(Oplayer.amIhost){
            //System.out.println("wait1");

            String clientout = Allplayers.clientReader.readLine();
            //System.out.println("wait2");
            System.out.println(clientout);


            if(clientout.equals("PlayerStartedTheGame") && players.size() > minplayer){
                //itt kezdodik a game

                Allplayers.gamestartedbyclient = true;




            }

        }

    }
}
*/