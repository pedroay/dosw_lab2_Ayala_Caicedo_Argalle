package edu.eci.dosw.reto4;

/**
 * Concrete Strategy: converts USD → COP.
 */
public class UsdToCopStrategy implements ExchangeRateStrategy {

    private static final double RATE = 4100.0;

    @Override
    public String getSourceCurrency() {
        return "USD";
    }

    @Override
    public String getTargetCurrency() {
        return "COP";
    }

    @Override
    public double convert(double amount) {
        return amount * RATE;
    }
}
