package socketThread;

import socketThread.AmisConnecterInterface.AmisConnecter;
import java.net.*;
import java.util.ArrayList;


public class serveur {

        public static void main(String[] test) {
            AmisConnecter amisConnecter;
            DatagramSocket socket = null;
            DatagramPacket receivedPacket;
            ArrayList<DatagramPacket> ClientConnecter=new ArrayList<>();
            final int PORT = 5000;
            byte[] b = new byte[1024];
            try {
                socket = new DatagramSocket(PORT);
                System.out.println("Server start!");
                while (true) {
                    // receive the packet from server
                    receivedPacket = new DatagramPacket(b, b.length);
                    socket.receive(receivedPacket);
                    // recevoir le packet
                    ClientConnecter.add(receivedPacket);
                    System.out.println(new String(receivedPacket.getData(), 0, receivedPacket.getLength()));
                    // commencer le thread qui va fair l'envoi des packets recus

                    Thread thread = new Thread(new Threadconnection(socket, receivedPacket));
                    thread.start();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

