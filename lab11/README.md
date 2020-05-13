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
  
Optional (week 12):
- created REST services for inserting and reading games: GameEntity, GameRepository and GameController;
- integrated the services into my previous project: I used RestTemplate to upload games and players when a game ends (see gomoku_server -> services -> GameCallService and PlayerCallService);
- I handle "not found" exceptions (see exceptions -> NotFoundExceptions), throwing when a request tries to use a game/player which is not in db, using a RestControllerAdvice (see advice -> MyRestControllerAdvice);
- securing the communication using the HTTPS protocol, using keytool: \
  ```keytool -genkeypair -alias stefan -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore stefan.p12 -validity 3650```
  
Bonus (week 12):
- documented my services using Swagger: ```http://localhost:2021/swagger-ui.html```
