package edu.eci.dosw.reto4;

/**
 * Concrete Strategy: converts USD → JPY.
 */
public class UsdToJpyStrategy implements ExchangeRateStrategy {

    private static final double RATE = 149.50;

    @Override
    public String getSourceCurrency() {
        return "USD";
    }

    @Override
    public String getTargetCurrency() {
        return "JPY";
    }

    @Override
    public double convert(double amount) {
        return amount * RATE;
    }
}
