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

    public List<Token> getTokens() {
        return tokens;
    }

    public synchronized Token extractRandomToken() {
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

    public synchronized Token extractToken(int tokenValue) {
        if (tokens.isEmpty()) {
            return new Token(-1);
        } else {
            for (Token token : tokens)
                if (token.getNumber() == tokenValue) {
                    tokens.remove(token);
                    return token;
                }
        }
        return new Token(-1);
    }
}
