package exercise;

import exercise.connections.Connection;
import exercise.connections.Disconnected;

// BEGIN
public class TcpConnection {
    private Connection state;
    private String ipAddress;
    private int port;

    public TcpConnection(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
        state = new Disconnected(this);
    }

    public void connect() {
        state.connect();
    }

    public void disconnect() {
        state.disconnect();
    }

    public String getCurrentState() {
        return state.getCurrentState();
    }

    public void setState(Connection connection) {
        state = connection;
    }

    public void write(String data) {
        state.write(data);
    }
}
// END
