package edu.eci.dosw.reto5;

public class WhippedCreamDecorator extends ToppingDecorator {
    public WhippedCreamDecorator(Coffe coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Whipped Cream";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 2000.0;
    }
}