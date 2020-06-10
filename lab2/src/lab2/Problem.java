package lab2;

import java.util.Arrays;

public class Problem {
    private Depot[] depots;
    private Client[] clients;

    int[][] cost;

    public Problem() {

    }

    public Depot[] getDepots() {
        return depots;
    }

    /**
     * This method set UNIQUE depots to problem
     *
     * @param depots Depots to be set in problem
     */
    public void setDepots(Depot... depots) {
        Depot[] auxDepots = new Depot[depots.length];
        int depotsSize = 0;

        /* get unique depots in array auxDepots */
        for (Depot d1 : depots) {
            boolean ok = false;

            for (Depot d2 : auxDepots) {
                if (!d1.equals(d2)) {
                    ok = true;
                } else {
                    System.out.println("Depot -" + d1.getName() + "- already exists!");
                    ok = false;
                    break;
                }
            }
            if (ok) auxDepots[depotsSize++] = d1;
        }

        this.depots = new Depot[depotsSize];
        System.arraycopy(auxDepots, 0, this.depots, 0, depotsSize);
    }

    public Client[] getClients() {
        return clients;
    }

    /**
     * This method set UNIQUE clients to problem
     *
     * @param clients Clients to be set in problem
     */
    public void setClients(Client... clients) {
        Client[] auxClient = new Client[clients.length];
        int clientsSize = 0;

        /* get unique clients in array auxClients */
        for (Client c1 : clients) {
            boolean ok = false;

            for (Client c2 : auxClient) {
                if (!c1.equals(c2)) {
                    ok = true;
                } else {
                    System.out.println("Client -" + c1.getName() + "- already exists!");
                    ok = false;
                    break;
                }
            }
            if (ok) auxClient[clientsSize++] = c1;
        }

        this.clients = new Client[clientsSize];
        System.arraycopy(auxClient, 0, this.clients, 0, clientsSize);
    }

    public int[][] getCost() {
        return cost;
    }

    public void setCost(int[][] cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "depots=" + Arrays.toString(depots) +
                ", \nclients=" + Arrays.toString(clients) +
                '}';
    }

    /**
     * This method get all vehicles, from all depots
     *
     * @return Vehicle[] Array of all vehicles
     */
    Vehicle[] getVehicles() {
        Vehicle[] vehicles = new Vehicle[0];
        /*
            oldSize = Current size of vehicles in the array
            newSize = Current size of the vehicles to be into array
        */
        int oldSize = 0, newSize = 0;

        for (Depot depot : this.depots) {
            newSize = depot.getVehicles().length;
            Vehicle[] auxVehicles = depot.getVehicles();

            if (oldSize != 0) {
                /* resize vehicles */
                Vehicle[] copyVehicles = new Vehicle[oldSize];
                System.arraycopy(vehicles, 0, copyVehicles, 0, oldSize);

                vehicles = new Vehicle[oldSize + newSize];
                System.arraycopy(copyVehicles, 0, vehicles, 0, oldSize);
            } else {
                vehicles = new Vehicle[newSize];
            }

            /* add new vehicles */
            int i = oldSize;
            for (Vehicle vehicle : auxVehicles)
                vehicles[i++] = vehicle;

            oldSize = vehicles.length;
        }

        return vehicles;
    }
}
