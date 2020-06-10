package app;

import game.Game;
import game.Player;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientThread extends Thread {
    private final Socket socket;
    public static volatile List<Game> activeGames = new ArrayList<>();

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        activeGames.removeIf(game -> game.players == 0); // cleanup games with no players

        boolean gameFound = false;
        for (Game game : activeGames) {
            if (game.players == 1) { // found a game with a waiting player, join
                gameFound = true;
                game.players++;
                new Player(socket, 2, game).start();
                break;
            }
        }
        if (!gameFound) { // no waiting players, create a new game
            Game game = new Game();
            activeGames.add(game);
            new Player(socket, 1, game).start();
        }
    }
}