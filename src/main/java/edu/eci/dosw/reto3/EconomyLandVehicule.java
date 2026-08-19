package edu.eci.dosw.reto3;

public class EconomyLandVehicule implements LandVehicle {
    private String type;

    public EconomyLandVehicule(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getCategory() {
        return "Economy";
    }

    @Override
    public double getMaximumSpeed() {
        return 120.0; // Velocidad estándar para la categoría económica
    }

    @Override
    public double getPrice() {
        return 15000.0; // Precio accesible para la categoría económica
    }

    @Override
    public String getComfort() {
        return "Basic Comfort";
    }

    @Override
    public String getEquipment() {
        return "Standard Seatbelts, FM Radio, Manual Windows";
    }

    @Override
    public void driveOnRoad() {
        System.out.println("Driving " + type + " economically on the road.");
    }

    @Override
    public int getWheelCount() {
        if (type.equalsIgnoreCase("Bicycle") || type.equalsIgnoreCase("Motorcycle")) {
            return 2;
        }
        return 4;
    }
}