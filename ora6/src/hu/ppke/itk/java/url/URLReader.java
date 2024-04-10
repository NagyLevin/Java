package hu.ppke.itk.java.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 
 * @author rudanj
 *
 *	Alapvető URL-tulajdonságokat bemutató osztály.
 *
 */
public class URLReader {
	private URL addr;
	
	/**
	 * URL létrehozása
	 * @param url
	 */
	public URLReader(String url) {
		try {
			addr = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * olvasás az URL-ről
	 */
	public void read() {
		try {
			// vegyük át az addr osztály stream-jét egy BufferedReader-be
			URLConnection connection = addr.openConnection(); //connection létrehozása
			InputStream stream = connection.getInputStream(); //connection lértrehozása
			BufferedReader br = new BufferedReader(new InputStreamReader(stream)); //belerakom egy buffereaderbe


			// addig olvassunk a readerből, amíg lehet, és írjuk ki a képernyőre 
			String line;
			while((line = br.readLine() != null){
				System.out.println(line);
			}
			br.close();

			// és ne felejtsük el lezárni a BufferedReadert!
			throw new IOException();
		} catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * URL-el kapcsolatos információk
	 */
	public void info() {
	    System.out.println("getAuthority: " + addr.getAuthority());
	    System.out.println("getDefaultPort: " + addr.getDefaultPort());
	    System.out.println("getFile: " + addr.getFile());
	    System.out.println("getHost: " + addr.getHost());
	    System.out.println("getPath: " + addr.getPath());
	    System.out.println("getPort: " + addr.getPort());
	    System.out.println("getProtocol: " + addr.getProtocol());
	    System.out.println("getQuery: " + addr.getQuery());
	    System.out.println("getRef: " + addr.getRef());
	    System.out.println("getUserInfo: " + addr.getUserInfo());
	    System.out.println("getHashCode: " + addr.hashCode());
	}
	
}