package hu.ppke.itk.java.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("localhost");
        byte[] buf = "Hello world!\n".getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 10123);
        socket.send(packet);

        byte[] buf2 = new byte[256];
        DatagramPacket packet2 = new DatagramPacket(buf2, buf2.length);
        socket.receive(packet2);
        String received = new String(packet2.getData(), 0, packet2.getLength());
        System.out.println("Received: " + received);
        socket.close();
    }
}
