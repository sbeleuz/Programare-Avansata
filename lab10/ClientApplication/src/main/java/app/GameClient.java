package app;

import gui.MainFrame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class GameClient {
    String serverAddress = "127.0.0.1";
    int PORT = 2020;

    public static Socket socket;
    public static PrintWriter out;
    public static BufferedReader in;

    public void play() throws IOException {
        try (
                Socket socket = new Socket(serverAddress, PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            GameClient.socket = socket;
            GameClient.out = out;
            GameClient.in = in;

            MainFrame mainFrame = new MainFrame();

            // get player id
            String response = in.readLine();
            mainFrame.textField.setText(response);
            int playerId = Integer.parseInt(response.substring(24, 25));

            while (true) {
                response = in.readLine();
                if (response == null)
                    break;

                if (response.startsWith("Token put at")) {
                    mainFrame.textField.setText("");
                    // get move positions
                    response = response.substring(13);
                    String[] pos = response.split("[,.]");
                    mainFrame.boardPanel.drawCircle(playerId, Integer.parseInt(pos[0]), Integer.parseInt(pos[1]));
                } else if (response.startsWith("[Opponent]: Token put at")) {
                    mainFrame.textField.setText("");
                    // get opponent's move positions
                    response = response.substring(25);
                    String[] pos = response.split("[,.]");
                    mainFrame.boardPanel.drawCircle((playerId % 2) + 1, Integer.parseInt(pos[0]), Integer.parseInt(pos[1]));
                } else if (response.startsWith("You won!") || response.startsWith("You lost!") || response.startsWith("Draw!")) {
                    mainFrame.boardPanel.gameOver(response);
                } else if (response.startsWith("Your opponent has left!")) {
                    mainFrame.boardPanel.opponentHasLeft();
                } else if (response.startsWith("You are player")) {
                    mainFrame.textField.setText(response);
                    playerId = Integer.parseInt(response.substring(15, 16));
                } else {
                    mainFrame.textField.setText(response);
                }
            }
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        }
    }
}