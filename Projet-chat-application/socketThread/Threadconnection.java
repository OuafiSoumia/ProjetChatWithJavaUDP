package socketThread;

import socketThread.entities.User;
import socketThread.entities.group;

import javax.swing.*;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import socketThread.entities.group;
public class Threadconnection extends Thread {

    DatagramSocket socket = null;
    DatagramPacket receivedPacket = null;
    List<User> users=new ArrayList();
    List<User> UsersInGroup=new ArrayList<>();
    group groupTosend=new group();

    public Threadconnection(DatagramSocket socket, DatagramPacket receivedPacket) throws SQLException, ClassNotFoundException {
        this.socket = socket;
        this.receivedPacket = receivedPacket;
        this.users=(new User().ListUsers());

    }
   public void brodcastMessageToGroup(DatagramPacket receivedPacket,DatagramSocket socket) throws IOException, SQLException, ClassNotFoundException {
        //message[0]=afen
        //message[1]=g_soumia (destination)
       //distination[0]=g (group or user)
       //distination[1=soumia (nom de destination )
       int len = receivedPacket.getLength();
       String msg =new String(receivedPacket.getData(), 0, len);

       String[] message = msg.split(":");
       byte[] b = (message[0]+":"+receivedPacket.getPort()).getBytes(StandardCharsets.UTF_8);
      // String[] distination=message[1].split("_");
       int idCreatoor=(new group()).getCreatorGroupByNameGroup(message[1]);

       //pour un groupe selectionner avec le nom et l'id creator
       UsersInGroup=(new group()).getListUsersInGroupe(idCreatoor,message[1]);
       //pour un group recuperer par son nom
       UsersInGroup.add((new User()).getUserById(idCreatoor));
       boolean existe=false;
       //pour interdire les utilisateurs qui ne sont pas membre du group d'envoyer un message dedans
        for (User clt:UsersInGroup){
            if  (new User().getUserById(new User().getIdByPort(receivedPacket.getPort())).id==clt.id){
              existe=true;
            }
        }
        //si c'est un membre
       if(existe){
           for (User cltSock : UsersInGroup) {
               if((cltSock.getPort(cltSock.id)) != receivedPacket.getPort()){
                   DatagramPacket sendPacket = new DatagramPacket(b, b.length, cltSock.getAddress(), cltSock.getPort(cltSock.id));
                   // send
                   socket.send(sendPacket);
               }
           }
       }
       else JOptionPane.showMessageDialog(null,"vous ne pouvez pas acceder a ce group");
   }
   public void brodcastMessage(DatagramPacket receivedPacket,DatagramSocket socket) throws IOException {
       //message[0]=hello (le message a tranferer)
       //message[1]=g_soumia (destination)
       //distination[0]=g (group or user)
       //distination[1]=soumia (nom de destination )

       int len = receivedPacket.getLength();
       String msg =new String(receivedPacket.getData(), 0, len);
       String[] message = msg.split(":");

       byte[] b = (message[0]+":"+receivedPacket.getPort()).getBytes(StandardCharsets.UTF_8);
       String[] distination=message[1].split("_");


            int cpt=0;
       for (User cltSock : users) {
           //pour faire le brodcast:
           if (message[1].equals("ALL")) {
               if ((cltSock.getPort(cltSock.id)) != receivedPacket.getPort()) {
                  //& System.out.println("fin aysifet" + cltSock.getPort(cltSock.id) + " li ayssifet" + receivedPacket.getPort());
                   DatagramPacket sendPacket = new DatagramPacket(b, b.length, cltSock.getAddress(), cltSock.getPort(cltSock.id));
                   // send
                   socket.send(sendPacket);
                    }
               cpt++;
           }
           //pour envoyer a une personne:
           else if (distination[1].equals(cltSock.name)){
               if ((cltSock.getPort(cltSock.id)) != receivedPacket.getPort()) {
                   DatagramPacket sendPacket = new DatagramPacket(b, b.length, cltSock.getAddress(), cltSock.getPort(cltSock.id));
                   // send
                   socket.send(sendPacket);
               }
               cpt++;
           }
       }
       //si le destinataire n'egale ni ALL(le touts) ni un utilisateur qui existe
       if(cpt==0)   JOptionPane.showMessageDialog(null, "le distinataire n'existe pas");

   }

    public void run() {
        try {
            byte[] data = receivedPacket.getData();
            int len = receivedPacket.getLength();
            // recuperer l'@ de l'utilisateur
            InetAddress clientAddress = receivedPacket.getAddress();
            // recuperer le port de l'utilisateur
            int clientPort = receivedPacket.getPort();
            // aficher les info du packet
            System.out.println("Client a pour adresse IP：" + clientAddress.getHostAddress());
            System.out.println("Client a pour port：" + clientPort);
            System.out.println("les info：" + new String(data, 0, len));
           //preparer le message a envoyer
            String msgprepa =new String(receivedPacket.getData(), 0, len);

            String[] message2 = msgprepa.split(":");
            String charForALL=message2[1];
            //pour distinguer un nom d'un utilisateur d'un nom du group
            String[] message = charForALL.split("_");
            String charForComparation =message[0];
            //l'envoi a un utilisateur ou a touts les utilisateurs
            if(charForComparation.equals("u") || charForALL.equals("ALL")){
                //la methode qui fait l'envoi du message
                brodcastMessage(receivedPacket, socket);
            }
            //l'envoi a un groupe:
            else if(charForComparation.equals("g")){
                brodcastMessageToGroup(receivedPacket, socket);
            }
            //si le distinataire n'existe pas ou l'utilisateur a mal taper son nom
            else       JOptionPane.showMessageDialog(null, "il faut respecter les regle (envoie a un group :g_nomGroup && envoie a un user :u_nomUser)");



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    }


