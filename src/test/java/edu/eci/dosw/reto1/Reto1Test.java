package edu.eci.dosw.reto1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Reto1Test {

    @Test
    @DisplayName("Should create immutable product with valid getters")
    void testProductImmutabilityAndEncapsulation() {
        Product tshirt = new Product("Camiseta", 20000);
        assertEquals("Camiseta", tshirt.getName());
        assertEquals(20000, tshirt.getUnitPrice());
    }

    @Test
    @DisplayName("Should throw exception when creating product with negative price")
    void testProductNegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> new Product("Invalido", -100));
    }

    @Test
    @DisplayName("Should calculate 5% discount for NEW customer")
    void testNewCustomerDiscount() {
        Customer customer = new Customer("Juan", CustomerType.NEW);
        ShoppingCart cart = new ShoppingCart(customer);
        cart.addItem(new Product("Pantalón", 50000), 2); // 100,000 subtotal

        assertEquals(100000, cart.calculateSubtotal());
        assertEquals(5000, cart.calculateDiscount()); // 5% of 100,000
        assertEquals(95000, cart.calculateTotal());
    }

    @Test
    @DisplayName("Should calculate 10% discount for FREQUENT customer")
    void testFrequentCustomerDiscount() {
        Customer customer = new Customer("Maria", CustomerType.FREQUENT);
        ShoppingCart cart = new ShoppingCart(customer);
        cart.addItem(new Product("Pantalón", 50000), 2); // 100,000 subtotal

        assertEquals(100000, cart.calculateSubtotal());
        assertEquals(10000, cart.calculateDiscount()); // 10% of 100,000
        assertEquals(90000, cart.calculateTotal());
    }

    @Test
    @DisplayName("Should correctly calculate example scenario from workshop guide")
    void testExampleScenarioFromGuide() {
        Product tshirt = new Product("Camiseta", 20000);
        Product cookies = new Product("Galletas", 500);
        Product juice = new Product("Jugo Natural", 3000);

        Customer customer = new Customer("Carlos Gómez", CustomerType.FREQUENT);
        ShoppingCart cart = new ShoppingCart(customer);
        cart.addItem(tshirt, 2);  // 40,000
        cart.addItem(cookies, 3); // 1,500
        cart.addItem(juice, 5);   // 15,000

        // Subtotal expected: 56,500
        assertEquals(56500, cart.calculateSubtotal());
        assertEquals(56500, cart.calculateSubtotalWithReduce());

        // Discount expected: 5,650 (10%)
        assertEquals(5650, cart.calculateDiscount());

        // Total expected: 50,850
        assertEquals(50850, cart.calculateTotal());
    }

    @Test
    @DisplayName("Should generate valid receipt output")
    void testReceiptGeneration() {
        Product tshirt = new Product("Camiseta", 20000);
        Customer customer = new Customer("Ana", CustomerType.NEW);
        ShoppingCart cart = new ShoppingCart(customer);
        cart.addItem(tshirt, 1);

        Receipt receipt = new Receipt(cart);
        String text = receipt.generateReceiptText();

        assertTrue(text.contains("TIENDA DON PEPE"));
        assertTrue(text.contains("Ana"));
        assertTrue(text.contains("Camiseta"));
        assertTrue(text.contains("Subtotal"));
        assertTrue(text.contains("Total a Pagar"));
    }
}
