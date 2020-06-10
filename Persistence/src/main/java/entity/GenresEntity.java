package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "genres", schema = "musicalbums")
@NamedQuery(name = "Genres.findByName", query = "SELECT g FROM GenresEntity g WHERE g.name=:name")
public class GenresEntity extends AbstractEntity {
    private int id;
    private String name;

    public GenresEntity() {
    }

    public GenresEntity(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenresEntity that = (GenresEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
