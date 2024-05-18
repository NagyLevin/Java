package DrawGame.GUI;

import DrawGame.GUI.Server.Lobby;


public class Main {


    public static void main(String[] args) {
        //System.out.println("Hello world!");

        ReadPromts RP = new ReadPromts(); //PROMTOK BEOLVASÁSA
        RP.readfile("promts.txt");
        RP.readpromts();
        //Gson gson = new Gson(); //https://repo.maven.apache.org/maven2/ itt sem találom, nem létezik
        //gson.toJson()         //találtam egy módszert rá
        Lobby lobby = new Lobby();



        //EZ CSAK EGY TESZT ABLAK!
        //A jatekjot a kliensek és a lobby inditasaval lehet elindítani

        //lobby.start();
        // newFIXEDTHREADPOOL ezt is használhatod, hiszen tudod,hogy mennyi player van //50%
        // lehet hogy lehetne coundown latch csinálni?// Gson file atkuldheto, van hozzá package
       // https://mkyong.com/java/how-do-convert-java-object-to-from-json-format-gson-api/
        //GM gamemaster ebben legyenek a gamestagek // lehet nem is fog kelleni
        //hosszu kódot, ne a javba rakd, mert lefagy a gui //runlater megoldja
        //a felhasználo felület maradjon rszponziv // eddig jó
        //a logic külön szálra
        //Platform.isFxApplicationThread() debug a szálakhoz
        //rakj bele minden olyan extra dolgot amint tanultal a felev soran
        // rakj bele enumot // de hova ?

        //megvan egy kliens join ablak
        //megvan egy főablak
        //megvan egy rajzolós ablak itt egy listából kapja a promtot
        //megvan egy promt adós ablak
        //megvan egy szavazós ablak ahol a szavazás után a pontok is látszanak majd


    }


}