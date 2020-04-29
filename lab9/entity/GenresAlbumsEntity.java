package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "genres_albums", schema = "musicalbums")
@NamedQuery(name = "Genres.findByAlbum", query = "SELECT g.genreId FROM GenresAlbumsEntity g WHERE g.albumId=:albumId")
public class GenresAlbumsEntity extends AbstractEntity {
    private int id;
    private int genreId;
    private int albumId;

    public GenresAlbumsEntity() {
    }

    public GenresAlbumsEntity(int genreId, int albumId) {
        this.genreId = genreId;
        this.albumId = albumId;
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "genre_id")
    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    @Basic
    @Column(name = "album_id")
    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenresAlbumsEntity that = (GenresAlbumsEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(genreId, that.genreId) &&
                Objects.equals(albumId, that.albumId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, genreId, albumId);
    }
}
