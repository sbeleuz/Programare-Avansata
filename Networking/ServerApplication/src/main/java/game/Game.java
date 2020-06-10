package game;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    public final static int WIN_SIZE = 5;
    private final Board board = new Board();
    public int players = 1;
    private Player currentPlayer = null;

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public synchronized void addToken(Player player, int x, int y) {
        if (player.getOpponent() == null)
            throw new IllegalStateException("Opponent not connected!");
        if (player != currentPlayer)
            throw new IllegalStateException("Not your turn!");

        boolean result = board.addToken(player.getPlayerId(), x, y);

        if (!result)
            throw new IllegalStateException("Impossible move!");

        currentPlayer = currentPlayer.getOpponent();
    }

    public void restart() {
        board.resetBoard();
    }

    public boolean checkDraw() {
        int[][] boardMatrix = board.getBoard();

        for (int i = 0; i < Board.SIZE; i++)
            for (int j = 0; j < Board.SIZE; j++)
                if (boardMatrix[i][j] == 0)
                    return false;
        return true;
    }

    public boolean checkWin() {
        return checkHorizontal() || checkVertical() || checkDiagonalRight() || checkDiagonalLeft();
    }

    private boolean checkHorizontal() {
        int[][] boardMatrix = board.getBoard();

        for (int x = 0; x < Board.SIZE; x++) {
            int y = 0;
            while (y < Board.SIZE) {
                if (boardMatrix[x][y] != 0) {
                    int player = boardMatrix[x][y];
                    int counter = 1;
                    y++;
                    while (counter < WIN_SIZE && y < Board.SIZE) {
                        if (boardMatrix[x][y] == player) {
                            counter++;
                            y++;
                        } else break;
                    }
                    if (counter == WIN_SIZE)
                        return true;
                } else
                    y++;
            }
        }
        return false;
    }

    private boolean checkVertical() {
        int[][] boardMatrix = board.getBoard();

        for (int y = 0; y < Board.SIZE; y++) {
            int x = 0;
            while (x < Board.SIZE) {
                if (boardMatrix[x][y] != 0) {
                    int player = boardMatrix[x][y];
                    int counter = 1;
                    x++;
                    while (counter < WIN_SIZE && x < Board.SIZE) {
                        if (boardMatrix[x][y] == player) {
                            counter++;
                            x++;
                        } else break;
                    }
                    if (counter == WIN_SIZE)
                        return true;
                } else
                    x++;
            }
        }
        return false;
    }

    private boolean checkDiagonalRight() {
        int[][] boardMatrix = board.getBoard();

        // check below primary diagonal
        for (int i = 0; i <= Board.SIZE - WIN_SIZE; i++) {
            int x = i;
            int y = 0;
            while (x < Board.SIZE) {
                if (boardMatrix[x][y] != 0) {
                    int player = boardMatrix[x][y];
                    int counter = 1;
                    x++;
                    y++;
                    while (counter < WIN_SIZE && x < Board.SIZE) {
                        if (boardMatrix[x][y] == player) {
                            counter++;
                            x++;
                            y++;
                        } else break;
                    }
                    if (counter == WIN_SIZE)
                        return true;
                } else {
                    x++;
                    y++;
                }
            }
        }

        // check above primary diagonal
        for (int i = 1; i <= Board.SIZE - WIN_SIZE; i++) {
            int x = 0;
            int y = i;
            while (y < Board.SIZE) {
                if (boardMatrix[x][y] != 0) {
                    int player = boardMatrix[x][y];
                    int counter = 1;
                    x++;
                    y++;
                    while (counter < WIN_SIZE && y < Board.SIZE) {
                        if (boardMatrix[x][y] == player) {
                            counter++;
                            x++;
                            y++;
                        } else break;
                    }
                    if (counter == WIN_SIZE)
                        return true;
                } else {
                    x++;
                    y++;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonalLeft() {
        int[][] boardMatrix = board.getBoard();

        // check above secondary diagonal
        for (int i = Board.SIZE - 1; i >= Board.SIZE - WIN_SIZE; i--) {
            int x = 0;
            int y = i;
            while (y >= 0) {
                if (boardMatrix[x][y] != 0) {
                    int player = boardMatrix[x][y];
                    int counter = 1;
                    x++;
                    y--;
                    while (counter < WIN_SIZE && y >= 0) {
                        if (boardMatrix[x][y] == player) {
                            counter++;
                            x++;
                            y--;
                        } else break;
                    }
                    if (counter == WIN_SIZE)
                        return true;
                } else {
                    x++;
                    y--;
                }
            }
        }

        // check below primary diagonal
        for (int i = 1; i <= Board.SIZE - WIN_SIZE; i++) {
            int x = i;
            int y = Board.SIZE - 1;
            while (x < Board.SIZE) {
                if (boardMatrix[x][y] != 0) {
                    int player = boardMatrix[x][y];
                    int counter = 1;
                    x++;
                    y--;
                    while (counter < WIN_SIZE && x < Board.SIZE) {
                        if (boardMatrix[x][y] == player) {
                            counter++;
                            x++;
                            y--;
                        } else break;
                    }
                    if (counter == WIN_SIZE)
                        return true;
                } else {
                    x++;
                    y--;
                }
            }
        }
        return false;
    }

    public String getGameRepresentation() throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        cfg.setDirectoryForTemplateLoading(new File(".\\src\\main\\java\\templates"));

        Map<String, Object> input = new HashMap<>();

        List<Integer> cells = new ArrayList<>();
        int[][] boardMatrix = board.getBoard();
        for (int i = 0; i < Board.SIZE; i++)
            for (int j = 0; j < Board.SIZE; j++)
                cells.add(boardMatrix[i][j]);
        input.put("cells", cells);

        Template temp = cfg.getTemplate("template.ftlh");

        Writer writer = new FileWriter("game_representation.html");
        temp.process(input, writer);

        return "game_representation.html";
    }
}
