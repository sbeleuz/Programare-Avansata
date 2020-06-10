package dao;
import entity.Artist;

public interface ArtistController {
    int create(String name, String country);

    Artist findByName(String name);
}
