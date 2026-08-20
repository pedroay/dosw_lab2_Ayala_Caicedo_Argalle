package edu.eci.dosw.reto4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Context class in the Strategy pattern.
 * Holds a registry of exchange rate strategies and performs conversions.
 * Uses Java Streams to group and accumulate transaction summaries.
 */
public class CurrencyConverter {

    /** Registry of strategies keyed by "SOURCE_TARGET" (e.g., "USD_EUR"). */
    private final Map<String, ExchangeRateStrategy> strategies = new HashMap<>();

    /** History of all executed transactions. */
    private final List<ConversionTransaction> history = new ArrayList<>();

    /**
     * Registers an exchange rate strategy.
     * Overwrites any previously registered strategy for the same currency pair.
     *
     * @param strategy the strategy to register
     */
    public void registerStrategy(ExchangeRateStrategy strategy) {
        String key = buildKey(strategy.getSourceCurrency(), strategy.getTargetCurrency());
        strategies.put(key, strategy);
    }

    /**
     * Converts an amount from {@code source} to {@code target} using the
     * registered strategy for that pair. Records the result in the history.
     *
     * @param amount         amount to convert (must be positive)
     * @param sourceCurrency ISO currency code of the source
     * @param targetCurrency ISO currency code of the target
     * @return the converted amount
     * @throws IllegalArgumentException if no strategy is registered for the pair
     *                                  or if the amount is not positive
     */
    public double convert(double amount, String sourceCurrency, String targetCurrency) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        String key = buildKey(sourceCurrency, targetCurrency);
        ExchangeRateStrategy strategy = strategies.get(key);
        if (strategy == null) {
            throw new IllegalArgumentException(
                    "No exchange strategy registered for pair: " + sourceCurrency + " → " + targetCurrency);
        }
        double result = strategy.convert(amount);
        history.add(new ConversionTransaction(amount, sourceCurrency, result, targetCurrency));
        return result;
    }

    /**
     * Returns an unmodifiable view of the transaction history.
     */
    public List<ConversionTransaction> getHistory() {
        return List.copyOf(history);
    }

    /**
     * Uses Java Streams to compute the total converted amount per target currency
     * across all recorded transactions.
     *
     * @return map of target currency → total converted amount
     */
    public Map<String, Double> totalsByTargetCurrency() {
        return history.stream()
                .collect(Collectors.groupingBy(
                        ConversionTransaction::getTargetCurrency,
                        Collectors.summingDouble(ConversionTransaction::getConvertedAmount)));
    }

    /**
     * Prints a formatted summary of all transactions and the grouped totals.
     */
    public void printSummary() {
        System.out.println("================================================");
        System.out.println("         CURRENCY EXCHANGE — SUMMARY            ");
        System.out.println("================================================");

        if (history.isEmpty()) {
            System.out.println("  No transactions recorded.");
        } else {
            System.out.println("  Transaction History:");
            history.forEach(System.out::println);

            System.out.println("\n  Totals by Target Currency:");
            totalsByTargetCurrency()
                    .forEach((currency, total) ->
                            System.out.printf("  %s  →  %.4f%n", currency, total));
        }

        System.out.println("================================================");
    }

    // --- helpers ---

    private String buildKey(String source, String target) {
        return source.toUpperCase() + "_" + target.toUpperCase();
    }
}
