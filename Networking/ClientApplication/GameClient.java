package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GameClient {
    String serverAddress = "127.0.0.1"; // The server's IP address
    int PORT = 2020; // The server's port

    public void play() throws IOException {
        try (
                Socket socket = new Socket(serverAddress, PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            boolean running = true;
            while (running) {
                // get the request from keyboard
                Scanner keyboard = new Scanner(System.in);
                keyboard.useDelimiter("\n");
                System.out.println("Enter a request...");
                String request = keyboard.next();
                if (request.equals("exit") || request.equals("stop"))
                    running = false;

                // send request to server
                out.println(request);

                // wait the response from server
                String response = in.readLine();
                System.out.println(response);
            }

        } catch (UnknownHostException e) {
            System.err.println(e.getMessage());
        }
    }

}
