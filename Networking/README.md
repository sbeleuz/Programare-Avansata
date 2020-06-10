Lab 10:

Compulsory (week 10):
- all done.

Optional (week 11):
- implemented game functionalities + game management: \
on Server: classes GameServer(listen and accept new connections from client), ClientThread(manages players distribution into games, when a new player connects I check for a waiting game and if I find one, the game will start, else I create a new game and current player waits for an opponent), Board(manages a matrix of 19x19, where 0 is an empty cell, 1 is player 1's token and 2 player 2's token), Game(manages the board and the players, doing things like keeping the current player, add token to board, check for win/draw or get the game representation), Player(manages the communication between server and client); \
on Client: classes GameClient(manages the communication between client and server), MainFrame(the frame of application, displaying the board and a message box), BoardFrame(board graphical representation, also getting user input).
- once a game is finished, an HTML representation of the game is uploaded to a Web server(I used FreeMarker to generate game representation, based on the board and JCraft to connect to my fenrir account and transfer the html file using SFTP).

Bonus (week 11):
- I implemented a GUI for the game, using Swing.
