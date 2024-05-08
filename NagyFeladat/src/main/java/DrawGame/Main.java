package DrawGame;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");

        ReadPromts RP = new ReadPromts();
        RP.readfile("promts.txt");
        RP.readpromts();
        //GM gamemaster ebben legyenek a gamestagek
        //hosszu kódot, ne a javba rakd, mert lefagy a gui
        //a felhasználo felület maradjon rszponziv
        //a logic külön szálra
        //Platform.isFxApplicationThread() debug a szálakhoz
        //rakj bele minden olyan extra dolgot amint tanultal a felev soran
        // rakj bele enumot

    }


}