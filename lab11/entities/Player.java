package ro.uaic.info.java.lab11.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "players", schema = "gomoku")
@NamedQuery(name = "Players.getAllPlayers", query = "SELECT p FROM Player p")
public class Player {
    private Integer id;
    private Integer gameId;
    private String name;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "game_id")
    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player that = (Player) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(gameId, that.gameId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gameId);
    }
}
