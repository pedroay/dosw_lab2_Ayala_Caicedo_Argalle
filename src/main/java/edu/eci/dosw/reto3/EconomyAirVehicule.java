package edu.eci.dosw.reto3;

public class EconomyAirVehicule implements AirVehicule {
    private String type;
    private double maximumSpeed;
    private double price;

    public EconomyAirVehicule(String type) {
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
        return maximumSpeed;
    }

    @Override
    public double getPrice() {
        return 50000.0;
    }

    @Override
    public String getComfort() {
        return "Standard Airplane Cabin";
    }

    @Override
    public String getEquipment() {
        return "Basic Altimeter, Emergency Parachutes";
    }

    @Override
    public void fly() {
        System.out.println("Flying " + type + " at standard cruising altitude.");
    }

    @Override
    public double getMaxAltitude() {
        return 3000.0; // Altitud máxima en metros
    }
}