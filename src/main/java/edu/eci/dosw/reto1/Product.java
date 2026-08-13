package edu.eci.dosw.reto1;

import java.util.Objects;

/**
 * Represents a store product.
 * Immutability: Product unit price cannot be changed after creation.
 * Encapsulation: Attributes are private and final, accessible only via getter methods.
 */
public final class Product {

    private final String name;
    private final double unitPrice;

    public Product(String name, double unitPrice) {
        if (unitPrice < 0) {
            throw new IllegalArgumentException("Unit price cannot be negative");
        }
        this.name = Objects.requireNonNull(name, "Product name cannot be null");
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.unitPrice, unitPrice) == 0 && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, unitPrice);
    }

    @Override
    public String toString() {
        return name + " (COP " + String.format("%,.0f", unitPrice) + ")";
    }
}
