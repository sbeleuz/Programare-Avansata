import java.util.ArrayList;
import java.util.List;

public class Game {
    static List<Player> players;
    static int k; // size of a complete arithmetic progression
    static boolean isRunning = true;
    static Player winner = null;
    static int currentPlayerId = 0;
    private TimeKeeper timeKeeper;

    public Game(List<Player> players, int k, TimeKeeper timeKeeper) {
        Game.players = players;
        this.timeKeeper = timeKeeper;
        Game.k = k;
    }

    public void simulate() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();

        Thread t = new Thread(timeKeeper);
        t.setDaemon(true);
        t.start();

        for (Player player : players) {
            t = new Thread(player);
            t.start();
            threads.add(t);
        }

        for (Thread thread : threads) thread.join();
    }

    public void showPoints() {
        System.out.println("-----Points-----");
        if (winner != null) System.out.println("Winner: " + winner.getName());
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getPoints());
        }
        System.out.println("----------------");
    }
}
