package hu.ppke.itk.java.bingfight;

import java.io.*;

public class Main {

	/**
	 * Mérhetetlenül egyszerű példaprogram a bingFight problémára.
	 * Csak egyszavas keresőkifejezéseket kezel!
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		BingFight myFighter = new BingFight();
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(
				System.in));

		System.out.println("Szeretnel újabb BingFight-ot? ");
		while (!stdIn.readLine().equals("nem")) {
			myFighter.setStrings();
			myFighter.fight();
			System.out.println("Szeretnel újabb BingFight-ot? ");
		}
		System.out.println("Viszlát.");
	}

}
