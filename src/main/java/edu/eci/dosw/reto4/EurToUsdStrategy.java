package edu.eci.dosw.reto4;

/**
 * Concrete Strategy: converts EUR → USD.
 */
public class EurToUsdStrategy implements ExchangeRateStrategy {

    private static final double RATE = 1.09;

    @Override
    public String getSourceCurrency() {
        return "EUR";
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
