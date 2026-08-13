package edu.eci.dosw.reto1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Encapsulates the shopping cart state and performs calculations using Java Streams.
 */
public final class ShoppingCart {

    private final Customer customer;
    private final List<CartItem> items;

    public ShoppingCart(Customer customer) {
        this.customer = Objects.requireNonNull(customer, "Customer cannot be null");
        this.items = new ArrayList<>();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void addItem(Product product, int quantity) {
        Objects.requireNonNull(product, "Product cannot be null");
        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    /**
     * Calculates the subtotal price of all cart items using Java Streams (mapToDouble and sum).
     */
    public double calculateSubtotal() {
        return items.stream()
                .filter(item -> item.getQuantity() > 0)
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

    /**
     * Alternative subtotal calculation demonstrating Java Streams reduce operation.
     */
    public double calculateSubtotalWithReduce() {
        return items.stream()
                .map(CartItem::getTotalPrice)
                .reduce(0.0, Double::sum);
    }

    /**
     * Calculates the discount applied based on the customer type.
     */
    public double calculateDiscount() {
        double subtotal = calculateSubtotal();
        return customer.calculateDiscount(subtotal);
    }

    /**
     * Calculates the final total amount to pay.
     */
    public double calculateTotal() {
        return calculateSubtotal() - calculateDiscount();
    }
}
