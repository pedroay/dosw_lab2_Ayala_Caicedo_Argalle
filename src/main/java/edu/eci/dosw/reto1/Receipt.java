package edu.eci.dosw.reto1;

import java.util.Objects;

/**
 * Generates and formats sales receipts.
 */
public final class Receipt {

    private final ShoppingCart cart;

    public Receipt(ShoppingCart cart) {
        this.cart = Objects.requireNonNull(cart, "ShoppingCart cannot be null");
    }

    public String generateReceiptText() {
        StringBuilder sb = new StringBuilder();
        sb.append("=========================================\n");
        sb.append("         TIENDA DON PEPE - RECIBO        \n");
        sb.append("=========================================\n");
        sb.append("Cliente: ").append(cart.getCustomer().getName()).append("\n");
        sb.append("Tipo Cliente: ").append(cart.getCustomer().getCustomerType().getDisplayName()).append("\n");
        sb.append("-----------------------------------------\n");
        sb.append("Productos Comprados:\n");

        // Use Java Streams forEach to format each purchased item
        cart.getItems().stream().forEach(item -> {
            sb.append(String.format(" - %-20s x %2d | COP %,9.0f\n",
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.getTotalPrice()));
        });

        sb.append("-----------------------------------------\n");
        sb.append(String.format("Subtotal:               COP %,12.0f\n", cart.calculateSubtotal()));
        sb.append(String.format("Descuento (%-10s): - COP %,12.0f\n",
                (int)(cart.getCustomer().getCustomerType().getDiscountPercentage() * 100) + "%",
                cart.calculateDiscount()));
        sb.append(String.format("Total a Pagar:          COP %,12.0f\n", cart.calculateTotal()));
        sb.append("=========================================\n");

        return sb.toString();
    }

    public void printReceipt() {
        System.out.println(generateReceiptText());
    }
}
