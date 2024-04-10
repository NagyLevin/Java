package hu.ppke.itk.java.url;

import java.net.*;
import java.io.*;


/**
 * URLConnection osztály használata
 * @author rudanj
 *
 */
public class URLReaderConn {
	private URL addr;
	private URLConnection testConn;
	private String urlText;
	
	/**
	 * URL és URLConnection inicializálása
	 * @param url
	 */
	public URLReaderConn(String url) {
	    try {
			addr = new URL(url);
			// vegyük át az addr osztály URLConnection-jét a testConn változóba 
			testConn = addr.openConnection();
			urlText = url; // ez kelleni fog a POST method-nal
	    } catch (MalformedURLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * URLConnection információk
	 */
	public void info() {
		//testConn = addr.openConnection();
		System.out.println("---Néhány lekérdezhető információ:");
		System.out.println("getConnectTimeout: " + testConn.getConnectTimeout());
		System.out.println("getContentEncoding: " + testConn.getContentEncoding());
		System.out.println("getContentLength: " + testConn.getContentLength());
		System.out.println("getContentType: " + testConn.getContentType());
		System.out.println("getDate " + testConn.getDate());
		System.out.println("getExpiration: " + testConn.getExpiration());
		System.out.println("---Az URL amihez csatlakoztunk:");
		System.out.println("getURL " + testConn.getURL());
		System.out.println("---Van pár dolog, amit set-elni is lehet (lehet próbálkozni...)");
	}
	
	/**
	 * Egyszerű olvasás URLConnection-ről
	 */
	public void read() {
		try {
			// vegyük át a testConn input stream-jét, és rakjuk egy BufferedReader-be
			InputStream stream =testConn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(stream));
			// olvassunk ugyanúgy a BufferedReader-ből, ahogy azt az URLReader-ben tettük
			String line;
			while((line = br.readLine() != null){
				System.out.println(line);
			}
			br.close();


			throw new IOException();
		}  catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * POST metódus vizsgálata
	 */
	public void testPostMethod() {
		try {
			testConn = null; 
			testConn = addr.openConnection();
			testConn.setDoOutput(true);
	
			// nyissuk meg a testConn output stream-ját egy OutputStreamWriter-be
			OutputStream stream = testConn.getOutputStream();


			// írjuk bele a "testPOST=tesztelgetunk..." stringet

			// majd zárjuk be a writert
			
			read();
	    } catch(IOException e)
	    {
	    	System.out.println(e.getCause());
	    }
	}

	/**
	 * GET metódus vizsgálata
	 */
	public void testGetMethod() {
		String getPart = "?testGET=tesztelGETunk";
	    String getPart2 = "&testGET2=tesztelGETunk2";
	    
	    try
	    {
	    	URL getTest = new URL(urlText + getPart + getPart2);
	    	testConn = getTest.openConnection();
	        
	    	read();
	    } catch(MalformedURLException e) {
	    	System.out.println(e.getStackTrace());
	    } catch(IOException e) {
	    	System.out.println(e.getStackTrace());
	    }
		
	}
}
