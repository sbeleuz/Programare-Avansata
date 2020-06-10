package lab3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateProblem {
    int generateCapacity() {
        Random rand = new Random();

        // capacity between 500 and 2500
        return rand.nextInt(2500 - 500) + 500;
    }

    List<Item> generateItems() {
        Random rand = new Random();

        List<Item> items = new ArrayList<>();

        // items between 10 and 200
        int noOfItems = rand.nextInt(100 - 10) + 10;
        for (int i = 0; i < noOfItems; i++) {
            // get type of item(book/food/weapon)
            int randItem = rand.nextInt(3);
            // rand Book
            if (randItem == 0)
                items.add(new Book("", rand.nextInt(1000), rand.nextInt(500)));
                // rand Food
            else if (randItem == 1)
                items.add(new Food("", rand.nextInt(250)));
                // rand Weapon
            else items.add(new Weapon(WeaponType.Sword, rand.nextInt(200), rand.nextInt(500)));
        }

        return items;
    }
}
