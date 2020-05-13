package ro.uaic.info.java.lab11.rest_api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uaic.info.java.lab11.rest_api.entities.GameEntity;
import ro.uaic.info.java.lab11.rest_api.exceptions.NotFoundException;
import ro.uaic.info.java.lab11.rest_api.repos.GameRepository;
import ro.uaic.info.java.lab11.rest_api.util.PersistenceUtil;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {
    PersistenceUtil persistenceUtil = PersistenceUtil.getInstance();
    GameRepository gameRepository = new GameRepository(persistenceUtil);

    @GetMapping
    public List<GameEntity> getAllGames() {
        return gameRepository.getAllGames();
    }

    @PostMapping
    public ResponseEntity<String> addGame(@RequestBody GameEntity gameEntity) {
        int id = gameRepository.addGame(gameEntity);
        return new ResponseEntity<>("Game " + id + " created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{gameId}")
    public ResponseEntity<String> deleteGame(@PathVariable int gameId) throws NotFoundException {
        if (gameRepository.findGameById(gameId) == null) {
            throw new NotFoundException("Game " + gameId + " not found");
        } else {
            gameRepository.deleteGame(gameId);
            return new ResponseEntity<>("Game " + gameId + " deleted successfully", HttpStatus.OK);
        }
    }
}