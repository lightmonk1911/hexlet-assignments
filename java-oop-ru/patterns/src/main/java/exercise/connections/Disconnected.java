package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection {
    public Disconnected(TcpConnection connection) {
        this.connection = connection;
    }

    private TcpConnection connection;

    @Override
    public void connect() {
        System.out.println("Connected");
        connection.setState(new Connected(connection));
    }

    @Override
    public void disconnect() {
        System.out.println("Error! Connection already disconnected");
    }

    @Override
    public String getCurrentState() {
        return "disconnected";
    }

    @Override
    public void write(String data) {
        System.out.println("Error! We can't write before connected");
    }
}
// END
