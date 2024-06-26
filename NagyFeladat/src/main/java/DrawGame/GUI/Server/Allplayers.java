package DrawGame.GUI.Server;
import DrawGame.GUI.ReadPromts;
import javafx.application.Platform;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.BrokenBarrierException;

class OnlinePlayer{
    public String playersdarwing = "";
    public String playersvote = "";
    int[] palyercolor = new int[3];
    boolean amIhost = false;
    String playername = "";
    String givenpromt = "";
    Vector<String> fakepromts = new Vector<>();
    int playerid ;
    int points =0;


    /**
     * A player nek beállitok egy uj piros szin komponenst azért van mindez külön fv-ben, mert lehet egyszer akarom majd valamilyen paraméterrel állítani a színeket, pl a választott piros árnyalatnak csak a fele
     * @param coloR
     */
    public synchronized void setPlayerColoR(int coloR){

        palyercolor[0] = coloR;

    }
    /**
     * A player nek beállitok egy uj kék szin komponenst
     * @param coloB
     */
    public synchronized void setPlayerColoB(int coloB){

        palyercolor[2] = coloB;

    }
    /**
     * A player nek beállitok egy uj zöld szin komponenst
     * @param coloG
     */
    public synchronized void setPlayerColoG(int coloG){

        palyercolor[1] = coloG;

    }


}



public class Allplayers extends Thread{

    protected Socket clientSocket;
    protected BufferedReader clientReader;
    protected PrintWriter clientWriter;
    static Vector<OnlinePlayer> players = new Vector<>();
    static String gamecode = "";
    int minplayer = 3;  //kesobb 3 ra állísd
    int maxplayer = 8;  //kesobb 8 a példában
    static boolean gamestartedbyclient = false;
    static String Stringallimmages ="";
    static String PlayersandPoints = "";


