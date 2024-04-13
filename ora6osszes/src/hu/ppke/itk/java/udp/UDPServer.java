package hu.ppke.itk.java.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer extends Thread {

    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[256];

    public UDPServer() throws SocketException {
        socket = new DatagramSocket( 10123);
    }

    public void run() {
        running = true;

        try{
            while (running) {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                String received = new String(packet.getData(), 0, packet.getLength());

                System.out.println("New packet from " + address + " : "+ received);

                if (received.equals("end")) {
                    running = false;
                    continue;
                }
                socket.send(packet);

            }
        } catch (IOException e){
            e.printStackTrace();
        }
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        UDPServer u = new UDPServer();
        u.start();
    }
}
