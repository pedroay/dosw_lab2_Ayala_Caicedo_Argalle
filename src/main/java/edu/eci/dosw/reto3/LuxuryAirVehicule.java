package edu.eci.dosw.reto3;

public class LuxuryAirVehicule implements AirVehicule {
    private String type;
    private double maximumSpeed;
    private double price;
    private String comfort;
    private String equipment;
    private double maximumAltitud;

    public LuxuryAirVehicule(String type,double maximumSpeed, double price, String comfort, String equipemnt, double maxAltitusd) {
        this.type = type;
        this.maximumSpeed = maximumSpeed;
        this.price = price;
        this.comfort = comfort;
        this.equipment = equipemnt;
        this.maximumAltitud = maxAltitusd;
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
        return maximumSpeed;
    }

    @Override
    public double getPrice() {
        return price;
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
    public void fly() {
        System.out.println("Flying " + type + " at standard cruising altitude.");
    }

    @Override
    public double getMaxAltitude() {
        return maximumAltitud;
    }

}