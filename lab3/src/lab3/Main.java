package lab3;

import java.util.ArrayList;
import java.util.List;

import static java.lang.StrictMath.pow;

/**
 * @author Stefan Beleuz
 */

public class Main {

    public static void main(String[] args) {
//        Item book1 = new Book("Dragon Rising", 300, 5);
//        Item book2 = new Book("A Blade in the Dark", 300, 5);
//
//        Item food1 = new Food("Cabbage", 2);
//        Item food2 = new Food("Rabbit", 2);
//
//        Item weapon = new Weapon(WeaponType.Sword, 5, 10);
//
//        Knapsack emptyKnapsack = new Knapsack(10);
//        List<Item> items = new ArrayList<>(List.of(book1, book2, food1, food2, weapon));
//
//        Algorithm greedy = new Greedy();
//        System.out.println("Cost (greedy) = " + greedy.solve(emptyKnapsack, items));
//
//        Algorithm dynamicProgramming = new DynamicProgramming();
//        System.out.println("Cost (dp) = " + dynamicProgramming.solve(emptyKnapsack, items));

        GenerateProblem problem = new GenerateProblem();
        List<Item> items = problem.generateItems();
        Knapsack emptyKnapsack = new Knapsack(problem.generateCapacity());

        long startTime = System.nanoTime();
        Algorithm greedy = new Greedy();
        long endTime = System.nanoTime();
        System.out.println("Cost (greedy) = " + greedy.solve(emptyKnapsack, items));
        System.out.println("Time (milli): " + (endTime - startTime)/pow(10, 6));

        startTime = System.nanoTime();
        Algorithm dynamicProgramming = new DynamicProgramming();
        endTime = System.nanoTime();
        System.out.println("Cost (dp) = " + dynamicProgramming.solve(emptyKnapsack, items));
        System.out.println("Time (milli): " + (endTime - startTime)/pow(10, 6));
    }
}
