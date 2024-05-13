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
    protected static Socket clientSocket;
    static boolean playerstartedgame = false;
    int countdown = 0;



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

    public synchronized String fromserver(){
        try {
            BufferedReader serversays = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  //ezt kuldi a szerver
            return serversays.readLine();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static synchronized void toServer(String message) throws IOException {
        PrintWriter toszerver = new PrintWriter(clientSocket.getOutputStream());//ezt küldjük a szervernek
        toszerver.println(message);
        toszerver.flush();

    }

  public static synchronized void startgame(){   //elküld egy start gamet
      if (ClientJoin.playerishost && !playerstartedgame){

          System.out.println("I started the game");


          try {
              toServer("PlayerStartedTheGame");
              System.out.println("Sending starter message to server");
          } catch (IOException e) {
              throw new RuntimeException(e);
          }
      }



  }

  public synchronized int SyncParseToint(String beszam){
    return   Integer.parseInt(beszam);
  }

    @Override
    public void run() {

        LOCALPlayer Lplayer = new LOCALPlayer(playername,palyercolor);
        System.out.println("client join is running");


        System.out.println("isJavaFxThread? Playerben" + Platform.isFxApplicationThread()); //meg tudom vele nezni, hogy javafx thread e az adott thread
        if(fromserver().equals("Givestats")){
            //System.out.println("siker");
            String serversays ="";
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
            //host vagyok?
            try {
                toServer("AmIHost");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            serversays = fromserver();
            if(serversays.equals("true")){
                Lplayer.amIhost = true;

                Platform.runLater(() -> {
                    //System.out.println("runs?");
                    ClientJoin.playerishost = true;
                    ClientJoin.createStart();
                });
                System.out.println("I am the host :D");

            }
            else if(serversays.equals("false")){

                Lplayer.amIhost = false;
                Platform.runLater(() -> {
                    ClientJoin.playerishost = false;
                    ClientJoin.createStart();
                });


                System.out.println("I am not the host :O");

            }else{
                System.out.println("hiba az adatok szinkronizállása közben");

            }
            //itt van egy start game fv hivás




            //System.out.println("wait1");
            //utána kapunk countdownt válaszul
            serversays = fromserver();
            //System.out.println("wait2");

            if(serversays.equals("StartGameCountDown")){

                System.out.println(serversays);

                countdown = 1; // msp 10 re ird majd at



                playerstartedgame = true;

                while (countdown > 0) {
                    Platform.runLater(() -> {
                        ClientJoin.TimerInClient(countdown);


                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    countdown--;
                }

                try {
                    toServer("GivePromt");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                serversays = fromserver();
                Lplayer.givenpromt =serversays;
                System.out.println(serversays);


            }



            //Valahol itt nyiss meg egy DRAWINGBOARDOT mindkét kliensnek
            Platform.runLater(() -> {
            ClientJoin.opentheboard(Lplayer.givenpromt,Lplayer.numofcolors,Lplayer.palyercolor);


            });
            countdown = 180;
            while (countdown > 0) {
                Platform.runLater(() -> {
                    DrawfuLboard.TimerInClient(countdown);
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countdown--;
            }



        }





    }
}
