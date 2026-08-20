package edu.eci.dosw.reto5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Reto5Test {

    @Test
    @DisplayName("Simple coffee base price and description")
    void testSimpleCoffee() {
        Coffe coffee = new SimpleCoffee("Simple Coffee", 3000.0);
        assertEquals("Simple Coffee", coffee.getDescription());
        assertEquals(3000.0, coffee.getPrice(), 0.001);
    }

    @Test
    @DisplayName("Coffee with Milk decorator adds price and description")
    void testMilkDecorator() {
        Coffe coffee = new SimpleCoffee("Simple Coffee", 3000.0);
        coffee = new MilkDecorator(coffee);
        assertEquals("Simple Coffee + Milk", coffee.getDescription());
        assertEquals(4000.0, coffee.getPrice(), 0.001);
    }

    @Test
    @DisplayName("Coffee with Chocolate and Whipped Cream decorators")
    void testMultipleDecorators() {
        Coffe coffee = new SimpleCoffee("Espresso", 3500.0);
        coffee = new ChocolateDecorator(coffee);
        coffee = new WhippedCreamDecorator(coffee);

        assertEquals("Espresso + Chocolate + Whipped Cream", coffee.getDescription());
        assertEquals(3500.0 + 1500.0 + 2000.0, coffee.getPrice(), 0.001);
    }

    @Test
    @DisplayName("Coffee with all decorators stacked")
    void testAllDecorators() {
        Coffe coffee = new SimpleCoffee("Coffee", 2000.0);
        coffee = new MilkDecorator(coffee);         // +1000
        coffee = new ChocolateDecorator(coffee);    // +1500
        coffee = new CaramelDecorator(coffee);      // +1200
        coffee = new WhippedCreamDecorator(coffee); // +2000
        coffee = new MintDecorator(coffee);         // +1300
        coffee = new CustomToppingDecorator(coffee, "Cinnamon", 500.0); // +500

        assertEquals("Coffee + Milk + Chocolate + Caramel + Whipped Cream + Mint + Cinnamon", coffee.getDescription());
        assertEquals(2000.0 + 1000.0 + 1500.0 + 1200.0 + 2000.0 + 1300.0 + 500.0, coffee.getPrice(), 0.001);
    }
}