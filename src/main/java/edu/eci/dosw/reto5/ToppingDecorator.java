package edu.eci.dosw.reto5;

//base decorator
public abstract class ToppingDecorator implements Coffe {
    protected Coffe wrappee; // El objeto de tipo Coffee que estamos envolviendo

    public ToppingDecorator(Coffe coffee) {
        this.wrappee = coffee;
    }

    // Delegación base hacia el componente envuelto
    @Override
    public String getDescription() {
        return wrappee.getDescription(); 
    }

    @Override
    public double getPrice() {
        return wrappee.getPrice();
    }
}
