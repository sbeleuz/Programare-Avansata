package ro.uaic.info.java.lab11.gomoku_server.game;

public class Board {
    public static final int SIZE = 19;
    private final int[][] board;

    public Board() {
        this.board = new int[SIZE][SIZE];
    }

    public int[][] getBoard() {
        return board;
    }

    public void resetBoard() {
        for (int i = 0; i < Board.SIZE; i++)
            for (int j = 0; j < Board.SIZE; j++)
                board[i][j] = 0;
    }

    public boolean addToken(int token, int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE || board[x][y] != 0)
            return false;
        else {
            board[x][y] = token;
            return true;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                stringBuilder.append(board[i][j]);
                stringBuilder.append(" ");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
