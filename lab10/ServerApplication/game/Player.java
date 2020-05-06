package game;

import com.jcraft.jsch.*;
import freemarker.template.TemplateException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player extends Thread {
    private final Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    private final Game game;
    private int id;
    private Player opponent = null;

    public Player(Socket socket, int id, Game game) {
        this.socket = socket;
        this.id = id;
        this.game = game;
    }

    public int getPlayerId() {
        return id;
    }

    public Player getOpponent() {
        return opponent;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public void run() {
        try {
            initiate();
            processCommands();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (opponent != null)
                    opponent.out.println("Your opponent has left!");
                socket.close();
                in.close();
                out.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private void initiate() throws IOException {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        String response = "Welcome! You are player " + id + ". ";
        if (id == 1) {
            out.println(response + "Waiting for player 2 to connect...");
            game.setCurrentPlayer(this);
        } else if (id == 2) {
            out.println(response);
            opponent = game.getCurrentPlayer();
            opponent.setOpponent(this);
            opponent.out.println("Player 2 connected!");
        }
    }

    private void reInitiate(int id) {
        String response = "You are player " + id + ". ";
        this.id = id;

        if (id == 1) {
            game.setCurrentPlayer(this);
            if (opponent == null)
                out.println(response + "Waiting for player 2 to connect...");
            else
                opponent.setOpponent(this);
        } else if (id == 2) {
            out.println(response);
            opponent.setOpponent(this);
        }
    }

    private void processCommands() throws IOException {
        while (true) {
            String command = in.readLine();
            if (command.startsWith("exit")) {
                game.players--;
                if (opponent != null) {
                    opponent.setOpponent(null);
                    opponent.reInitiate(1);
                }
                break;
            } else if (command.startsWith("restart")) {
                game.restart();
                if (id == 1) {
                    reInitiate(1);
                } else if (id == 2) {
                    if (opponent == null)
                        reInitiate(1); // opponent has left, now you are player 1
                    else
                        reInitiate(2); // opponent is still there, you are player 2
                }
                out.println("Game restarted!");
            } else if (command.startsWith("move ")) {
                // get the position on board
                command = command.substring(5);
                String[] movePositions = command.split("\\s+");

                processMoveCommand(Integer.parseInt(movePositions[0]), Integer.parseInt(movePositions[1]));
            }
        }
    }

    private void processMoveCommand(int x, int y) {
        try {
            game.addToken(this, x, y);
            String response = "Token put at " + x + "," + y + ".";
            out.println(response);
            opponent.out.println("[Opponent]: " + response);
            if (game.checkWin()) {
                representGame();
                out.println("You won! :)");
                opponent.out.println("You lost! :(");
            }
            else if(game.checkDraw()) {
                representGame();
                out.println("Draw!");
                opponent.out.println("Draw!");
            }
        } catch (IllegalStateException e) {
            out.println(e.getMessage()); // not your turn / impossible move
        }
    }

    private void representGame() {
        try {
            // get game representation
            String src = game.getGameRepresentation();

            // upload representation of the game to a Web server (fenrir account)
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");

            JSch jsch = new JSch();
            Session session = jsch.getSession("stefan.beleuz", "students.info.uaic.ro", 22);
            session.setPassword("");
            session.setConfig(config);
            session.connect();

            ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
            sftpChannel.connect();
            sftpChannel.put(src, "game_representation.html");
        } catch (IOException | TemplateException | JSchException | SftpException e) {
            System.out.println(e.getMessage());
        }
    }
}
