class ParkingLot {

    static class Spot {
        String plate;
        boolean occupied;

        Spot(String plate) {
            this.plate = plate;
            occupied = true;
        }
    }

    private Spot[] table;
    private int capacity;

    public ParkingLot(int size) {
        capacity = size;
        table = new Spot[size];
    }

    private int hash(String plate) {
        return Math.abs(plate.hashCode()) % capacity;
    }

    public int parkVehicle(String plate) {

        int index = hash(plate);

        while (table[index] != null && table[index].occupied) {
            index = (index + 1) % capacity;
        }

        table[index] = new Spot(plate);
        return index;
    }

    public void exitVehicle(String plate) {

        int index = hash(plate);

        while (table[index] != null) {

            if (table[index].plate.equals(plate)) {
                table[index].occupied = false;
                return;
            }

            index = (index + 1) % capacity;
        }
    }
}
