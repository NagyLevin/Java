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
    public String playersdarwing = "";
    public String playersvote = "";
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
    static int everythreadisthere = 0;
    static int everythreadvoted = 0;




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
                System.out.println(player.fakepromts.get(0));
                //System.out.println("elso " + player.fakepromts.getFirst());


            }

        }
    }

    private synchronized String  PromtsForVote(int kor, String playerspromt) { //mukodik, de nagyon ronda, ha van időd találj ki mást

        Vector<String> votePromts = new Vector<>();
        votePromts.add(playerspromt);

        for (OnlinePlayer player2 : players) {
            Vector<String> playerspromts = player2.fakepromts;



            if(playerspromts.size() > kor) {
                // System.out.println("lefut");
                // System.out.println(playerspromts.get(kor));
                votePromts.add(playerspromts.get(kor));  //elso elker
            }
        }

        String votepormtstoclient = playerspromt; //az első legyen mindig az igazi promt, ketszer lesz benne, de nem baj, mert csak egyszer veszed figyelembe
        //A VOTEPROMTOKAT ÖSSZE KELL KEVERNI! TESTMODE UTAN

        for (int i = 0; i < votePromts.size(); i++) {
            votepormtstoclient = votepormtstoclient + "," + votePromts.get(i);

        }


        return votepormtstoclient;

    }
    private synchronized String makeOneBigStringWithallImages(Vector<OnlinePlayer> players, int ThreadID) {
        String allimmages = ""; //tudom hogy rossz




        for (OnlinePlayer player : players) {
            while (player.playersdarwing.isEmpty() ){ //Threadek ujraszinkronizálása
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            if(player.playerid != ThreadID){
                allimmages = allimmages +","+  player.playersdarwing;
            }



        }

        return allimmages;
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
                  clientout = clientReader.readLine(); //nev beállít
                  clientout = clientout + ThreadId; //TESTROW DELETE LATER
                  Oplayer.playername = clientout;
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
                            clientout = clientReader.readLine();

                            player.playersdarwing = clientout;  //megkapom a drawingot stringben es eltarolom
                            System.out.println("megkaptam a kliens drawingjat");

                        }

                    }
                }

            }


            sendLine(makeOneBigStringWithallImages(players, -1));   //elkuldom az osszes kepet egy nagy stringben

            clientout = clientReader.readLine();    //itt egy stringbe tomoritve megkapja a szerver az osszes promtot
            System.out.println("A kliens promtja: " +clientout);

            String[] promts;
            promts = clientout.split(",",-2);

            GiveFAkePromts(promts,(int) ThreadId,readPromts);   //fakepromtok eltarolasa

            //voting fázis

            //csináljuk egy stringet ahol random sorrendben lesznek a fakepromt es az igazi promt, amit elkuldunk
            //visszakapok egy promtot, megnézem kié volt, majd pontot adaok, ha kell
            //utána a string sorrendjében megnézzük, hogy ki mire szavazott es a string sorrendjében visszaküldöm a neveket a kliensnek
            //ezt annyiszor ismételve ahány player van

                String allimmages = makeOneBigStringWithallImages(players, -1);
                //System.out.println(allimmages);
                sendLine(allimmages); //egy kis hackelés, felteszem, hogy -1 es idjő thread nincs
                clientout = clientReader.readLine();
                System.out.println(clientout);
                if(clientout.equals("GottheImage")){



                int kor = 0;
                for (OnlinePlayer player : players) { //az a baj, hogy neki nem az az elso kepe, mint nekem

                    String votePromts = PromtsForVote(kor,player.givenpromt );
                    sendLine(votePromts);
                    System.out.println("Promts to clients: " +votePromts);

                    clientout = clientReader.readLine(); //var arra hogy a kliens melyiket választotta
                    System.out.println("A kliens ezt valasztotta: " + clientout);

                    Pontozas(clientout,players,(int) ThreadId, player.givenpromt,kor);
                    System.out.println("pontozas megvan");
                    //egy olyan string, ahol , vel elvalasztva vannak a playerek es a pointjaik, és ; vesszovel a playerek a fakepromtok sorrendjeben
                    String PlayersandPoints = PlayersVotes(votePromts,clientout,players); //osszes valasz es a jo valasz
                    System.out.println("Ezek voltak a valaszok, es a pontok: " + PlayersandPoints);
                    sendLine(PlayersandPoints); //elkuldom a pontokat es a playereket , es ; vel elvalasztva

                    System.out.println("elso kor vege");
                    kor = kor +1;
                }

                sendLine("StopTheVote"); //Jatek vege
                }



        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }




    }

    private synchronized String PlayersVotes(String votePromts, String clientout, Vector<OnlinePlayer> players) {


        String playerspointsandnames ="";
        String[] promtsformvotePromts = votePromts.split(",",-2);
        Vector<String> points = new Vector<>();
        Vector<String> names = new Vector<>();


        everythreadvoted = everythreadvoted + 1;

        while (everythreadvoted != players.size()){

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }


        for (int i = 0; i < promtsformvotePromts.length; i++) {





                //System.out.println("i ma stuck in the while loop");



                for (OnlinePlayer player : players) {


                    if (player.playersvote.equals(promtsformvotePromts[i])) {
                        points.add(String.valueOf(player.points));
                        names.add(player.playername);


                    }


                }
            }




        for (int i = 0; i < points.size(); i++) {
            playerspointsandnames = playerspointsandnames + ";" + names.get(i) + "," + points.get(i);
        }




        //playerspointsandnames = playerspointsandnames.substring(2);

        return playerspointsandnames;
    }

    private synchronized void Pontozas(String playerschoice, Vector<OnlinePlayer> players, int threadId, String jovalasz,int kor) {

        everythreadisthere = everythreadisthere + 1;
        while (everythreadisthere != players.size()){

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }//ne ezt hanem szalat kezelj....

        for (OnlinePlayer player : players) {

            if(player.playerid == threadId){
                player.playersvote = playerschoice;

            }




            if(player.playerid == threadId && !player.givenpromt.equals(playerschoice) && playerschoice.equals(jovalasz)){
                player.points = player.points +2; //ket pont egy jo valasz
            }
            if(player.fakepromts.size() >= kor && player.fakepromts.get(kor).equals(playerschoice)){
                player.points = player.points + 1; //egy olyan valasz, ahol nem jo, de vlaki masé, akkor 1 pontot kap az illeto

            }


        }


    }




}
