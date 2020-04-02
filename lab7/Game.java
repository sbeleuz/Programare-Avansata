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

    public void simulate() {
        for (Player player : players) {
            new Thread(player).start();
        }
    }

    public void showPoints() {
        System.out.println("----------------");
        if (winner != null) System.out.println("Winner: " + winner.getName());
        for (Player player : players) System.out.println(player.getName() + ": " + player.getPoints());
    }
}
