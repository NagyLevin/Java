import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Sockettest {
    public static void main(String[] args) throws IOException {

        Socket sock = new Socket("www.google.hu", 80);
        InputStream is = sock.getInputStream();
        OutputStream os = sock.getOutputStream();
        sock.close(); // a streamek lezárása

        /*
        bind() - ezen a címen és porton keresztül
        connect() - erre a címre és portra csatlakozunk
        ha nem hívunk külön bind()-ot, a connect() esetén az automatikusan lefut
        ezek a paraméterek Java esetén a konstruktorban is megadhatók
        getInputStream() - ha fogadni akarjuk a bejövő adatokat, akkor azt egy InputStream-en keresztül tudjuk
        getOutputStream() - ha küldeni is szeretnénk valamit a másik félnek
         */

        ServerSocket server = new ServerSocket(55555);
        Socket client = server.accept();
        client.close();
        server.close();

        /*
            A szerver-kliens kommunikáció során 10000 fölötti portszámot válasszunk (közmegegyezés szerint ezek a szabadon használható portok)
            Ne felejtsünk el minden használt Socketet és streamet lezárni kilépés előtt!
            Socketre írás esetén használjuk a PrintWriter két paraméterű konstruktorát
            autoFlush = true
            azaz a println() automatán üríti a puffert
            Az operációs rendszertől függ, hogy milyen sorvége jellel dolgozik (és üríti puffert), erre a tervezésnél gondolni kell. Ha ettől független kommunikációt szeretnénk, akkor simán print()-et kell használnunk, és manuálisan meghívni utána a flush()-t

         */

    }
}