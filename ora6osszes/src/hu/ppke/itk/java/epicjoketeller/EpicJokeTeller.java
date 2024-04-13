package hu.ppke.itk.java.epicjoketeller;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.io.IOException;

public class EpicJokeTeller extends Thread {
    public EpicJokeTeller(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.clientWriter = new PrintWriter(clientSocket.getOutputStream());
    }

    protected void sendLine(String line) throws IOException {
        clientWriter.print(line + "\r\n");
        clientWriter.flush();
    }

    protected boolean expect(String line) throws IOException {
        String clientLine = clientReader.readLine();
        if (! clientLine.equals(line)) {
            return false;
        }

        return true;
    }

    public void run() {
        System.out.println("Klienssel kommunikáció indul");

        try {
            sendLine("Knock-knock!");
            if (! expect("Who's there?")) {
                sendLine("You're supposed to say \"Who's there?\"");
                clientSocket.close();
                System.out.println("A klienssel a kommunikáció lezárult!");
                return;
            }

            String[] joke = JOKES[new java.util.Random().nextInt(JOKES.length)];
            sendLine(joke[0]);

            if (! expect(joke[0] + " who?")) {
                sendLine("You're supposed to say \"" + joke[0] + " who?\"");
                clientSocket.close();
                System.out.println("A klienssel a kommunikáció lezárult!");
                return;
            }

            sendLine(joke[1]);
            sendLine("BA-DUM TSS");

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("A klienssel a kommunikáció sikeres volt!");
    }

    protected Socket clientSocket;
    protected BufferedReader clientReader;
    protected PrintWriter clientWriter;

    protected static final String[][] JOKES = {
            {"Dexter", "Dexter halls with boughs of holly."},
            {"Disco", "Disconnected"},
            {"Doughnut", "Doughnut worry it is just a joke!"},
            {"Vampire", "Vampire state building!"},
            {"Wendy", "Wendy the last time you took a bath?"},
            {"Ken", "Ken I come in, it’s freezing out here?"},
            {"Sherlock", "Sherlock your door! Someone could break in..."},
            {"Quacker", "Quacker another bad knock-knock joke and I’m leaving!"},
            {"Venice", "Venice your mother coming home?"},
            {"Says", "Says me, that's who?"},
            {"Ice cream", "Ice cream if you don't let me in!"},
            {"Cousin", "Cousin stead of opening the door, you're making me stand here!"},
            {"Cows go", "No, cows go moo!"},
            {"Spell", "OK, W H O."},
            {"Hatch", "Bless you and please cover your mouth next time."},
            {"Boo", "Don't cry, it's just a joke."},
            {"Mikey", "Mikey doesn't fit in the keyhole!"},
            {"Hawaii", "I'm fine. Hawaii you?"},
            {"Justin", "Justin case you forgot me out here."},
            {"Kanga", "Kanga roo!"},
            {"Voodoo", "Voodoo you think you are?"},
            {"A little old lady", "I didn't know you could yodel."},
            {"Lettuce", "Lettuce in!"},
            {"Olive", "Olive you."},
            {"Hyou", "Hyou people need to listen to me!"},
            {"Cash", "I didn't realize you were some kind of a nut!"},
            {"Honey bee", "Honey bee a dear and get me a soda."},
            {"Repeat", "Who Who Who."},
            {"Ya", "What a cheerfull person u r!"},
            {"Robin", "Robin your house!"},
            {"Myth", "I myth Daffy Duck"},
            {"Dishes", "Dishes a very bad joke!"}
    };

}
