package DrawGame.GUI.Client;

import DrawGame.GUI.Server.hosting;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Base64;
import java.util.Queue;
import java.util.Vector;

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
    static Vector<String> fakepromtsfromImmagePromt = new Vector<>();


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

    public static void giveFakePromts(Vector<String> _fakepromts) {
        fakepromtsfromImmagePromt = _fakepromts;

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

    private static Image convertBIToImage(BufferedImage image) { //https://stackoverflow.com/questions/30970005/bufferedimage-to-javafx-image //convertalas Bufferedimmagerol image-re
        WritableImage wr = null;
        if (image != null) {
            wr = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
        }

        return new ImageView(wr).getImage();
    }



    @Override
    public void run() {

        while (true){

            playerstartedgame = false;
            LOCALPlayer Lplayer = new LOCALPlayer(playername,palyercolor);
            System.out.println("client join is running");
            String serversays ="";

            System.out.println("isJavaFxThread? Playerben" + Platform.isFxApplicationThread()); //meg tudom vele nezni, hogy javafx thread e az adott thread


            serversays = fromserver();

            if(serversays.equals("Givestats")){
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



        }
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
                    Thread.sleep(1000); //van SEConds.sleep is
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
        countdown = 10; //180 ra állísd
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
        byte[] imageinbytes = DrawfuLboard.playersDrawing.toByteArray();
        String stringimmage = Base64.getEncoder().encodeToString(imageinbytes);
        //System.out.println("counverted to: " + stringimmage);
        try {
            toServer(stringimmage);  //eleg hosszu, de a json nem mukodott
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //rajzolas vege, a nem elmentett kepeket nem mentem el
        //itt kezdodik a promt adas

        serversays = fromserver();

        Vector<Image> Bimmages = ConvertStringToImage(serversays); //ne lehessen modositani

        Platform.runLater(() -> {
            DrawfuLboard.openPromting(Lplayer.palyercolor, Bimmages);


        });





        countdown = 30; //30 ra állísd
        while (countdown > 0) {
            Platform.runLater(() -> {
                ImagePromt.TimerInClient(countdown);
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countdown--;
        }

        String mergedpromt = "";

        for (int i = 0; i < fakepromtsfromImmagePromt.size(); i++) {
            mergedpromt = mergedpromt + "," + fakepromtsfromImmagePromt.get(i);

        }
        //elkuldom a promtokat a szervernek
        try {

            System.out.println("Elkuldom a promtokat a szervernek: " +mergedpromt);
            toServer(mergedpromt);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Voting innen kezdodik


        serversays = fromserver(); //itt kapom meg az osszes kepet
        //System.out.println("Ezt kapom a szervertol kepeknek: " +serversays);
        Vector<Image> AllImagesVote = ConvertStringToImage(serversays);
        AllImagesVote.addFirst(AllImagesVote.firstElement()); //azert hogy a nextvote majd ne akozzon problemat
        //System.out.println("ide meg eljutok?");
        try {
            toServer("GottheImage");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }




        Platform.runLater(() -> {
            ImagePromt.openVoting(Lplayer.palyercolor,AllImagesVote);


        });

        //utana amig nem mondja a szerver hogy stopvoting, megy a voting egy whileban

        while (!serversays.equals("StopTheVote")){
            serversays = fromserver(); //itt kapom meg a korok promtjait



            if(!serversays.equals("StopTheVote")){
                System.out.println("Osszes promt a korre: " + serversays);
                String[] promts = serversays.split(",",-2);

                    Platform.runLater(() -> {
                    ImageVote.nextVote(promts); //allits at mindent a kovetkezo votera
                    });

                    countdown = 30; //30 ra állísd
                    while (countdown > 0) {
                        Platform.runLater(() -> {
                            ImageVote.TimerInClient(countdown);
                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        countdown--;
                    }



                    try {
                        System.out.println(ImageVote.playersChoice);
                        toServer(ImageVote.playersChoice);   //majd ide az en valasztottamat
                        ImageVote.playersChoice = "";
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    serversays = fromserver(); //itt kapom vissza, hogy ki mire sazvazott
                    System.out.println(serversays); //kimire

                    String[] names = serversays.split(";",-2);
                    Vector<String> namesv = new Vector<>();
                    namesv.addAll(Arrays.asList(names));
                    namesv.removeFirst();

                    /*
                    for (int i = 0; i < namesv.size(); i++) {

                        System.out.println(namesv.get(i));

                    }
                    */

                    Platform.runLater(() -> {
                    ImageVote.whoVoted(namesv);
                    });

                    countdown = 10; //10 ra állísd
                    while (countdown > 0) {
                        Platform.runLater(() -> {
                            ImageVote.TimerInClient(countdown);
                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        countdown--;
                    }


                System.out.println("egy szavazasnak vege");
                }

            if(serversays.equals("StopTheVote")){
                try {
                    toServer("voteStopped");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                serversays = fromserver();
                String finalServersays = serversays;
                Platform.runLater(() -> {
                    ImageVote.EndGame(finalServersays); //jatek vge gyoztest mutasd
                });

                countdown = 30; //30 ra állísd
                while (countdown > 0) {
                    Platform.runLater(() -> {
                        ImageVote.TimerInClient(countdown);
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    countdown--;
                }

                serversays = "StopTheVote";

            }


        }

        Platform.runLater(() -> {

            ClientJoin.restart();

        });

        }

    }

    private synchronized Vector<Image> ConvertStringToImage(String serversays) {

        String[] pictures;
        pictures = serversays.split(",",-2);
        Vector<Image> bimmages = new Vector<>();
        for (int i = 1; i < pictures.length; i++) { //mert a nulladik elem ures
            byte[] byteimmage = Base64.getDecoder().decode(pictures[i]);
            //System.out.println(byteimmage);
            ByteArrayInputStream byteoutputImageStream = new ByteArrayInputStream(byteimmage);
            try {
                BufferedImage bi =  ImageIO.read(byteoutputImageStream);

                bimmages.add(convertBIToImage(bi)); //immaget csinalok a bufferedimmageból

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return bimmages;
    }
}
