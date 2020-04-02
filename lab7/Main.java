import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Player.board = new Board(5, 1);

        List<Player> players = new ArrayList<>();
        players.add(new Player("Player1"));
        players.add(new Player("Player2"));

        Game game = new Game(players, 3);
        game.simulate();
        TimeUnit.SECONDS.sleep(1); // ?
        game.showPoints();
    }
}
