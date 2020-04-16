package dao;

import entity.Album;
import java.util.List;

public interface AlbumController {
    int create(String name, int artistId, int releaseYear);

    List<Album> findByArtist(int artistId);
}
