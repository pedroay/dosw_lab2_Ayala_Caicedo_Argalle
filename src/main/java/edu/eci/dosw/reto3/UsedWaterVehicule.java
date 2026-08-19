package edu.eci.dosw.reto3;

public class UsedWaterVehicule implements WaterVehicule {
    private String type;

    public UsedWaterVehicule(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getCategory() {
        return "Used";
    }

    @Override
    public double getMaximumSpeed() {
        return 30.0;
    }

    @Override
    public double getPrice() {
        return 4000.0;
    }

    @Override
    public String getComfort() {
        return "Basic Weathered Seating";
    }

    @Override
    public String getEquipment() {
        return "Manual Anchor, Standard Life Vests, Basic Radio";
    }

    @Override
    public void navigateWater() {
        System.out.println("Navigating Used " + type + " through calm waters.");
    }

    @Override
    public boolean hasAnchor() {
        return true;
    }
}