package edu.eci.dosw.reto1;

/**
 * Customer types with their corresponding discount rates.
 * NEW: 5% discount (0.05)
 * FREQUENT: 10% discount (0.10)
 */
public enum CustomerType implements DiscountStrategy {
    NEW("Nuevo", 0.05),
    FREQUENT("Frecuente", 0.10);

    private final String displayName;
    private final double discountPercentage;

    CustomerType(String displayName, double discountPercentage) {
        this.displayName = displayName;
        this.discountPercentage = discountPercentage;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    @Override
    public double calculateDiscount(double subtotal) {
        if (subtotal < 0) {
            return 0;
        }
        return subtotal * discountPercentage;
    }

    @Override
    public String toString() {
        return displayName + " (" + (int)(discountPercentage * 100) + "% desc.)";
    }
}
