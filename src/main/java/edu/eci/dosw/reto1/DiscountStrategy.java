package edu.eci.dosw.reto1;

/**
 * Strategy interface for calculating customer discounts.
 * Follows Open/Closed Principle: New discount rules can be added without modifying existing cart logic.
 */
@FunctionalInterface
public interface DiscountStrategy {
    double calculateDiscount(double subtotal);
}
