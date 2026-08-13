package edu.eci.dosw.reto1;

import java.util.Objects;

/**
 * Represents a line item in a shopping cart containing a product and its quantity.
 */
public final class CartItem {

    private final Product product;
    private final int quantity;

    public CartItem(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        this.product = Objects.requireNonNull(product, "Product cannot be null");
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return product.getUnitPrice() * quantity;
    }

    @Override
    public String toString() {
        return String.format("%d x %s = COP %,.0f", quantity, product.getName(), getTotalPrice());
    }
}
