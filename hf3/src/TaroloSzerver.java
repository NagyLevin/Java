import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TaroloSzerver implements Runnable {
    public static final int PORT_NUMBER = 13131;
    protected ServerSocket serverSocket;
    public TaroloSzerver() throws IOException {
        serverSocket = new ServerSocket(PORT_NUMBER);           //a szerver socketje
    }

    public void close() throws IOException {
        serverSocket.close();
    }

    public void run() {
        try {
            while (! Thread.currentThread().isInterrupted()) {
                Socket clientSocket = serverSocket.accept();        // a kliensek socketjei
                try {
                    new Tarolas(clientSocket).start();
                } catch (IOException e) {
                    System.err.println("Cant communicate with the client");
                }
            }
        } catch (IOException e) {
            System.out.println("Accept failed!");
        }

        try {
            if(serverSocket != null){
                serverSocket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("BUSINESS IS DOWN");
    }

    public static void main(String[] args) {
        try {
            new Thread(new TaroloSzerver()).start();    //a szerver szála
            System.out.println("BUSINESS IS UP");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}