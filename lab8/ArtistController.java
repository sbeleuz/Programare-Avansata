package dao;
import util.Artist;

public interface ArtistController {
    void create(String name, String country);

    Artist findByName(String name);
}
