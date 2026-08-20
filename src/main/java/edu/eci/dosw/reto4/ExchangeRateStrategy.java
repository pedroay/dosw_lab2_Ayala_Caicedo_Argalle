package edu.eci.dosw.reto4;

/**
 * Strategy interface for currency exchange rate calculation.
 * Each implementation defines the conversion rate for a specific currency pair.
 */
public interface ExchangeRateStrategy {

    /**
     * Returns the source currency code (e.g., "USD").
     */
    String getSourceCurrency();

    /**
     * Returns the destination currency code (e.g., "EUR").
     */
    String getTargetCurrency();

    /**
     * Converts the given amount from the source currency to the target currency.
     *
     * @param amount the amount in the source currency
     * @return the converted amount in the target currency
     */
    double convert(double amount);
}
