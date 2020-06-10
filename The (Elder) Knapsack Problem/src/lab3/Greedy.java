package lab3;

import java.util.List;

public class Greedy implements Algorithm {
    @Override
    public double solve(Knapsack knapsack, List<Item> items) {
        double capacity = knapsack.getCapacity();
        double totalWeight = 0;
        double value = 0;

        items.sort((item1, item2) -> Double.compare(item2.profitFactor(), item1.profitFactor()));

        for (Item item : items)
            if (totalWeight + item.getWeight() < capacity) {
                totalWeight += item.getWeight();
                value += item.getValue();
                //knapsack.addItem(item);
            }

        return value;
    }
}
