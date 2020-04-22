package app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    public static final int PORT = 2020;

    public GameServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                System.out.println("Waiting for a client ...");
                Socket socket = serverSocket.accept();

                new ClientThread(socket).start();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
