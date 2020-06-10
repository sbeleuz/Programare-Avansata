package lab3;

public interface Item extends Comparable<Item> {
    String getName();

    double getValue();

    double getWeight();

    default double profitFactor() {
        return getValue() / getWeight();
    }

    @Override
    default int compareTo(Item o) {
        return this.getName().compareTo(o.getName());
    }
}
