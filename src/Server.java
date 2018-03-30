import java.net.*;
import java.util.*;

public class Server {


    private static HashMap<String, ArrayList<String>> connHistory;
    private static int port;
    private static int dataSize;

    static {
        connHistory = new HashMap<String, ArrayList<String>>();
        port = 9876;
        dataSize = 1024;
    }


    public static void main(String args[])throws Exception {


        String capitalizedSentence;
        DatagramSocket serverSocket = new DatagramSocket(port);


        while(true) {

            byte[] sendData = new byte[dataSize];
            byte[] receivedData = new byte[dataSize];

            DatagramPacket recievePacket = new DatagramPacket(receivedData, receivedData.length);
            serverSocket.receive(recievePacket);

            String sentence = new String( recievePacket.getData()).trim();
            System.out.println("RECEIVED: '" + sentence + "' from " + recievePacket.getAddress().toString());

            InetAddress IPAddress = recievePacket.getAddress();
            int port = recievePacket.getPort();
            capitalizedSentence = sentence.toUpperCase();
            sendData = capitalizedSentence.getBytes();

            System.out.println("SENDING: '" + capitalizedSentence + "' to " + recievePacket.getAddress().toString());

            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);

        }
    }
}
