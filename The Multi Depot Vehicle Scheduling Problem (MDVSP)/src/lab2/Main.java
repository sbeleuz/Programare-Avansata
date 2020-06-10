package lab2;

/**
 * @author Stefan Beleuz
 */

public class Main {

    public static void main(String[] args) {
        Problem problem = new Problem();

        Vehicle[] vehicles = new Vehicle[3];
        vehicles[0] = new Car("Vehicle 1");
        vehicles[1] = new Truck("Vehicle 2");
        vehicles[2] = new Drone("Vehicle 3");

        Depot d1 = new Depot("Depot 1");
        d1.setVehicles(vehicles[0], vehicles[1]);
        Depot d2 = new Depot("Depot 2");
        d2.setVehicles(vehicles[2]);

        Client c1 = new Client("Client 1", 1);
        Client c2 = new Client("Client 2", 1);
        Client c3 = new Client("Client 3", 2);
        Client c4 = new Client("Client 4", 2);
        Client c5 = new Client("Client 5", 3);

        problem.setClients(c1, c2, c3, c4, c5);
        problem.setDepots(d1, d2);

        // System.out.println(problem);

        Solution solution = new Solution();

        Tour[] tours = solution.solveOptional(problem);

        for (Tour tour : tours)
            System.out.println(tour);

        int[][] cost = {
                {-1, -1, 4, 3, 1, 2, 3},
                {-1, -1, 2, 1, 1, 2, 4},
                {2, 3, -1, -1, 3, 1, 1},
                {3, 2, -1, -1, 2, 4, 3},
                {1, 4, -1, -1, -1, -1, 2},
                {4, 3, -1, -1, -1, -1, 5},
                {2, 1, -1, -1, -1, -1, -1}
        };
        problem.setCost(cost);

        System.out.println("Total cost of tours: " + solution.solveBonus(problem));

    }
}
