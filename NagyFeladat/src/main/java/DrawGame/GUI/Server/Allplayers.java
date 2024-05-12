package DrawGame.GUI.Server;

import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

class OnlinePlayer{
    int[] palyercolor = new int[3];
    boolean amIhost = false;
    String playername = "";
    String givenpromt = "";
    String fakepromt = "";
    int numofcolors = 2;
    int playerid ;
    int points =0;

    OnlinePlayer(){


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



public class Allplayers extends Thread{

    protected Socket clientSocket;
    protected BufferedReader clientReader;
    protected PrintWriter clientWriter;
    static Vector<OnlinePlayer> players = new Vector<>();
    static String gamecode = "";



    public Allplayers(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));   //reader a kommunikációhoz
        this.clientWriter = new PrintWriter(clientSocket.getOutputStream());    //writer a kommunikációhoz
        gamecode = hosting.gamecode;

    }
    protected void sendLine(String line) throws IOException {   //elküld egy sort a kliensnek
        clientWriter.print(line + "\r\n");
        clientWriter.flush();
    }
    public void run() {

        System.out.println("isJavaFxThread? Allplayersben " + Platform.isFxApplicationThread()); //meg tudom vele nezni, hogy javafx thread e az adott thread

        OnlinePlayer Oplayer = new OnlinePlayer();
        GameMaster GameM = new GameMaster();


        System.out.println("van egy kliens!");
        long ThreadId = Thread.currentThread().getId();
        System.out.println("Hello Kliens!" + "(" +ThreadId + ")");

        try {  //statok szinkronizállásának elkezdese
              sendLine("Givestats");
            //System.out.println(clientReader.readLine());

              if(clientReader.readLine().equals("firstStats")){

                  String clienscode = clientReader.readLine();
                  if(!clienscode.equals(gamecode)){
                      System.out.println(clienscode + " : " + gamecode);

                      clientSocket.close();

                  }

                  Oplayer.setPlayerColoR(Integer.parseInt(clientReader.readLine()) ); //szinR
                  Oplayer.setPlayerColoG(Integer.parseInt(clientReader.readLine()) );//szinG
                  Oplayer.setPlayerColoB(Integer.parseInt(clientReader.readLine()) );//szinB
                  Oplayer.playername = clientReader.readLine(); //nev beállít
                  Lobby.addPlayerName(Oplayer.playername);
                  Oplayer.playerid = (int) ThreadId;
                  if(players.isEmpty()){
                      Oplayer.amIhost = true;
                      sendLine("true");
                  }
                  else{
                      Oplayer.amIhost = false;
                      sendLine("false");
                  }


                  players.add(Oplayer);


                  //System.out.println(Oplayer.playername);
              }

              String startedgame = clientReader.readLine();
              if(startedgame.equals("PlayerStartedTheGame")){

                  System.out.println("Game indul");
              }






        } catch (IOException e) {
            throw new RuntimeException(e);
        }




    }


}
