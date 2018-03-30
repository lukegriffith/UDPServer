import java.net.*;
import java.util.Scanner;


public class Client {

    public static void main(String arg[]) throws Exception {

        byte[] buffer = new byte[1024];

        DatagramSocket socket = new DatagramSocket();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);


        Scanner userInput = new Scanner(System.in);

        String msg = userInput.nextLine();

        buffer = msg.getBytes();

        InetAddress hostAddress = InetAddress.getByName("localhost");

        DatagramPacket out = new DatagramPacket(buffer, buffer.length, hostAddress, 9876);



        socket.send(out);

        socket.receive(packet);

        String s = new String(packet.getData(), 0, packet.getLength());

        System.out.println(s);





    }


}
