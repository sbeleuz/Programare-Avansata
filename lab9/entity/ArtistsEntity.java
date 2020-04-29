package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "artists", schema = "musicalbums")
@NamedQuery(name = "Artists.findByName", query = "SELECT a FROM ArtistsEntity a WHERE a.name =:name")
public class ArtistsEntity extends AbstractEntity {
    private int id;
    private String name;
    private String country;

    public ArtistsEntity() {
    }

    public ArtistsEntity(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public ArtistsEntity(String name, String country) {
        this.name = name;
        this.country = country;
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
    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistsEntity that = (ArtistsEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country);
    }

    @Override
    public String toString() {
        return "ArtistsEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
