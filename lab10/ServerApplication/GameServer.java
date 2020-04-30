//package app;
//
//import java.io.IOException;
//import java.net.ServerSocket;
//
//public class GameServer {
//    public static final int PORT = 2020;
//
//    public GameServer() {
//        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
//            System.out.println("Game server is running...");
//            while (true) {
//                new ClientThread(serverSocket.accept()).start();
//            }
//        } catch (IOException e) {
//            System.err.println(e.getMessage());
//        }
//    }
//}
package app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    public static final int PORT = 2020;
    public static volatile boolean RUNNING = true; // thread-safe

    public GameServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            serverSocket.setSoTimeout(10 * 1000); // timeout for accept()
            while (RUNNING) {
                System.out.println("Waiting for a client ...");
                try {
                    Socket socket = serverSocket.accept();
                    new ClientThread(socket).start();
                } catch (java.io.InterruptedIOException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("Server stopped!");
    }
}