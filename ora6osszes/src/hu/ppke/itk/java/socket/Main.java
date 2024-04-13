package hu.ppke.itk.java.socket;

import java.io.*;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {

        try {
            Socket sock = new Socket("www.google.com", 80);
            InputStream is = sock.getInputStream();
            OutputStream os = sock.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            PrintWriter pw = new PrintWriter(os);
            pw.println("Hello world!");


//            while (true){
//                System.out.println(br.readLine());
//            }
            sock.close();

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
