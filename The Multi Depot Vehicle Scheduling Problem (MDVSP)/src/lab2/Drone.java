package lab2;

public class Drone extends Vehicle {
    public Drone() {
    }

    public Drone(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Drone{" +
                "name='" + this.name + '\'' +
                ", depot=" + depot.getName() +
                '}';
    }
}
