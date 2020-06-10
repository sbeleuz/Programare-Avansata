package lab3;

public class Weapon implements Item {
    private WeaponType type;
    private double weight;
    private double value;

    public Weapon() {
    }

    public Weapon(WeaponType type, double weight, double value) {
        this.type = type;
        this.weight = weight;
        this.value = value;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public WeaponType getType() {
        return type;
    }

    public void setType(WeaponType type) {
        this.type = type;
    }

    @Override
    public String getName() {
        return type.name();
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
