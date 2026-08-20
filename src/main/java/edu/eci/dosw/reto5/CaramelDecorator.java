package edu.eci.dosw.reto5;

public class CaramelDecorator extends ToppingDecorator {
    public CaramelDecorator(Coffe coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Caramel";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 1200.0;
    }
}
