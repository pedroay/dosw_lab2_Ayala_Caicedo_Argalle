package edu.eci.dosw.reto4;

/**
 * Concrete Strategy: converts COP → USD.
 */
public class CopToUsdStrategy implements ExchangeRateStrategy {

    private static final double RATE = 1.0 / 4100.0;

    @Override
    public String getSourceCurrency() {
        return "COP";
    }

    @Override
    public String getTargetCurrency() {
        return "USD";
    }

    @Override
    public double convert(double amount) {
        return amount * RATE;
    }
}
