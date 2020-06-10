package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "albums", schema = "musicalbums")
@NamedQueries({
        @NamedQuery(name = "Albums.findByName", query = "SELECT a FROM AlbumsEntity a WHERE a.name=:name"),
        @NamedQuery(name = "Albums.findByArtist", query = "SELECT a FROM AlbumsEntity a WHERE a.artistId=:artistId")})
public class AlbumsEntity extends AbstractEntity {
    private int id;
    private String name;
    private int releaseYear;
    private int artistId;

    public AlbumsEntity() {
    }

    public AlbumsEntity(String name, int releaseYear, int artistId) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.artistId = artistId;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "release_year")
    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Basic
    @Column(name = "artist_id")
    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumsEntity that = (AlbumsEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(releaseYear, that.releaseYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, releaseYear);
    }

    @Override
    public String toString() {
        return "AlbumsEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseYear=" + releaseYear +
                '}';
    }
}
