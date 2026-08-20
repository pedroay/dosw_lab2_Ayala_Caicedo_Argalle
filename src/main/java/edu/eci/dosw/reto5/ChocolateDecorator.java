package edu.eci.dosw.reto5;

// Decorador 2: Chocolate (COP 1,500)
public class ChocolateDecorator extends ToppingDecorator {
    public ChocolateDecorator(Coffe coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Chocolate";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 1500.0;
    }
}
