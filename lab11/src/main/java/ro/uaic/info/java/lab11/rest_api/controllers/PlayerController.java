package ro.uaic.info.java.lab11.rest_api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uaic.info.java.lab11.rest_api.entities.PlayerEntity;
import ro.uaic.info.java.lab11.rest_api.exceptions.NotFoundException;
import ro.uaic.info.java.lab11.rest_api.repos.PlayerRepository;
import ro.uaic.info.java.lab11.rest_api.util.PersistenceUtil;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {
    PersistenceUtil persistenceUtil = PersistenceUtil.getInstance();
    PlayerRepository playerRepository = new PlayerRepository(persistenceUtil);

    @GetMapping
    public List<PlayerEntity> getAllPlayers() {
        return playerRepository.getAllPlayers();
    }

    @PostMapping
    public ResponseEntity<String> addPlayer(@RequestBody PlayerEntity playerEntity) {
        int id = playerRepository.addPlayer(playerEntity);
        return new ResponseEntity<>("Player " + id + " created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{playerId}")
    public ResponseEntity<String> changePlayerName(@PathVariable int playerId, @RequestParam String name) throws NotFoundException {
        if(playerRepository.findPlayerById(playerId) == null) {
            throw new NotFoundException("Player " + playerId + " not found");
        }
        else {
            playerRepository.changePlayerName(playerId, name);
            return new ResponseEntity<>("Player's name updated successfully", HttpStatus.OK);
        }
    }

    @DeleteMapping("/{playerId}")
    public ResponseEntity<String> deletePlayer(@PathVariable int playerId) throws NotFoundException {
        if(playerRepository.findPlayerById(playerId) == null) {
            throw new NotFoundException("Player " + playerId + " not found");
        }
        else {
            playerRepository.deletePlayer(playerId);
            return new ResponseEntity<>("Player " + playerId + " deleted successfully", HttpStatus.OK);
        }
    }

    @GetMapping("/{gameId}")
    public List<PlayerEntity> getPlayersByGameId(@PathVariable int gameId) {
        return playerRepository.getPlayersByGameId(gameId);
    }

}