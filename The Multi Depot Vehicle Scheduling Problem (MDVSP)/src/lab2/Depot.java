package lab2;

import java.util.Arrays;

public class Depot {
    private String name;
    private Vehicle[] vehicles;

    public Depot() {
    }

    public Depot(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vehicle[] getVehicles() {
        return vehicles;
    }

    /**
     * This method set UNIQUE vehicles to depot
     *
     * @param vehicles Vehicles to be set in depot
     */
    public void setVehicles(Vehicle... vehicles) {
        Vehicle[] auxVehicle = new Vehicle[vehicles.length];
        int sizeVehicle = 0;

        /* get unique vehicles in array auxVehicles */
        for (Vehicle v1 : vehicles) {
            boolean ok = false;

            for (Vehicle v2 : auxVehicle) {
                if (!v1.equals(v2)) {
                    ok = true;
                } else {
                    System.out.println("Vehicle -" + v1.getName() + "- already exists!");
                    ok = false;
                    break;
                }
            }
            if (ok) auxVehicle[sizeVehicle++] = v1;
        }
        this.vehicles = new Vehicle[sizeVehicle];
        System.arraycopy(auxVehicle, 0, this.vehicles, 0, sizeVehicle);

        /* set for each vehicle its depot */
        int i = 0;
        for (Vehicle v : this.vehicles) {
            v.setDepot(this);
        }
    }

    @Override
    public String toString() {
        return "Depot{" +
                "name='" + name + '\'' +
                ", vehicles=" + Arrays.toString(vehicles) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Depot depot = (Depot) o;

        return name.equals(depot.name) && Arrays.equals(vehicles, depot.vehicles);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(vehicles);
        return result;
    }
}
