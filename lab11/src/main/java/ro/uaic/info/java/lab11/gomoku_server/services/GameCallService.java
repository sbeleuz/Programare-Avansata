package ro.uaic.info.java.lab11.gomoku_server.services;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ro.uaic.info.java.lab11.gomoku_server.game.Game;

import java.util.Date;

// https://www.baeldung.com/rest-template
@RestController
public class GameCallService {
    final String uri = "http://localhost:2021/games";

    @PostMapping("/callGames")
    public String addGame(Game game) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject request = new JSONObject();
        request.put("content", game.getBoard().toString());
        request.put("date", new Date().getTime());
        request.put("result", game.getResult());

        HttpEntity<String> entity = new HttpEntity<>(request.toString(), headers);

        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);

        return response.getBody();
    }
}

