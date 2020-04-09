package dao;

import util.Album;
import java.util.List;

public interface AlbumController {
    void create(String name, int artistId, int releaseYear);

    List<Album> findByArtist(int artistId);
}
