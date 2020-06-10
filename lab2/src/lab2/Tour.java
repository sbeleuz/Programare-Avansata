package lab2;

import java.util.Arrays;

public class Tour {
    private Vehicle vehicle;
    private Client[] clients;

    public Tour() {
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Client[] getClients() {
        return clients;
    }

    public void setClients(Client... clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "vehicle=" + vehicle +
                ", clients=" + Arrays.toString(clients) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tour tour = (Tour) o;

        return (vehicle == tour.vehicle) && Arrays.equals(clients, tour.clients);
    }

    @Override
    public int hashCode() {
        int result = vehicle != null ? vehicle.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(clients);
        return result;
    }
}
