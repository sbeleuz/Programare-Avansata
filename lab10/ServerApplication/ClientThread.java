//package app;
//
//import game.Game;
//import game.Player;
//
//import java.net.Socket;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ClientThread extends Thread {
//    private final Socket socket;
//    public static volatile List<Game> activeGames = new ArrayList<>();
//
//    public ClientThread(Socket socket) {
//        this.socket = socket;
//    }
//
//    public void run() {
//        activeGames.removeIf(game -> game.players == 0); // cleanup games with no players
//
//        boolean gameFound = false;
//        for (Game game : activeGames) {
//            if (game.players == 1) { // found a game with a waiting player, join
//                gameFound = true;
//                game.players++;
//                new Player(socket, 2, game).start();
//                break;
//            }
//        }
//        if (!gameFound) { // no waiting players, create a new game
//            Game game = new Game();
//            activeGames.add(game);
//            new Player(socket, 1, game).start();
//        }
//    }
//}
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