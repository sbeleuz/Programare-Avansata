Laboratorul 7:

Compulsory (week 7):
- class Token - holding a number from 1 to m, 0 for blank;
- class Board - contains a list of Tokens (+ blank ones) and has synchronized method extractToken, which return a valid Token or a Token with value -1 if the board is empty;
- class Player - implements Runnable (each Player runs on a new Thread) and extracts Tokens (random) untill there are no more Tokens or a Player formed a complete arithmetic progression of size k. For finding the largest arithmetic progression I use a list of HashMaps to store the length of a progression, ending on the index i (1 <= i <= size of the extracted Tokens), with a certain difference between elements;
- class Game - simulates the game and show points at the end.
