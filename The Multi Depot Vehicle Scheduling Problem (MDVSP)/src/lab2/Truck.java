package lab2;

public class Truck extends Vehicle {
    public Truck() {
    }

    public Truck(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Truck{" +
                "name='" + this.name + '\'' +
                ", depot=" + depot.getName() +
                '}';
    }
}
