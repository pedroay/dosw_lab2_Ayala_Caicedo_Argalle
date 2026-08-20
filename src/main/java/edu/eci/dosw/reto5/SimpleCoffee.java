package edu.eci.dosw.reto5;

public class SimpleCoffee implements Coffe {
    private String name;
    private double basePrice;


    public SimpleCoffee(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    @Override
    public String getDescription() {
        return name;
    }

    @Override
    public double getPrice() {
        return basePrice;
    }
}