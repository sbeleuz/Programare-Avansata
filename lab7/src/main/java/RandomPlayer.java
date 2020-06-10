public class RandomPlayer extends Player {

    public RandomPlayer(int id, String name) {
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

            // try to extract a token (random)
            Token t = board.extractRandomToken();

            if (t.getNumber() == -1) { // no more tokens on the board
                Game.isRunning = false;
            } else { // add extracted token and check win
                System.out.println(this.name + "-" + t.getNumber());
                extractedTokens.add(t);
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
