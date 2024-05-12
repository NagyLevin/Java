package DrawGame.GUI.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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


    public Allplayers(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));   //reader a kommunikációhoz
        this.clientWriter = new PrintWriter(clientSocket.getOutputStream());    //writer a kommunikációhoz
    }
    protected void sendLine(String line) throws IOException {   //elküld egy sort a kliensnek
        clientWriter.print(line + "\r\n");
        clientWriter.flush();
    }
    public void run() {

        OnlinePlayer Oplayer = new OnlinePlayer();
        GameMaster GameM = new GameMaster();


        System.out.println("van egy kliens!");
        long ThreadId = Thread.currentThread().getId();
        System.out.println("Hello Kliens!" + "(" +ThreadId + ")");

        try {  //statok szinkronizállásának elkezdese
              sendLine("Givestats");
            //System.out.println(clientReader.readLine());

              if(clientReader.readLine().equals("firstStats")){


                  Oplayer.setPlayerColoR(Integer.parseInt(clientReader.readLine()) );
                  Oplayer.setPlayerColoG(Integer.parseInt(clientReader.readLine()) );
                  Oplayer.setPlayerColoB(Integer.parseInt(clientReader.readLine()) );
                  Oplayer.playername = clientReader.readLine();
                  Oplayer.playerid = (int) ThreadId;

                  //System.out.println(Oplayer.playername);
              }






        } catch (IOException e) {
            throw new RuntimeException(e);
        }




    }


}
