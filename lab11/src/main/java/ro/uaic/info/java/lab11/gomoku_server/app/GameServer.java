package ro.uaic.info.java.lab11.gomoku_server.app;

import java.io.IOException;
import java.net.ServerSocket;

public class GameServer {
    public static final int PORT = 2020;

    public GameServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Game server is running...");
            while (true) {
                new ClientThread(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}