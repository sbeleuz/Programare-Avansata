import java.util.Objects;

public class Resident {
    private String name;

    public Resident(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resident resident = (Resident) o;

        return Objects.equals(name, resident.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Resident{" +
                name +
                '}';
    }
}
