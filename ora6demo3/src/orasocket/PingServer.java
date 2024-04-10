package orasocket;

import java.net.ServerSocket;
import java.net.Socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.io.IOException;

public class PingServer implements Runnable {
	public static final int PORT_NUMBER = 55555;

	public PingServer() throws IOException {
		serverSocket = new ServerSocket(PORT_NUMBER);
	}

	public void close() throws IOException {
		serverSocket.close();
	}

	public void run() {
		try {
			Socket clientSocket = serverSocket.accept();
			// Egy bejovo klienssel elkezdunk foglalkozni.
			// Ha jelenleg nincs kapcsolodo kliens, akkor a szerver blokkol, var addig, amig akad valaki, aki velunk akar csevegni.

			// A szukseges IO cuccok
			BufferedReader clientInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter clientOutput = new PrintWriter(clientSocket.getOutputStream());


			String line = "";
			// String osszehasonlitas MINDIG equals-zal!
			while (! (line = clientInput.readLine()).equals("QUIT")) {
				if (line.equals("PING")) {
					// Halozati kommunikacioban szokas, hogy a sorveget CRLF jelzi, ezt a konvenciot tartsuk meg!
					clientOutput.print("PONG\r\n"); // Hasznalhatnank a println() metodust is. De mivel a sorvege jel operaciosrendszertol fugg (unix/linuxon: \n, windows: \r\n), ezert biztosabb, ha kiirjuk
					clientOutput.flush();
				}
			}

			// CLEANUP
			// Sose felejtsetek el lezarni! Magatoknak okoztok vele fejfajast.
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace(); // Eleg sok helyen elszallhat a kapcsolat, erdemes a java doksit egyszer vegigolvasni, hogy mennyi problema lephet fel halozati kommunikacio soran.
		}

		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("A Szerver leáll");
	}

	public static void main(String[] args) {
		try {
			new Thread(new PingServer()).start();
			System.out.println("A Szerver elindult");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected ServerSocket serverSocket;
}
