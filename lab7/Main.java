import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Player.board = new Board(10, 2);

        Player p1 = new ManualPlayer(0, "Player1");
        Player p2 = new RandomPlayer(1, "Player2");
        Player p3 = new SmartPlayer(2, "Player3");

        List<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);

        TimeKeeper timeKeeper = new TimeKeeper(300); // 5 minutes

        Game game = new Game(players, 5, timeKeeper);
        try {
            game.simulate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        game.showPoints();

        if (TimeKeeper.isTime)
            System.out.println("Time of playing: " + timeKeeper.getRunningTime() + " seconds.");
        else
            System.out.println("Game was stopped (time ended)!");
    }
}
