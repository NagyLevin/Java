import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



public class TaroloSzerver implements Runnable {
    public static final int PORT_NUMBER = 13131;    //port szama 10k felett
    protected ServerSocket serverSocket;

    /**
     * szerver elindítása
     *
     * @throws RuntimeException ha nem sikerül elinditani
     */
    public TaroloSzerver() {
        try {
            serverSocket = new ServerSocket(PORT_NUMBER);           //a szerver socketje
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * ez a szerver viselkedesenek a fo resze, itt probal meg kommunikalni a klienssel, es itt hivja meg a terolashoz sükséges tarolo osztályt
     *
     */

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

    /**
     * szerver mainje, itt inditom el a szerver szálát
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        new Thread(new TaroloSzerver()).start();    //a szerver szála
        System.out.println("BUSINESS IS UP");


    }


}