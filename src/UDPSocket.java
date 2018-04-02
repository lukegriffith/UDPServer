import java.net.*;

public class UDPSocket {

    private DatagramSocket socket;
    private int dataSize = 1024;


    public UDPSocket(int port) throws Exception {
        this.socket = new DatagramSocket(port);
    }

    public UDPSocket() throws Exception {
        this.socket = new DatagramSocket();
    }

    public void send(String message, String address, int port) throws Exception {

        byte[] buffer = message.getBytes();
        InetAddress targetAddress = InetAddress.getByName(address);

        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, targetAddress, port);
        socket.send(packet);
    }

    public UDPMessage receive() throws Exception {

        byte[] buffer = new byte[dataSize];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);

        UDPMessage message = new UDPMessage();

        message.message = new String(packet.getData(), 0, packet.getLength());
        message.address = packet.getAddress();
        message.port = packet.getPort();

        return message;
    }

}
