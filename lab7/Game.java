import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    static int k; // size of a complete arithmetic progression
    static boolean isRunning = true;
    static Player winner = null;

    public Game(List<Player> players, int k) {
        this.players = players;
        Game.k = k;
    }

    public void simulate() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (Player player : players) {
           Thread t = new Thread(player);
            t.start();
            threads.add(t);
        }

        for(Thread t : threads) t.join();
    }

    public void showPoints() {
        System.out.println("----------------");
        if (winner != null) System.out.println("Winner: " + winner.getName());
        for (Player player : players) System.out.println(player.getName() + ": " + player.getPoints());
    }
}
