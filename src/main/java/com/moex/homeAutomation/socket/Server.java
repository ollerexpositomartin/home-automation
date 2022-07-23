package com.moex.homeAutomation.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread {

    private final int PORT;
    private static Server session;
    private ServerSocket server;
    private final List<ClientThread> connections;

    public static Server getInstance(int port) {
        if (session == null) {
            session = new Server(port);
        }
        return session;
    }

    private Server(int port) {
        connections = new ArrayList<>();
        this.PORT = port;
    }

    public void startServer() throws IOException {
        if (server != null && !server.isClosed())
            return;

        server = new ServerSocket(PORT);
        start();
    }

    public List<ClientThread> getConnections() {
        return connections;
    }

    @Override
    public void run() {
        System.out.println("Waiting Clients");
        while (true) {
            try {
                Socket socket = server.accept();
                System.out.println("Client Connected "+socket.getRemoteSocketAddress().toString());
                connections.add(new ClientThread(socket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
