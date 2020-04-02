import java.util.*;

public class Player implements Runnable {
    private String name;
    static Board board;
    private int points;
    private List<Token> extractedTokens = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return this.points;
    }

    @Override
    public void run() {
        while (Game.isRunning) {
            // try to extract a token (random)
            Token t = board.extractToken();

            if (t.getNumber() == -1) { // no more tokens on the board
                Game.isRunning = false;
                this.points = largestArithmeticProgression(); // no winner, score = size of largest arithmetic progression
            } else {
                System.out.println(this.name + "-" + t.getNumber());
                extractedTokens.add(t);
                if (checkWin(Game.k)) {
                    Game.isRunning = false;
                    if (Game.winner == null) {
                        Game.winner = this;
                        this.points = board.getN(); // winner, score = n
                    } else this.points = 0;
                }
            }
        }
    }

    // complete arithmetic progression of size k
    private boolean checkWin(int k) {
        return largestArithmeticProgression() >= k;
    }

    private int largestArithmeticProgression() {
        Collections.sort(extractedTokens);

        // count and remove blanks
        int blanksCounter = 0;
        int i = 0;
        while (i < extractedTokens.size() && extractedTokens.get(i).getNumber() == 0) {
            blanksCounter++;
            extractedTokens.remove(i++);
        }

        int n = extractedTokens.size();
        if (n <= 2) { // more than two numbers to form an arithmetic progression
            for (i = 0; i < blanksCounter; i++) extractedTokens.add(i, new Token(0)); // add blanks back
            return (n + blanksCounter);
        }

        List<Map<Integer, Integer>> progressions = new ArrayList<>(); // Map[diff] -> counter
        /*
         * for each number extracted, map its difference (diff) with each number smaller than it
         * to number of times (counter), the same difference was previous found
         */
        progressions.add(0, new HashMap<>());
        for (i = 1; i < n; i++) {
            progressions.add(i, new HashMap<>());
            for (int j = i - 1; j >= 0; j--) {
                int diff = extractedTokens.get(i).getNumber() - extractedTokens.get(j).getNumber();
                // if same difference was computed before, increment the length of the progression with difference = diff
                if (progressions.get(j).containsKey(diff)) {
                    progressions.get(i).put(diff, progressions.get(j).get(diff) + 1);
                }
                // else, there is a new progression with difference = diff
                else {
                    progressions.get(i).put(diff, 1);
                }
            }
        }

        // get maximum length of a progression
        int max = 0;
        // for each number, find the longest progression ending with it
        for (i = 0; i < n; i++) {
            for (Integer key : progressions.get(i).keySet()) {
                int val = progressions.get(i).get(key);
                if (val > max)
                    max = val;
            }
        }

        for (i = 0; i < blanksCounter; i++) extractedTokens.add(i, new Token(0)); // add blanks back

        // add the blank tokens to the longest progression
        return 1 + max + blanksCounter;
    }
}
