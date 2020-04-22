package app;

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
            boolean running = true;
            while (running) {
                // get the request from client
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String request = in.readLine();
                if (request.equals("exit")) {
                    running = false;
                }

                // send the response to client
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                String response = "Server received the request: " + request + ".";

                out.println(response);
                out.flush();
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
