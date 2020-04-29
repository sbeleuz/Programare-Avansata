package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "charts", schema = "musicalbums")
@NamedQuery(name = "Charts.findByName", query = "SELECT c FROM ChartsEntity c WHERE c.name=:name")
public class ChartsEntity extends AbstractEntity {
    private int id;
    private String name;

    public ChartsEntity() {
    }

    public ChartsEntity(String name) {
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
        ChartsEntity that = (ChartsEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "ChartsEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
