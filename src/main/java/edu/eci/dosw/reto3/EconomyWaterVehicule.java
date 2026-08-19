package edu.eci.dosw.reto3;

public class EconomyWaterVehicule implements WaterVehicule {
    private String type;

    public EconomyWaterVehicule(String type) {
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
        return 45.0; // Nudos o km/h bajos
    }

    @Override
    public double getPrice() {
        return 10000.0;
    }

    @Override
    public String getComfort() {
        return "Simple Seating";
    }

    @Override
    public String getEquipment() {
        return "Life Vests, Manual Oars, Basic Compass";
    }

    @Override
    public void navigateWater() {
        System.out.println("Sailing " + type + " gently on water.");
    }

    @Override
    public boolean hasAnchor() {
        return true;
    }
}
