package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection {
    public Connected(TcpConnection connection) {
        this.connection = connection;
    }

    private TcpConnection connection;

    @Override
    public void connect() {
        System.out.println("Error! Connection already connected");
    }

    @Override
    public void disconnect() {
        connection.setState(new Disconnected(connection));
        System.out.println("Connection disconnected");
    }

    @Override
    public String getCurrentState() {
        return "connected";
    }

    @Override
    public void write(String data) {
        System.out.println("Successfully written data: " + data);
    }
}
// END
