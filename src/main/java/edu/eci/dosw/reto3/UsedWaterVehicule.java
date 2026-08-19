package edu.eci.dosw.reto3;

public class UsedWaterVehicule implements WaterVehicule {
    private String type;
    private double maximumSpeed;
    private double price;
    private String comfort;
    private String equipment;
    private boolean anchor;

    public UsedWaterVehicule(String type,double maximumSpeed,double price,String comfort,String equipement,boolean anchor) {
        this.type = type;
        this.maximumSpeed = maximumSpeed;
        this.price = price;
        this.comfort = comfort;
        this.equipment = equipement;
        this.anchor = anchor;
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
        return maximumSpeed;
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
    public void navigateWater() {
        System.out.println("Sailing " + type + " gently on water.");
    }

    @Override
    public boolean hasAnchor() {
        return this.anchor;
    }
}
