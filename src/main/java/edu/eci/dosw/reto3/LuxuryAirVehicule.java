package edu.eci.dosw.reto3;

public class LuxuryAirVehicule implements AirVehicule {
    private String type;

    public LuxuryAirVehicule(String type) {
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
        return 600.0;
    }

    @Override
    public double getPrice() {
        return 500000.0;
    }

    @Override
    public String getComfort() {
        return "First-Class Leather Recliners & Active Noise Cancelling";
    }

    @Override
    public String getEquipment() {
        return "Advanced Radar, Pressurized Luxury Cabin, Satellite Phone";
    }

    @Override
    public void fly() {
        System.out.println("Flying Luxury " + type + " in style.");
    }

    @Override
    public double getMaxAltitude() {
        return 12000.0;
    }
}