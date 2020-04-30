package app;

import com.jcraft.jsch.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private final Socket socket;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            boolean running = true;
            while (running) {
                // get the request from client
                String request = in.readLine();
                String response = "Server received the request: " + request + ".";
                if (request.equals("exit")) {
                    running = false;
                } else if (request.equals("stop")) {
                    GameServer.RUNNING = false;
                    response = "Server stopped!";
                    running = false;
                }

                // send the response to client
                out.println(response);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
