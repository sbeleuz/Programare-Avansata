import java.util.List;
import java.util.Scanner;

public class ManualPlayer extends Player {

    public ManualPlayer(int id, String name) {
        super(id, name);
    }

    @Override
    void play() {
        int noOfPlayers = Game.players.size();

        mainLoop:
        while (Game.isRunning && TimeKeeper.isTime) {
            // wait for player's turn
            while (this.id != Game.currentPlayerId) {
                try {
                    // time has ended, stop the game
                    if (!TimeKeeper.isTime)
                        break mainLoop;

                    synchronized (Board.class) {
                        Board.class.wait();
                    }
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }

            // get and show available tokens to choose from
            List<Token> availableTokens = board.getTokens();
            if (availableTokens.size() == 0)
                Game.isRunning = false;
            else {
                System.out.print("Available tokens: ");
                for (Token token : availableTokens) {
                    System.out.printf("%d ", token.getNumber());
                }
                System.out.println();

                // choose a token and try to extract it
                Scanner input = new Scanner(System.in);
                Token myToken;
                while (true) {
                    System.out.println("Choose a token:");
                    int myTokenValue = input.nextInt();

                    myToken = board.extractToken(myTokenValue);
                    if (myToken.getNumber() != -1) break;

                    System.out.println("Token not available, try again...");
                }

                // add extracted token and check win
                System.out.println(this.name + "-" + myToken.getNumber());
                extractedTokens.add(myToken);
                if (checkWin(Game.k)) {
                    Game.isRunning = false;
                    if (Game.winner == null) {
                        Game.winner = this;
                    }
                }
            }

            // next player's turn
            Game.currentPlayerId = (Game.currentPlayerId + 1) % noOfPlayers;
            synchronized (Board.class) {
                Board.class.notifyAll();
            }
        }

        this.points = computePoints();
    }
}
