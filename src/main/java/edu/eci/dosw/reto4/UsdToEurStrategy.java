package edu.eci.dosw.reto4;

/**
 * Concrete Strategy: converts USD → EUR.
 */
public class UsdToEurStrategy implements ExchangeRateStrategy {

    private static final double RATE = 0.92;

    @Override
    public String getSourceCurrency() {
        return "USD";
    }

    @Override
    public String getTargetCurrency() {
        return "EUR";
    }

    @Override
    public double convert(double amount) {
        return amount * RATE;
    }
}
