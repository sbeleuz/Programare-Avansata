package ro.uaic.info.java.lab11.gomoku_server.services;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ro.uaic.info.java.lab11.gomoku_server.game.Player;

// https://www.baeldung.com/rest-template
@RestController
public class PlayerCallService {
    final String uri = "http://localhost:2021/players";

    @PostMapping("/callPlayers")
    public String addPlayer(Player player, int gameId) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject request = new JSONObject();
        request.put("gameId", gameId);
        request.put("name", player.getPlayerName());

        HttpEntity<String> entity = new HttpEntity<>(request.toString(), headers);

        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);

        return response.getBody();
    }
}