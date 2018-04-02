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

    public void send(String message, InetAddress address, int port) throws Exception {

        byte[] buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
        socket.send(packet);
    }

    public UDPMessage receive() throws Exception {

        byte[] buffer = new byte[dataSize];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);

        return new UDPMessage(
                new String(packet.getData(), 0, packet.getLength()),
                packet.getAddress(),
                packet.getPort()
        );

    }

}
