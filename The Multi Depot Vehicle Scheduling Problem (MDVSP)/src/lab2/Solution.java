package lab2;

public class Solution {
    /**
     * This method check if there are unvisited clients anymore
     *
     * @param visited Lists of clients
     * @return boolean true if there unvisited clients, else false
     */
    boolean checkClients(boolean[] visited) {
        for (boolean b : visited)
            if (!b) return true;

        return false;
    }

    /**
     * This method sort clients based on their order
     *
     * @param clients Clients to be sort
     */
    void sortClients(Client[] clients) {
        for (int i = 0; i < clients.length - 1; i++)
            for (int j = i + 1; j < clients.length; j++)
                if (clients[i].getOrder() > clients[j].getOrder()) {
                    Client aux = clients[i];
                    clients[i] = clients[j];
                    clients[j] = aux;
                }
    }

    /**
     * This method allocate trips to vehicles
     *
     * @param problem Instance of the problem
     * @return Tour[] Array of tours assigned
     */
    Tour[] solveOptional(Problem problem) {
        Vehicle[] vehicles = problem.getVehicles();

        Client[] clients = problem.getClients();
        sortClients(clients);

        boolean[] visited = new boolean[clients.length];

        Tour[] tours = new Tour[problem.getVehicles().length];
        int toursSize = 0;

        for (Vehicle vehicle : vehicles) {
            if (checkClients(visited)) {
                Client[] currentClients = new Client[clients.length];
                int currentClientsSize = 0;

                tours[toursSize] = new Tour();
                tours[toursSize].setVehicle(vehicle);
                int currentOrder = 0;

                for (int i = 0; i < clients.length; i++) {
                    if (!visited[i] && currentOrder < clients[i].getOrder()) {
                        visited[i] = true;
                        currentOrder = clients[i].getOrder();
                        currentClients[currentClientsSize++] = clients[i];
                    }
                }
                /* shrink clients */
                Client[] auxClients = new Client[currentClientsSize];
                System.arraycopy(currentClients, 0, auxClients, 0, currentClientsSize);
                currentClients = new Client[currentClientsSize];
                System.arraycopy(auxClients, 0, currentClients, 0, currentClientsSize);

                tours[toursSize++].setClients(currentClients);
            } else break;
        }

        /* shrink tours */
        Tour[] auxTours = new Tour[toursSize];
        System.arraycopy(tours, 0, auxTours, 0, toursSize);
        tours = new Tour[toursSize];
        System.arraycopy(auxTours, 0, tours, 0, toursSize);

        return tours;
    }

    /**
     * This method compute the total cost of the tours
     *
     * @param problem Instance of the problem
     * @return int total cost
     */
    int solveBonus(Problem problem) {
        /* total cost of the tours */
        int totalCost = 0;
        /* cost matrix from one location (depot or client) to another */
        int[][] cost = problem.getCost();

        /* get number of vehicles from all depots*/
        int noOfVehicles = problem.getVehicles().length;
        /* get depot for each vehicle */
        int[] whichDepot = new int[noOfVehicles];
        Depot[] depots = problem.getDepots();
        int poz = 0;
        for (int d = 0; d < depots.length; d++)
            for (int v = 0; v < depots[d].getVehicles().length; v++)
                whichDepot[poz++] = d;

        /* mark visited clients */
        int matrixSize = cost[0].length;
        int noOfDepots = problem.getDepots().length;
        int clientsSize = matrixSize - noOfDepots;
        boolean[] visited = new boolean[clientsSize];

        for (int v = 0; v < noOfVehicles; v++) {
            boolean firstClient = true;
            if (checkClients(visited)) {
                int i = noOfDepots;
                int lastClient = i;
                while (i < matrixSize) {
                    if (!visited[i - noOfDepots]) {
                        if (firstClient) {
                            totalCost += cost[whichDepot[v]][i];
                            firstClient = false;
                        }
                        visited[i - noOfDepots] = true;

                        int j;
                        for (j = noOfDepots; j < matrixSize; j++) {
                            if (cost[i][j] > 0 && !visited[j - noOfDepots]) {
                                totalCost += cost[i][j];
                                lastClient = j;
                                break;
                            }
                        }
                        i = j - 1;
                    }
                    i++;
                }
                totalCost += cost[lastClient][whichDepot[v]];
            } else break;
        }

        return totalCost;
    }

    int getNodeWithMinDist(boolean[] visited, int[] distance) {
        int minDist = Integer.MAX_VALUE;
        int node = -1;
        for (int i = 0; i < distance.length; i++) {
            if (!visited[i] && distance[i] < minDist) {
                minDist = distance[i];
                node = i;
            }
        }
        return node;
    }

    int[] Dijkstra(int source, Problem problem) {
        int[][] cost = problem.getCost();
        int n = cost[0].length;

        boolean[] visited = new boolean[n];
        int[] distance = new int[n];
        int[] before = new int[n];

        for (int v = 0; v < n; v++) {
            distance[v] = Integer.MAX_VALUE;
            before[v] = -1;
        }
        distance[source] = 0;

        while (checkClients(visited)) {
            int u = getNodeWithMinDist(visited, distance);
            visited[u] = true;

            for (int v = 0; v < n; v++)
                if (!visited[v] && cost[u][v] > 0) {
                    int d = distance[u] + cost[u][v];
                    if (d < distance[v]) {
                        distance[v] = d;
                        before[v] = u;
                    }
                }
        }

        return before;
    }
}
