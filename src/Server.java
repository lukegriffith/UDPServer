import javax.xml.crypto.Data;
import java.net.*;
import java.io.*;

public class Server {

    public static void main(String argv[])throws Exception {


        String capitalizedSentence;
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] recieveData = new byte[1024];
        byte[] sendData = new byte[1024];

        while(true) {

            DatagramPacket recievePacket = new DatagramPacket(recieveData, recieveData.length);
            serverSocket.receive(recievePacket);

            String sentence = new String( recievePacket.getData());
            System.out.println("RECIEVED: +" + sentence);

            InetAddress IPAddress = recievePacket.getAddress();
            int port = recievePacket.getPort();
            capitalizedSentence = sentence.toUpperCase();
            sendData = capitalizedSentence.getBytes();

            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);

        }
    }
}
