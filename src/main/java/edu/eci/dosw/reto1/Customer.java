package edu.eci.dosw.reto1;

import java.util.Objects;

/**
 * Represents a store customer.
 */
public final class Customer {

    private final String name;
    private final CustomerType customerType;

    public Customer(String name, CustomerType customerType) {
        this.name = Objects.requireNonNull(name, "Customer name cannot be null");
        this.customerType = Objects.requireNonNull(customerType, "Customer type cannot be null");
    }

    public String getName() {
        return name;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public double calculateDiscount(double subtotal) {
        return customerType.calculateDiscount(subtotal);
    }

    @Override
    public String toString() {
        return name + " [" + customerType + "]";
    }
}
