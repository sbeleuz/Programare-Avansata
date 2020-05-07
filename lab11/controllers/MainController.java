package ro.uaic.info.java.lab11.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uaic.info.java.lab11.entities.Player;
import ro.uaic.info.java.lab11.repos.PlayerRepository;
import ro.uaic.info.java.lab11.util.PersistenceUtil;

import java.util.List;

@RestController
@RequestMapping("/players")
public class MainController {
    PersistenceUtil persistenceUtil = PersistenceUtil.getInstance();
    PlayerRepository playerRepository = new PlayerRepository(persistenceUtil);

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerRepository.getAllPlayers();
    }

    @PostMapping
    public ResponseEntity<String> addPlayer(@RequestBody Player player) {
        int id = playerRepository.addPlayer(player);
        return new ResponseEntity<>("Player " + id + " created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{playerId}")
    public ResponseEntity<String> changePlayerName(@PathVariable int playerId, @RequestParam String name) {
        if(playerRepository.findPlayerById(playerId) == null) {
            return new ResponseEntity<>("Player not found", HttpStatus.NOT_FOUND);
        }
        else {
            playerRepository.changePlayerName(playerId, name);
            return new ResponseEntity<>("Player's name updated successfully", HttpStatus.OK);
        }
    }

    @DeleteMapping("/{playerId}")
    public ResponseEntity<String> deletePlayer(@PathVariable int playerId) {
        if(playerRepository.findPlayerById(playerId) == null) {
            return new ResponseEntity<>("Player not found", HttpStatus.GONE);
        }
        else {
            playerRepository.deletePlayer(playerId);
            return new ResponseEntity<>("Player " + playerId + " deleted successfully", HttpStatus.OK);
        }
    }
}
