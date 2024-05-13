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

    public synchronized void setPlayerName(String name){

        playername = name;
    }
    public synchronized int getPlayerColoR(){

        return palyercolor[0];

    }
    public synchronized int getPlayerColoB(){

        return palyercolor[2];

    }
    public synchronized int getPlayerColoG(){

        return palyercolor[1];

    }
    public synchronized void setPlayerColoR(int coloR){

        palyercolor[0] = coloR;

    }
    public synchronized void setPlayerColoB(int coloB){

        palyercolor[2] = coloB;

    } public synchronized void setPlayerColoG(int coloG){

        palyercolor[1] = coloG;

    }


}



public class Allplayers extends Thread{

    protected Socket clientSocket;
    protected BufferedReader clientReader;
    protected PrintWriter clientWriter;
    static Vector<OnlinePlayer> players = new Vector<>();
    static String gamecode = "";
    int minplayer = 1;  //kesobb 3 ra állísd
    int maxplayer = 1;  //kesobb 8 a példában
    static boolean gamestartedbyclient = false;

    public Allplayers(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));   //reader a kommunikációhoz
        this.clientWriter = new PrintWriter(clientSocket.getOutputStream());    //writer a kommunikációhoz
        gamecode = hosting.gamecode;

    }
    protected synchronized void sendLine(String line) throws IOException {   //elküld egy sort a kliensnek
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

            String clientout = clientReader.readLine();
            System.out.println(clientout);
              if(clientout.equals("firstStats")){

                  String clienscode = clientReader.readLine();
                  if(!clienscode.equals(gamecode)){
                      System.out.println(clienscode + " : " + gamecode);

                      clientSocket.close();

                  }

                  Oplayer.setPlayerColoR(Integer.parseInt(clientReader.readLine()) ); //szinR
                  Oplayer.setPlayerColoG(Integer.parseInt(clientReader.readLine()) );//szinG
                  Oplayer.setPlayerColoB(Integer.parseInt(clientReader.readLine()) );//szinB
                  Oplayer.playername = clientReader.readLine(); //nev beállít
                  Lobby.addPlayerName(Oplayer.playername,Oplayer.palyercolor);
                  Oplayer.playerid = (int) ThreadId;

                    clientout =clientReader.readLine();
                  System.out.println(clientout);
                  if(clientout.equals("AmIHost")){


                  if(players.isEmpty()){
                      Oplayer.amIhost = true;
                      sendLine("true");
                     // System.out.println("lefut a true");
                  }
                  else{
                     // System.out.println("lefut a fase");
                      Oplayer.amIhost = false;
                      sendLine("false");
                  }

                  if(players.size() > maxplayer){
                      clientSocket.close(); //max player hat
                  }

                  players.add(Oplayer);
                  }

                  //System.out.println(Oplayer.playername);
              }

            if(Oplayer.amIhost){
                System.out.println("wait1");

                clientout = clientReader.readLine();  //es itt akad meg a másik kliens
                System.out.println("wait2");
                System.out.println(clientout);

                if(clientout.equals("PlayerStartedTheGame") && players.size() > minplayer){
                    //itt kezdodik a game

                    gamestartedbyclient = true;





                }

            }

            while(!gamestartedbyclient){    //kliensek ujraszinkronizállása, hogy bevárják egymást
                //System.out.println("Waiting for game to start");
                Thread.sleep(1);
            }

            if(gamestartedbyclient){
                sendLine("StartGameCountDown");



            }






        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }




    }





}
