package edu.eci.dosw.reto4;

/**
 * Concrete Strategy: converts EUR → JPY.
 */
public class EurToJpyStrategy implements ExchangeRateStrategy {

    private static final double RATE = 162.80;

    @Override
    public String getSourceCurrency() {
        return "EUR";
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
