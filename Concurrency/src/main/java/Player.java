import java.util.*;

abstract public class Player implements Runnable {
    protected int id;
    protected String name;
    static Board board;
    protected int points;
    protected List<Token> extractedTokens = new ArrayList<>();

    public Player(int id, String name) {
        this.id = id;
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
        play();
    }

    abstract void play();

    protected int computePoints() {
        if (Game.winner == this)
            return board.getN();
        else {
            if (Game.winner == null)
                return largestArithmeticProgression();
            else
                return 0;
        }
    }

    // complete arithmetic progression of size k
    protected boolean checkWin(int k) {
        return largestArithmeticProgression() >= k;
    }

    protected int largestArithmeticProgression() {
        Collections.sort(extractedTokens);

        // count and remove blanks
        int blanksCounter = 0;
        int i = 0;
        while (i < extractedTokens.size() && extractedTokens.get(i).getNumber() == 0) {
            blanksCounter++;
            extractedTokens.remove(i);
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
