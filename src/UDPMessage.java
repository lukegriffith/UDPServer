import java.net.*;

public class UDPMessage {

    private String message;
    private InetAddress address;
    private int port;

    public UDPMessage(String message, InetAddress address, int port) {
        this.message = message;
        this.address = address;
        this.port = port;
    }

    public int getPort(){
        return port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public String getMessage() {
        return message;
    }

}
