package edu.eci.dosw.reto3;

public class LuxuryLandVehicule implements LandVehicle {
    private String type;

    public LuxuryLandVehicule(String type) {
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
        return 280.0;
    }

    @Override
    public double getPrice() {
        return 85000.0;
    }

    @Override
    public String getComfort() {
        return "High-End Leather & Climate Control";
    }

    @Override
    public String getEquipment() {
        return "Autopilot, Massage Seats, Premium Sound System";
    }

    @Override
    public void driveOnRoad() {
        System.out.println("Driving Luxury " + type + " smoothly on the highway.");
    }

    @Override
    public int getWheelCount() {
        if (type.equalsIgnoreCase("Bicycle") || type.equalsIgnoreCase("Motorcycle")) {
            return 2;
        }
        return 4;
    }
}