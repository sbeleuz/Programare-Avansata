package lab3;

public class Food implements Item {
    private String name;
    private double weight;

    public Food() {
    }

    public Food(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getValue() {
        return weight * 2;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
