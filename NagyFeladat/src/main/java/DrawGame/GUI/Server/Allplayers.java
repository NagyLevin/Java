package DrawGame.GUI.Server;

import DrawGame.GUI.ReadPromts;
import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

class OnlinePlayer{
    int[] palyercolor = new int[3];
    boolean amIhost = false;
    String playername = "";
    String givenpromt = "";
    Vector<String> fakepromts = new Vector<>();
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
    int maxplayer = 8;  //kesobb 8 a példában
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
    public synchronized void GiveFAkePromts(String[] promts,int id, ReadPromts RP){

        for (OnlinePlayer player : players) {
            if(player.playerid == id){


                player.fakepromts.addAll(List.of(promts));
                player.fakepromts.removeFirst(); //ures elso mezo torlese
                if(player.fakepromts.size() < players.size()){

                    while (player.fakepromts.size() != players.size()){
                        player.fakepromts.add(   RP.getOnePromt());     //ha nincs elég promt akkor kipótolom

                    }

                }
                //System.out.println("elso " + player.fakepromts.getFirst());


            }

        }
    }

    public void run() {

        System.out.println("isJavaFxThread? Allplayersben " + Platform.isFxApplicationThread()); //meg tudom vele nezni, hogy javafx thread e az adott thread
        ReadPromts readPromts = new ReadPromts();
        readPromts.readfile("promts.txt");  //fajl beolvasása, amiben vannak a promtok
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
                      clientSocket.close(); //max ha tobb a palyer akkor nem enged csatlakozni
                  }

                  players.add(Oplayer);

                  }

                  //System.out.println(Oplayer.playername);
              }

            if(Oplayer.amIhost){
                //System.out.println("wait1");

                clientout = clientReader.readLine();
                //System.out.println("wait2");
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
                clientout = clientReader.readLine();
                if(clientout.equals("GivePromt")){

                    for (OnlinePlayer player : players) {
                        if(player.playerid == (int) ThreadId){
                            player.givenpromt = readPromts.getOnePromt();
                            System.out.println("My promt is: " + player.givenpromt);
                            sendLine(player.givenpromt);
                        }

                    }
                }

            }
            clientout = clientReader.readLine();    //itt egy stringbe tomoritve megkapja a szerver az osszes promtot
            System.out.println(clientout);  //kapok vlami ilyet egyeskep, ketteskep

            String[] promts;
            promts = clientout.split(",",-2);

            GiveFAkePromts(promts,(int) ThreadId,readPromts);   //fakepromtok eltarolasa

            //voting fázis

            //csináljuk egy stringet ahol random sorrendben lesznek a fakepromt es az igazi promt, amit elkuldunk
            //visszakapok egy promtot, megnézem kié volt, majd pontot adaok, ha kell
            //utána a string sorrendjében megnézzük, hogy ki mire szavazott es a string sorrendjében visszaküldöm a neveket a kliensnek
            //ezt annyiszor ismételve ahány player van

            int kor = 0;
            for (OnlinePlayer player : players) {
                Vector<String> votePromts = new Vector<>();
                votePromts.add(player.givenpromt);
                for (OnlinePlayer playerfakepromt : players) {
                    votePromts.add(playerfakepromt.fakepromts.get(kor));  //elso elker
                    kor = kor + 1;
                }
                String votepormtstoclient = "";
                for (int i = 0; i < votePromts.size(); i++) {
                    votepormtstoclient = votepormtstoclient + "," + votePromts.get(i);

                }
                sendLine(votepormtstoclient);
                clientout = clientReader.readLine(); //var arra hogy a kliens melyiket választotta


            }







        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }




    }





}
