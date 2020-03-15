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
    public String toString() {
        return "Hospital{" +
                name +
                '}';
    }
}
