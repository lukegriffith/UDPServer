import java.net.*;
import java.util.Scanner;


public class Client {

    public static void main(String arg[]) throws Exception {

        InetAddress target = InetAddress.getByName("localhost");

        UDPSocket socket = new UDPSocket();


        for (int i = 0; i < 3; i++) {
            Scanner userInput = new Scanner(System.in);
            String msg = userInput.nextLine();
            socket.send(msg, target, 9876);
        }

        UDPMessage reconstructedString = socket.receive();
        UDPMessage lengthOfString = socket.receive();


        System.out.println(reconstructedString.getMessage() + " " + lengthOfString.getMessage());

    }

}
