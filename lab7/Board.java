import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private int n; // no of tokens
    private List<Token> tokens = new ArrayList<>(); // numbers from 1 to n + blanks

    public Board(int n, int noOfBlanks) {
        this.n = n;
        // add blank tokens
        for (int i = 1; i <= noOfBlanks; i++) {
            tokens.add(new Token(0));
        }
        // add tokens
        for (int i = 1; i <= n; i++) {
            tokens.add(new Token(i));
        }
    }

    public int getN() {
        return n;
    }

    // random token extraction
    public synchronized Token extractToken() {
        Random random = new Random();

        if (tokens.isEmpty()) {
            return new Token(-1);
        } else {
            int index = random.nextInt(tokens.size());
            Token t = tokens.get(index);
            tokens.remove(index);
            return t;
        }
    }
}
