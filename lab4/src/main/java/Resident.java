public class Resident {
    private String name;

    public Resident(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Resident{" +
                name +
                '}';
    }
}
