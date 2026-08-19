package edu.eci.dosw.reto2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Reto2RestauranteHamburguesa {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Restaurant restaurant = new Restaurant();
        restaurant.newHamburguer();
        Map<String, Integer> ingredientesSeleccionados = new HashMap<>();

        // Precios unitarios definidos en tu clase Restaurant
        final int PRECIO_BREAD = 2000;
        final int PRECIO_TOMATO = 1200;
        final int PRECIO_CHEESE = 3500;
        final int PRECIO_MEAT = 8000;

        System.out.println("=== BIENVENIDO AL RESTAURANTE DE HAMBURGUESAS ===");
        System.out.println("Por favor, ingresa las cantidades para cada ingrediente (escribe 0 si no deseas).");

        // 1. Pedir Pan (Bread)
        System.out.print("¿Cuántas unidades de Pan deseas? ");
        int cantidadBread = scanner.nextInt();
        if (cantidadBread > 0) {
            restaurant.withBread(cantidadBread);
            ingredientesSeleccionados.put("Bread", cantidadBread);
        }

        // 2. Pedir Tomate (Tomato)
        System.out.print("¿Cuántas unidades de Tomate deseas? ");
        int cantidadTomato = scanner.nextInt();
        if (cantidadTomato > 0) {
            restaurant.withTomato(cantidadTomato);
            ingredientesSeleccionados.put("Tomato", cantidadTomato);
        }

        // 3. Pedir Queso (Cheese)
        System.out.print("¿Cuántas unidades de Queso deseas? ");
        int cantidadCheese = scanner.nextInt();
        if (cantidadCheese > 0) {
            restaurant.withCheese(cantidadCheese);
            ingredientesSeleccionados.put("Cheese", cantidadCheese);
        }

        // 4. Pedir Carne (Meat)
        System.out.print("¿Cuántas unidades de Carne deseas? ");
        int cantidadMeat = scanner.nextInt();
        if (cantidadMeat > 0) {
            restaurant.withMeat(cantidadMeat);
            ingredientesSeleccionados.put("Meat", cantidadMeat);
        }

        // Calcular el precio final
        int precioTotal = (ingredientesSeleccionados.getOrDefault("Bread", 0) * PRECIO_BREAD) +
                          (ingredientesSeleccionados.getOrDefault("Tomato", 0) * PRECIO_TOMATO) +
                          (ingredientesSeleccionados.getOrDefault("Cheese", 0) * PRECIO_CHEESE) +
                          (ingredientesSeleccionados.getOrDefault("Meat", 0) * PRECIO_MEAT);

        // --- DISPLAY / RESUMEN FINAL ---
        System.out.println("\n================================================");
        System.out.println("            RESUMEN DE TU HAMBURGUESA           ");
        System.out.println("================================================");
        
        if (ingredientesSeleccionados.isEmpty()) {
            System.out.println("No seleccionaste ningún ingrediente.");
            System.out.println("Precio Final: $0");
        } else {
            System.out.println("Every selected ingredient (Ingredientes seleccionados):");
            for (Map.Entry<String, Integer> entry : ingredientesSeleccionados.entrySet()) {
                System.out.println(" - " + entry.getKey() + ": " + entry.getValue() + " unidad(es)");
            }
            
            System.out.println("\nA final summary of the customized hamburger:");
            System.out.println(" Hamburguesa personalizada con " + ingredientesSeleccionados.size() + " tipo(s) de ingrediente(s).");
            
            System.out.println("\nThe final price (Precio final):");
            System.out.println(" $" + precioTotal);
        }
        
        System.out.println("================================================");
        System.out.println("¡Gracias por tu pedido!");
        scanner.close();
    

        System.out.println("¡Gracias por tu pedido!");
        scanner.close();
    }
}


