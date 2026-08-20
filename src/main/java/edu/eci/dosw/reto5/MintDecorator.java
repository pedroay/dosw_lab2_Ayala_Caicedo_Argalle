package edu.eci.dosw.reto5;

public class MintDecorator extends ToppingDecorator {
    public MintDecorator(Coffe coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Mint";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 1300.0;
    }
}