    /**
     * Itt kapja meg a kliens a socketet,
     * @param clientSocket  amivel a kliens csatlakozott
     * @throws IOException dob egy kivételt, ha a kliens crash-elne, és igény szerint ujraindítja a lobbyt, ha nincs elég játékképes játékos
     */
    public Allplayers(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));   //reader a kommunikációhoz
        this.clientWriter = new PrintWriter(clientSocket.getOutputStream());    //writer a kommunikációhoz
        gamecode = hosting.gamecode;

    }

    /**
     *
     * @param line Ezt a sort elküldi a kliensnek
     * @throws IOException
     */
    protected synchronized void sendLine(String line) throws IOException {   //elküld egy sort a kliensnek
        clientWriter.print(line + "\r\n");
        clientWriter.flush();
    }

    /**
     * Feldolgozza a kliensek által beküldött promtokat
     * @param promts a kliens promtjai
     * @param id melyik kliens az, ez a thread id-je(A player neve után is odarkatam debugoláshoz, illetve arra az esetre, a két játékos ugyanazzal a színnel és névvel lépne be)
     * @param RP itt olvasunk még pe pár uj promtot, akkor, ha nem lenne elég az amit a kliens megadott
     */
    public synchronized void GiveFAkePromts(String[] promts,int id, ReadPromts RP){

        for (OnlinePlayer player : players) {
            if(player.playerid == id){


                player.fakepromts.addAll(List.of(promts));
                player.fakepromts.removeFirst();

                //player.fakepromts.removeFirst(); //ures elso mezo torlese
                for (int i = 0; i < player.fakepromts.size(); i++) {
                    if(player.fakepromts.get(i).equals("")){
                        player.fakepromts.set(i,RP.getOnePromt()) ; //ha urseenhagytam, az sem jo
                    }

                }

                if(player.fakepromts.size() < players.size()){

                    while (player.fakepromts.size() != players.size()){
                        player.fakepromts.add(   RP.getOnePromt());     //ha nincs elég promt akkor kipótolom

                    }

                }


            }

        }
    }


    /**
     * A szavazás résznél használom,
     * @param kor megadom hogy a szavazás hanyadik körében járok
     * @param playerspromt megadom a a szál kliensének a promtját
     * @return visszaadom a kliens erre a körre vontatkozó promtokat összekeverve, hogy mehessen a szavazás
     */
    private synchronized String  PromtsForVote(int kor, String playerspromt) {

        Vector<String> votePromts = new Vector<>();
        votePromts.add(playerspromt);

        for (OnlinePlayer player2 : players) {
            Vector<String> playerspromts = player2.fakepromts;



            if(playerspromts.size() > kor) {

                votePromts.add(playerspromts.get(kor));  //elso elker
            }
        }

        String votepormtstoclient = playerspromt; //az első legyen mindig az igazi promt, ketszer lesz benne, de nem baj, mert csak egyszer veszed figyelembe

        Collections.shuffle(votePromts); //összekeveres
        for (int i = 0; i < votePromts.size(); i++) {
            votepormtstoclient = votepormtstoclient + "," + votePromts.get(i);

        }


        return votepormtstoclient;

    }

    /**
     *Ez nem egy szép megoldás, biztos van jobb, de nekem json fileal nem müködött, ezért vesszővel elválasztva visszaadom egy stringben az összes képet vesszökkel elválasztva
     * @param players egy vektro amiben benne van az összes játékos összes adata
     * @return visszaad egy hosszú stringet benne az összes képpel
     */
    private synchronized String makeOneBigStringWithallImages(Vector<OnlinePlayer> players) {
        String allimmages = "";




        for (OnlinePlayer player : players) {


            allimmages = allimmages +","+  player.playersdarwing;




        }



        return allimmages;
    }

    /**
     * Odaadkja a playernek azt a stringet, amire szavazott
     * @param players egy vektro amiben benne van az összes játékos összes adata
     * @param playerschoice az a promt amire a player szavazott a körben
     * @param threadId a player id-je
     */
    private void PromtToPlayer(Vector<OnlinePlayer> players, String playerschoice, int threadId) {


        for (OnlinePlayer player : players) {

            if(player.playerid == threadId){
                player.playersvote = playerschoice;

            }

        }


    }

    /**
     *
     * @param votePromts ezek az összekevert szavazásra bocsájtott promtok
     * @param players egy vektro amiben benne van az összes játékos összes adata
     * @return visszaad egy stringet, amiben benne van a körben lévő összes játékos neve, az hogy melyik promtra szavazott, és hogy mennyi pontnál tart a pontozás után
     */
    private synchronized String PlayersVotes(String votePromts, Vector<OnlinePlayer> players) {


        String playerspointsandnames ="";
        String[] promtsformvotePromts = votePromts.split(",",-2);




        for (int i = 0; i < promtsformvotePromts.length; i++) {

            playerspointsandnames = playerspointsandnames + promtsformvotePromts[i] +  "=>";
            //System.out.println("i ma stuck in the while loop");
            for (OnlinePlayer player : players) {



                // System.out.println("A player valasztott promtja: " +player.playersvote);
                if (player.playersvote.equals(promtsformvotePromts[i])) {




                    playerspointsandnames = playerspointsandnames   + player.playername + ":" + player.points +  "  " ;



                }


            }
            playerspointsandnames = playerspointsandnames + ";";



        }


        return playerspointsandnames;
    }

    /**
     * Itt történik a klienssel való kommunikáció és a játéklogika, kezdettől tervben volt egy gamemaster osztály, ahova kiszervezek minden föbb lépést gamestage-ekben, de már az idő szüke miatt nincs arra időm hogy úgy megvalúsítsam, ahogy azt elképzeltem
     *
     */
    public void run() {

        System.out.println("isJavaFxThread? Allplayersben " + Platform.isFxApplicationThread()); //meg tudom vele nezni, hogy javafx thread e az adott thread
        ReadPromts readPromts = new ReadPromts();
        readPromts.readfile("promts.txt");  //fajl beolvasása, amiben vannak a promtok
        OnlinePlayer Oplayer = new OnlinePlayer();
        //GameMaster GameM = new GameMaster();


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

                    hosting.playerleft();
                    clientSocket.close();

                }

                Oplayer.setPlayerColoR(Integer.parseInt(clientReader.readLine()) ); //szinR
                Oplayer.setPlayerColoG(Integer.parseInt(clientReader.readLine()) );//szinG
                Oplayer.setPlayerColoB(Integer.parseInt(clientReader.readLine()) );//szinB
                clientout = clientReader.readLine(); //nev beállít
                clientout = clientout + ":" + ThreadId; //TESTROW DELETE LATER
                Oplayer.playername = clientout;

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

                        hosting.playerleft();
                        clientSocket.close(); //max ha tobb a palyer akkor nem enged csatlakozni

                    }

                    players.add(Oplayer);
                    Lobby.addPlayerName(Oplayer.playername,Oplayer.palyercolor);
                }

                //System.out.println(Oplayer.playername);
            }

            //System.out.println(hosting.barrier.getNumberWaiting());
            hosting.barrier.await();

            if(Oplayer.amIhost){
                //System.out.println("wait1");





                clientout = clientReader.readLine();
                //System.out.println("wait2");
                System.out.println(clientout);


                if(clientout.equals("PlayerStartedTheGame")){
                    //itt kezdodik a game



                    gamestartedbyclient = true;


                }
                hosting.latch.countDown();
            }





            //System.out.println("latchszam: " + hosting.latch.getCount());



            hosting.latch.await();

            if(gamestartedbyclient){
                sendLine("StartGameCountDown");
                clientout = clientReader.readLine();
                if(clientout.equals("GivePromt")){

                    hosting.barrier.await();
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


            //System.out.println(hosting.barrier.getNumberWaiting());
            System.out.println(hosting.currentplayers);

            hosting.barrier.await();
            //System.out.println(Oplayer.amIhost);
            if(Oplayer.amIhost){
                Stringallimmages = makeOneBigStringWithallImages(players);


            }




            hosting.barrier.await();

            System.out.println("Az osszes kep: "+ Stringallimmages);


            sendLine(Stringallimmages);   //elkuldom az osszes kepet egy nagy stringben




            clientout = clientReader.readLine();    //itt egy stringbe tomoritve megkapja a szerver az osszes promtot
            System.out.println("A kliens promtja: " +clientout);

            String[] promts;
            promts = clientout.split(",",-2);


            hosting.barrier.await();
            GiveFAkePromts(promts,(int) ThreadId,readPromts);   //fakepromtok eltarolasa
            System.out.println("i got the clients promts");

            //voting fázis

            //csináljuk egy stringet ahol random sorrendben lesznek a fakepromt es az igazi promt, amit elkuldunk
            //visszakapok egy promtot, megnézem kié volt, majd pontot adaok, ha kell
            //utána a string sorrendjében megnézzük, hogy ki mire szavazott es a string sorrendjében visszaküldöm a neveket a kliensnek
            //ezt annyiszor ismételve ahány player van


            //System.out.println(allimmages);
            hosting.barrier.await();
            sendLine(Stringallimmages); //egy kis hackelés, felteszem, hogy -1 es idjő thread nincs
            clientout = clientReader.readLine();
            System.out.println(clientout);



            if(clientout.equals("GottheImage")){



                int kor = 0;
                for (OnlinePlayer player : players) { //az a baj, hogy neki nem az az elso kepe, mint nekem

                    hosting.barrier.await();
                    String votePromts = PromtsForVote(kor,player.givenpromt );
                    hosting.barrier.await();
                    sendLine(votePromts);
                    System.out.println("Promts to clients: " +votePromts);

                    clientout = clientReader.readLine(); //var arra hogy a kliens melyiket választotta
                    System.out.println("A kliens ezt valasztotta: " + clientout);



                    hosting.barrier.await();
                    PromtToPlayer(players,clientout, (int) ThreadId);


                    hosting.barrier.await();
                    Pontozas(clientout,players,(int) ThreadId, player.givenpromt,kor);






                    hosting.barrier.await();

                    System.out.println("pontozas megvan");
                    //egy olyan string, ahol , vel elvalasztva vannak a playerek es a pointjaik, és ; vesszovel a playerek a fakepromtok sorrendjeben

                    if(Oplayer.amIhost) {
                        PlayersandPoints = PlayersVotes(votePromts, players); //osszes valasz es a jo valasz

                    }


                    hosting.barrier.await();
                    System.out.println("Ezek voltak a valaszok, es a pontok: " + PlayersandPoints);
                    sendLine(PlayersandPoints); //elkuldom a pontokat es a playereket , es ; vel elvalasztva
                    hosting.barrier.await();
                    System.out.println("elso kor vege");
                    kor = kor +1;
                    hosting.barrier.await();


                }

                sendLine("StopTheVote"); //Jatek vege
                clientout = clientReader.readLine();
                if(clientout.equals("voteStopped")){
                    String winner = SelectWinner(players);

                    sendLine(winner);
                }


            }
            hosting.barrier.await();
            players.clear();
            gamestartedbyclient = false;
            Stringallimmages ="";
            PlayersandPoints = "";
            gamecode = "";
            Lobby.reset();
            hosting.resetlatch();
            hosting.resetbarrier();
            clientSocket.close();



        } catch (IOException e ) {
            if(hosting.currentplayers < minplayer || Oplayer.amIhost){ //akkor ha kevesebben vagyunk mint 3 nem folytatodhat a game
                players.clear();
                gamestartedbyclient = false;
                Stringallimmages ="";
                PlayersandPoints = "";
                gamecode = "";
                Lobby.reset();
                hosting.resetlatch();
                hosting.resetbarrier();
                try {
                    clientSocket.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }else

            hosting.playerleft(); //ha kilép egy player, akkor a hosting classban is levonunk egyet a playerek számából
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {


            throw new RuntimeException(e);
        }


    }

    /**
     * Itt választom ki az utolsó körben, hogy melyik player nyerte meg a játékot
     * @param players egy vektro amiben benne van az összes játékos összes adata
     * @return a nyertes/ek neve/i ha több nyertes van akkor mindenkinek kiírja a nevét
     */
    private String SelectWinner(Vector<OnlinePlayer> players) {
        String winner = "";
        String currentbest = "";
        int maxpoints = 0;

        for (OnlinePlayer player : players) {

            if(player.points > maxpoints){
                maxpoints = player.points;
                currentbest = player.playername;
                winner = "";
                winner = winner + currentbest + " : " +maxpoints;


            }
            if(!currentbest.equals(player.playername)  && player.points >= maxpoints){
                    maxpoints = player.points;
                    winner = winner + player.playername + " : " +maxpoints + " ";


            }



        }

        return winner;
    }


    /**
     * Itt pontozom a körben leadott szavazatokat, bevallom hogy a szép megoldás az lett volna, hogy egy player ne tudjon szavazni a saját promtjára, de ezt már nem volt időm úgy átírni, hogy mindenkinek csak a többi player promtja jelenjen meg
     * ugy fixeltem, hogy egy player csak akkor kap pontot, ha nem a saját promtjára, vagy fake-promtjára szavaz
     * @param playerschoice a player szavazata
     * @param players egy vektro amiben benne van az összes játékos összes adata
     * @param threadId az aktuális szál
     * @param jovalasz a jó válasz a kérdésre
     * @param kor megnézzük hogy hanyadik körben járunk
     */
    private synchronized void Pontozas(String playerschoice, Vector<OnlinePlayer> players, int threadId, String jovalasz, int kor) {




        for (OnlinePlayer player : players) {


            if(player.playerid == threadId && !player.givenpromt.equals(playerschoice) && playerschoice.equals(jovalasz)){

                player.points = player.points +2; //ket pont egy jo valasz

            }
            if(player.playerid != threadId && player.fakepromts.size() >= kor && player.fakepromts.get(kor).equals(playerschoice)){

                player.points = player.points + 1; //egy olyan valasz, ahol nem jo, de vlaki masé, akkor 1 pontot kap az illeto

            }



        }




    }




}