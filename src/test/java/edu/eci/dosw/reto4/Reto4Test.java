package test.java.edu.eci.dosw.reto4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Reto4Test {

    private CurrencyConverter converter;

    @BeforeEach
    void setUp() {
        converter = new CurrencyConverter();
        converter.registerStrategy(new UsdToEurStrategy());
        converter.registerStrategy(new EurToUsdStrategy());
        converter.registerStrategy(new UsdToJpyStrategy());
        converter.registerStrategy(new UsdToCopStrategy());
        converter.registerStrategy(new EurToJpyStrategy());
        converter.registerStrategy(new CopToUsdStrategy());
    }

    // -----------------------------------------------------------------
    // Individual strategy conversions
    // -----------------------------------------------------------------

    @Test
    @DisplayName("USD → EUR: 100 USD should convert to 92 EUR (rate 0.92)")
    void testUsdToEur() {
        double result = converter.convert(100.0, "USD", "EUR");
        assertEquals(92.0, result, 0.001);
    }

    @Test
    @DisplayName("EUR → USD: 100 EUR should convert to 109 USD (rate 1.09)")
    void testEurToUsd() {
        double result = converter.convert(100.0, "EUR", "USD");
        assertEquals(109.0, result, 0.001);
    }

    @Test
    @DisplayName("USD → JPY: 1 USD should convert to 149.50 JPY")
    void testUsdToJpy() {
        double result = converter.convert(1.0, "USD", "JPY");
        assertEquals(149.50, result, 0.001);
    }

    @Test
    @DisplayName("USD → COP: 1 USD should convert to 4100 COP")
    void testUsdToCop() {
        double result = converter.convert(1.0, "USD", "COP");
        assertEquals(4100.0, result, 0.001);
    }

    @Test
    @DisplayName("COP → USD: 4100 COP should convert back to approximately 1 USD")
    void testCopToUsd() {
        double result = converter.convert(4100.0, "COP", "USD");
        assertEquals(1.0, result, 0.001);
    }

    @Test
    @DisplayName("EUR → JPY: 1 EUR should convert to 162.80 JPY")
    void testEurToJpy() {
        double result = converter.convert(1.0, "EUR", "JPY");
        assertEquals(162.80, result, 0.001);
    }

    // -----------------------------------------------------------------
    // Strategy independence: different pairs use different rates
    // -----------------------------------------------------------------

    @Test
    @DisplayName("USD→EUR and EUR→USD must use different rates (not a shared rate)")
    void testUsdEurAndEurUsdAreDifferentRates() {
        double usdToEur = new UsdToEurStrategy().convert(1.0);
        double eurToUsd = new EurToUsdStrategy().convert(1.0);
        assertNotEquals(usdToEur, eurToUsd,
                "USD→EUR and EUR→USD must NOT share the same rate");
    }

    // -----------------------------------------------------------------
    // Transaction history
    // -----------------------------------------------------------------

    @Test
    @DisplayName("History should record every executed transaction")
    void testHistoryRecordsTransactions() {
        converter.convert(100.0, "USD", "EUR");
        converter.convert(200.0, "USD", "JPY");
        assertEquals(2, converter.getHistory().size());
    }

    @Test
    @DisplayName("Transaction stores correct source and target data")
    void testTransactionData() {
        converter.convert(50.0, "USD", "EUR");
        ConversionTransaction tx = converter.getHistory().get(0);
        assertEquals(50.0, tx.getSourceAmount(), 0.001);
        assertEquals("USD", tx.getSourceCurrency());
        assertEquals("EUR", tx.getTargetCurrency());
        assertEquals(46.0, tx.getConvertedAmount(), 0.001); // 50 * 0.92
    }

    // -----------------------------------------------------------------
    // Streams — totals by target currency
    // -----------------------------------------------------------------

    @Test
    @DisplayName("totalsByTargetCurrency should sum conversions into the same target")
    void testTotalsByTargetCurrency() {
        converter.convert(100.0, "USD", "EUR"); // 92 EUR
        converter.convert(50.0,  "USD", "EUR"); // 46 EUR
        converter.convert(1.0,   "USD", "JPY"); // 149.50 JPY

        Map<String, Double> totals = converter.totalsByTargetCurrency();
        assertEquals(138.0, totals.get("EUR"), 0.001);
        assertEquals(149.50, totals.get("JPY"), 0.001);
    }

    // -----------------------------------------------------------------
    // Error handling
    // -----------------------------------------------------------------

    @Test
    @DisplayName("Converting with an unregistered pair should throw IllegalArgumentException")
    void testUnregisteredPairThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> converter.convert(100.0, "JPY", "COP"));
    }

    @Test
    @DisplayName("Converting a non-positive amount should throw IllegalArgumentException")
    void testNonPositiveAmountThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> converter.convert(0.0, "USD", "EUR"));
        assertThrows(IllegalArgumentException.class,
                () -> converter.convert(-10.0, "USD", "EUR"));
    }

    // -----------------------------------------------------------------
    // Dynamic strategy registration
    // -----------------------------------------------------------------

    @Test
    @DisplayName("A new strategy can be registered at runtime without modifying existing code")
    void testDynamicStrategyRegistration() {
        // Register a custom strategy inline (lambda-style via anonymous class)
        converter.registerStrategy(new ExchangeRateStrategy() {
            @Override public String getSourceCurrency() { return "JPY"; }
            @Override public String getTargetCurrency() { return "USD"; }
            @Override public double convert(double amount) { return amount / 149.50; }
        });
        double result = converter.convert(149.50, "JPY", "USD");
        assertEquals(1.0, result, 0.01);
    }
}