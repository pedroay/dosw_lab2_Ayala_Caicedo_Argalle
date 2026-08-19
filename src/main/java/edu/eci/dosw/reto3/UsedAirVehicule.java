package edu.eci.dosw.reto3;

public class UsedAirVehicule implements AirVehicule {
    private String type;

    public UsedAirVehicule(String type) {
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
        return 180.0;
    }

    @Override
    public double getPrice() {
        return 20000.0;
    }

    @Override
    public String getComfort() {
        return "Functional Vintage Seats";
    }

    @Override
    public String getEquipment() {
        return "Analog Gauges, Maintenance Logbook, Radio Transceiver";
    }

    @Override
    public void fly() {
        System.out.println("Flying Used " + type + " with extra caution.");
    }

    @Override
    public double getMaxAltitude() {
        return 2500.0;
    }
}