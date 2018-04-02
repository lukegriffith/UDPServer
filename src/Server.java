import java.net.*;
import java.util.*;

public class Server {


    private static HashMap<String, ArrayList<String>> connectionMap;
    private static int port;

    static {
        connectionMap = new HashMap<String, ArrayList<String>>();
        port = 9876;
    }


    public static void main(String args[])throws Exception {

        String capitalizedSentence;
        UDPSocket serverSocket = new UDPSocket(port);

        while(true) {

            UDPMessage message = serverSocket.receive();

            System.out.println("RECEIVED: '" + message.getMessage() + "' from " + message.getAddress().toString());

            /*
            String sentence = message.getMessage();
            capitalizedSentence = sentence.toUpperCase();
            byte[] sendData = capitalizedSentence.getBytes();





            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, message.getAddress(), port);

            serverSocket.send(capitalizedSentence, message.getAddress(), message.getPort());
            */

            ArrayList<String> history = connectionMap.get(message.getAddress().toString());

            if (history == null) {
                connectionMap.put(message.getAddress().toString(), new ArrayList<String>());
                history = connectionMap.get(message.getAddress().toString());
            }

            history.add(message.getMessage());

            if (history.size() >= 3) {
                String concatString = String.format("%s#%s#%s", history.get(0), history.get(1), history.get(2));
                String stringLength = Integer.toString(concatString.length() - 3);

                System.out.println("SENDING: concatString '" + concatString + "' to " + message.getAddress().toString());
                serverSocket.send(concatString, message.getAddress(), message.getPort());
                System.out.println("SENDING: stringLength '" + stringLength + "' to " + message.getAddress().toString());
                serverSocket.send(stringLength, message.getAddress(), message.getPort());

                connectionMap.remove(message.getAddress().toString());
            }

        }
    }


}

