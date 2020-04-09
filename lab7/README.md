Laboratorul 7:

Compulsory (week 7):
- class Token - holding a number from 1 to m, 0 for blank;
- class Board - contains a list of Tokens (+ blank ones) and has synchronized method extractToken, which return a valid Token or a Token with value -1 if the board is empty;
- class Player - implements Runnable (each Player runs on a new Thread) and extracts Tokens (random) untill there are no more Tokens or a Player formed a complete arithmetic progression of size k. For finding the largest arithmetic progression I use a list of HashMaps to store the length of a progression, ending on the index i (1 <= i <= size of the extracted Tokens), with a certain difference between elements;
- class Game - simulates the game and show points at the end.

Optional (week 8):
- each player wait their turn, using wait-notify;
- using a thread (deamon) as timekeeper, which have a maximum running time and which will stop the game if the time is exceeded;
- class Player is abstract now, having as subclasses: RandomPlayer (extracts each time a random Token from Board), SmartPlayer (tries to extract at firt blank Tokens then try to extend its progression but looking at the other players, sorted by their actual score, if same Token would be good for them too and extracts it), ManualPlayer (uses keyboard as input).
