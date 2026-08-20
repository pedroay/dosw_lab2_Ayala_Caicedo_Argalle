package edu.eci.dosw.reto2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Reto2Test {

    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        restaurant = new Restaurant();
        restaurant.newHamburguer();
    }

    @Test
    @DisplayName("Bread only: price should equal 1 * 2000 = 2000")
    void testBreadOnlyPrice() {
        restaurant.withBread(1);
        assertEquals(2000.0, restaurant.hamburguerPrice(), 0.01);
    }

    @Test
    @DisplayName("Multiple breads: price should equal quantity * unit price")
    void testMultipleBreadUnits() {
        restaurant.withBread(3);
        assertEquals(6000.0, restaurant.hamburguerPrice(), 0.01);
    }

    @Test
    @DisplayName("Meat only: price should equal 1 * 8000 = 8000")
    void testMeatOnlyPrice() {
        restaurant.withMeat(1);
        assertEquals(8000.0, restaurant.hamburguerPrice(), 0.01);
    }

    @Test
    @DisplayName("Cheese only: price should equal 1 * 3500 = 3500")
    void testCheeseOnlyPrice() {
        restaurant.withCheese(1);
        assertEquals(3500.0, restaurant.hamburguerPrice(), 0.01);
    }

    @Test
    @DisplayName("Tomato only: price should equal 1 * 1200 = 1200")
    void testTomatoOnlyPrice() {
        restaurant.withTomato(1);
        assertEquals(1200.0, restaurant.hamburguerPrice(), 0.01);
    }

    @Test
    @DisplayName("Full hamburger: bread(1) + tomato(1) + cheese(1) + meat(1) = 14700")
    void testFullHamburgerPrice() {
        restaurant.withBread(1);
        restaurant.withTomato(1);
        restaurant.withCheese(1);
        restaurant.withMeat(1);
        // 2000 + 1200 + 3500 + 8000 = 14700
        assertEquals(14700.0, restaurant.hamburguerPrice(), 0.01);
    }

    @Test
    @DisplayName("Empty hamburger (no ingredients) should have price 0")
    void testEmptyHamburgerPrice() {
        assertEquals(0.0, restaurant.hamburguerPrice(), 0.01);
    }

    @Test
    @DisplayName("Mixed quantities: bread(2) + meat(1) + cheese(2) = 4000+8000+7000 = 19000")
    void testMixedQuantities() {
        restaurant.withBread(2);   // 4000
        restaurant.withMeat(1);    // 8000
        restaurant.withCheese(2);  // 7000
        assertEquals(19000.0, restaurant.hamburguerPrice(), 0.01);
    }

    @Test
    @DisplayName("Ingredient encapsulation: name and price must be accessible")
    void testIngredientGetters() {
        Ingredient ingredient = new Ingredient("Bread", 2000);
        assertEquals("Bread", ingredient.getName());
        assertEquals(2000.0, ingredient.getPrice(), 0.01);
    }
}
