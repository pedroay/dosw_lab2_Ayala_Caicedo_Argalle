package edu.eci.dosw.reto5;

// Decorador 1: Milk (COP 1,000)
public class MilkDecorator extends ToppingDecorator {
    public MilkDecorator(Coffe coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Milk";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 1000.0;
    }
}
