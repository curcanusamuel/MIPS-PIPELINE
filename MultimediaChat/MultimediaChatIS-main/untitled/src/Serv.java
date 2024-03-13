import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;

import javax.swing.*;

public class Serv {
    private static Serv instance;
    private SocketIOServer server;
    private JTextArea textArea;
    private final int PORT_NUMBER = 5556;

    public static Serv getInstance(JTextArea textArea) {
        if (instance == null) {
            instance = new Serv(textArea);

        }

        return instance;
    }


    private Serv(JTextArea textArea) {
        this.textArea = textArea;
    }

    public void startServer() {
        Configuration config = new Configuration();
        config.setPort(PORT_NUMBER);
        server = new SocketIOServer(config);
        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient socketIOClient) {
                textArea.append("One client connected");
            }
        });

        server.start();
        textArea.append("Server started on port:" + PORT_NUMBER);

    }

}
