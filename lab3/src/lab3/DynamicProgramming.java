package lab3;

import java.util.List;

public class DynamicProgramming implements Algorithm {
    @Override
    public double solve(Knapsack knapsack, List<Item> items) {
        int noOfItems = items.size();
        int capacity = (int) knapsack.getCapacity();

        int[][] m = new int[noOfItems + 1][capacity + 1];

        for (int i = 0; i <= noOfItems; i++)
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0)
                    m[i][w] = 0;
                else if (items.get(i - 1).getWeight() > w)
                    m[i][w] = m[i - 1][w];
                else
                    m[i][w] = Math.max(m[i - 1][w], m[i - 1][w - (int) items.get(i - 1).getWeight()] + (int) items.get(i - 1).getValue());
            }

        return (double) m[noOfItems][capacity];
    }
}
