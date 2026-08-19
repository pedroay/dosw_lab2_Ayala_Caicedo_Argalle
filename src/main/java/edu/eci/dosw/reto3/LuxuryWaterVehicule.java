package edu.eci.dosw.reto3;

public class LuxuryWaterVehicule implements WaterVehicule {
    private String type;

    public LuxuryWaterVehicule(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getCategory() {
        return "Luxury";
    }

    @Override
    public double getMaximumSpeed() {
        return 95.0;
    }

    @Override
    public double getPrice() {
        return 150000.0;
    }

    @Override
    public String getComfort() {
        return "VIP Suite & Heated Leather Seats";
    }

    @Override
    public String getEquipment() {
        return "Sonar, Automatic Navigation System, Mini Bar";
    }

    @Override
    public void navigateWater() {
        System.out.println("Sailing Luxury " + type + " in ultimate comfort.");
    }

    @Override
    public boolean hasAnchor() {
        return true;
    }
}