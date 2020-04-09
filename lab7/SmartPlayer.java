import java.util.Comparator;
import java.util.List;

public class SmartPlayer extends Player {
    public SmartPlayer(int id, String name) {
        super(id, name);
    }

    @Override
    void play() {
        int noOfPlayers = Game.players.size();
        List<Player> players = Game.players;

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

            List<Token> availableTokens = board.getTokens();
            if (availableTokens.size() == 0)
                Game.isRunning = false;
            else {
                // try to extract a blank token
                Token myToken = board.extractToken(0);
                if (myToken.getNumber() != -1) {
                    extractedTokens.add(myToken);
                } else {
                    // try to extend my arithmetic progression, while not allowing others to extend theirs
                    boolean tokenFound = false;
                    myToken = null;

                    int currentLength = largestArithmeticProgression();
                    for (Token token : availableTokens) {
                        extractedTokens.add(token);
                        if (largestArithmeticProgression() > currentLength) { // found a good token for me
                            myToken = token;
                            extractedTokens.remove(token);
                            // sort players based on their actual length of arithmetic progression
                            players.sort(Comparator.comparingInt(Player::largestArithmeticProgression).reversed());

                            // check if token is also good for others
                            for (Player player : players) {
                                if (player != this) {
                                    int playerCurrentLength = player.largestArithmeticProgression();
                                    player.extractedTokens.add(token);
                                    if (player.largestArithmeticProgression() > playerCurrentLength) { // found a good token
                                        tokenFound = true;
                                        player.extractedTokens.remove(token);
                                        extractedTokens.add(board.extractToken(token.getNumber()));
                                        myToken = token;
                                        break;
                                    } else {
                                        player.extractedTokens.remove(token);
                                    }
                                }
                            }

                            if (tokenFound) break;

                        } else {
                            extractedTokens.remove(token);
                        }
                    }

                    // couldn't find a token good for me nor for the others
                    if (!tokenFound) {
                        if (myToken == null) { // couldn't find a good token, extract a random one
                            myToken = board.extractRandomToken();
                        } else {
                            myToken = board.extractToken(myToken.getNumber());
                        }

                        extractedTokens.add(myToken);
                    }
                }

                System.out.println(this.name + "-" + myToken.getNumber());

                // check for win
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
