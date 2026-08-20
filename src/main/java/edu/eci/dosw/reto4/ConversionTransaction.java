package edu.eci.dosw.reto4;

/**
 * Represents a single currency conversion transaction.
 * Stores the source amount, currencies, and the converted result.
 */
public class ConversionTransaction {

    private final double sourceAmount;
    private final String sourceCurrency;
    private final double convertedAmount;
    private final String targetCurrency;

    public ConversionTransaction(double sourceAmount, String sourceCurrency,
                                 double convertedAmount, String targetCurrency) {
        this.sourceAmount = sourceAmount;
        this.sourceCurrency = sourceCurrency;
        this.convertedAmount = convertedAmount;
        this.targetCurrency = targetCurrency;
    }

    public double getSourceAmount() {
        return sourceAmount;
    }

    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public double getConvertedAmount() {
        return convertedAmount;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    @Override
    public String toString() {
        return String.format("  %.2f %s  →  %.4f %s",
                sourceAmount, sourceCurrency, convertedAmount, targetCurrency);
    }
}
