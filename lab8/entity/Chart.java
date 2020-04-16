package entity;

import java.util.List;

public class Chart {
    private final int id;
    private final String name;
    private final List<Album> albums; // sorted by rank

    public Chart(int id, String name, List<Album> albums) {
        this.id = id;
        this.name = name;
        this.albums = albums;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    @Override
    public String toString() {
        return "Chart{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", albums=" + albums +
                '}';
    }
}
