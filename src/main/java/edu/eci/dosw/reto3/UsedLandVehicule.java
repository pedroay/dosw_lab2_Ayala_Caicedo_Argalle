package edu.eci.dosw.reto3;

public class UsedLandVehicule implements LandVehicle {
    private String type;

    public UsedLandVehicule(String type) {
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
        return 90.0;
    }

    @Override
    public double getPrice() {
        return 6000.0;
    }

    @Override
    public String getComfort() {
        return "Worn Fabric Seats";
    }

    @Override
    public String getEquipment() {
        return "AM/FM Radio, Spare Tire, Jack";
    }

    @Override
    public void driveOnRoad() {
        System.out.println("Driving Used " + type + " carefully.");
    }

    @Override
    public int getWheelCount() {
        if (type.equalsIgnoreCase("Bicycle") || type.equalsIgnoreCase("Motorcycle")) {
            return 2;
        }
        return 4;
    }
}