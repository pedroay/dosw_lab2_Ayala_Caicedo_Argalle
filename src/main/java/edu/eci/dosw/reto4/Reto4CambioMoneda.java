package edu.eci.dosw.reto4;

/**
 * Entry point for Challenge 4 — The Currency Exchange Scam.
 *
 * Demonstrates the Strategy pattern: each currency pair is handled
 * by its own ExchangeRateStrategy, so there is never a single shared rate
 * applied to every conversion.
 */
public class Reto4CambioMoneda {

    private Reto4CambioMoneda() {
    }

    public static void run() {
        CurrencyConverter converter = new CurrencyConverter();

        // Register all supported exchange rate strategies
        converter.registerStrategy(new UsdToEurStrategy());
        converter.registerStrategy(new EurToUsdStrategy());
        converter.registerStrategy(new UsdToJpyStrategy());
        converter.registerStrategy(new UsdToCopStrategy());
        converter.registerStrategy(new EurToJpyStrategy());
        converter.registerStrategy(new CopToUsdStrategy());

        System.out.println("=== Challenge 4 — The Currency Exchange Scam ===\n");

        // Multiple transactions
        converter.convert(100.0,  "USD", "EUR");
        converter.convert(200.0,  "USD", "JPY");
        converter.convert(500.0,  "USD", "COP");
        converter.convert(85.0,   "EUR", "USD");
        converter.convert(150.0,  "EUR", "JPY");
        converter.convert(820000, "COP", "USD");

        converter.printSummary();
    }
}
