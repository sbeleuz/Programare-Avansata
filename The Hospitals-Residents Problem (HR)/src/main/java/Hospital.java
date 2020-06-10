import java.util.Objects;

public class Hospital implements Comparable<Hospital> {
    private String name;
    private int capacity;

    public Hospital(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getName() {
        return name;
    }

    public int compareTo(Hospital o) {
        return this.name.compareTo(o.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hospital hospital = (Hospital) o;

        if (capacity != hospital.capacity) return false;
        return Objects.equals(name, hospital.name);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + capacity;
        return result;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                name +
                '}';
    }
}
