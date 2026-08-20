package edu.eci.dosw.reto5;

public class CustomToppingDecorator extends ToppingDecorator {
    private String customName;
    private double customPrice;

    public CustomToppingDecorator(Coffe coffee, String customName, double customPrice) {
        super(coffee);
        this.customName = customName;
        this.customPrice = customPrice;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + " + customName;
    }

    @Override
    public double getPrice() {
        return super.getPrice() + customPrice;
    }
}
