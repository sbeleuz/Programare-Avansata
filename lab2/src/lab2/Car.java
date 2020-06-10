package lab2;

public class Car extends Vehicle {
    public Car() {
    }

    public Car(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + this.name + '\'' +
                ", depot=" + depot.getName() +
                '}';
    }
}
