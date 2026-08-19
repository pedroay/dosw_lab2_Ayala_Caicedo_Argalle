package edu.eci.dosw.reto3;

public class UsedLandVehicule implements LandVehicle {
    private String type;
    private double maximumSpeed;
    private double price;
    private String comfort;
    private String equipment;
    private int wheels;

    public UsedLandVehicule(String type,double maximumSpeed, double price, String comfort, String equipemnt,int wheels) {
        this.type = type;
        this.maximumSpeed = maximumSpeed;
        this.price = price;
        this.comfort = comfort;
        this.equipment = equipemnt;
        this.wheels = wheels;
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
        return this.maximumSpeed;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String getComfort() {
        return this.comfort;
    }

    @Override
    public String getEquipment() {
        return this.equipment;
    }

    @Override
    public void driveOnRoad() {
        System.out.println("Driving " + type + " economically on the road.");
    }

    @Override
    public int getWheelCount() {
        return this.wheels;
    }
}