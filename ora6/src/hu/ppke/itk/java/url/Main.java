package hu.ppke.itk.java.url;

public class Main {
    public static void main(String [] args) {
        System.out.println("------URLReader------");
        // hozzunk létre egy új URLReader példányt kedvenc weblapunk címével

        URLReader urlReader = new URLReader("http://mad.itk.ppke.hu");

        urlReader.info();
        urlReader.read();

        System.out.println("------URLReaderConn------");
        // hozzunk létre egy új URLReaderConn változót a http://httpbin.org/post címhez és teszteljük a POST request-et

        URLReaderConn urlConnection = new URLReaderConn("http://httpbin.org/post");

        urlConnection.info();
        urlConnection.testPostMethod();

        //Hozzunk létre egy új URLReaderConn változót a http://httpbin.org/get címhez és teszteljük a GET request-et

        urlConnection = new URLReaderConn("http://httpbin.org/get");
        urlConnection.info();
        urlConnection.testGetMethod();
    }
}