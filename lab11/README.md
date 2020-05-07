Lab 11:

Compulsory (week 11): \
I used JPA to communicate with my MySql database (having tables Games and Players).
- util: PersistenceUtil (singleton class, used to create/return an EntityManagerFactory);
- entities: Game (id, content, date, result), Player (game id, name);
- repos: PlayerRepository (used to add a player into db, get a list of all players, update a player's name and to delete a player from db);
- controllers: MainController, containing methods for:
  - obtaining the list of the players, via a HTTP GET request;
  - adding a new player in the database, via a HTTP POST request and using a RequstBody to pass new player's game id and name;
  - modifying the name of a player, via a HTTP PUT request and using a PathVariable to pass player's id and a RequestParam to pass the new name;
  - deleting a player, via a HTTP DELETE request and using a PathVariable to pass player's id.
