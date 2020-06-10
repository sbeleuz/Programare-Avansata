package lab3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Knapsack {
    private double capacity;
    private List<Item> items = new ArrayList<>();

    public Knapsack() {
    }

    public Knapsack(double capacity) {
        this.capacity = capacity;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        Collections.sort(items);

        StringBuilder output = new StringBuilder();

        for (Item item : items) {
            output.append(item.getName());
            output.append(", ");
        }

        output.delete(output.length() - 2, output.length());

        return "Knapsack{" +
                "capacity=" + capacity +
                ", items=[" + output.toString() +
                "]}";
    }

    public void addItem(Item item) {
        items.add(item);
    }
}
