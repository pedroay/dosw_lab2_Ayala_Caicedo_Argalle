package edu.eci.dosw.reto5;

import java.util.Scanner;

/**
 * Runner class for Challenge 5 — The Coffee Shop (Decorator pattern).
 */
public class Reto5Cafeteria {

    public static void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================================");
        System.out.println("       BIENVENIDO A LA CAFETERIA (RETO 5)        ");
        System.out.println("=================================================");

        // 1. Seleccionar o configurar el café base
        System.out.println("\nSelecciona tu café base:");
        System.out.println("1. Café Simple / Expreso ($3,000)");
        System.out.println("2. Café Americano ($2,500)");
        System.out.println("3. Café Colombiano Tradicional ($2,000)");
        System.out.println("4. Personalizar café base");
        System.out.print("Opción: ");

        String opcionBase = scanner.nextLine().trim();
        Coffe miCafe;

        switch (opcionBase) {
            case "2":
                miCafe = new SimpleCoffee("Café Americano", 2500.0);
                break;
            case "3":
                miCafe = new SimpleCoffee("Café Colombiano Tradicional", 2000.0);
                break;
            case "4":
                System.out.print("Ingresa el nombre del café base: ");
                String nombre = scanner.nextLine().trim();
                System.out.print("Ingresa el precio base: ");
                double precio = Double.parseDouble(scanner.nextLine().trim());
                miCafe = new SimpleCoffee(nombre, precio);
                break;
            case "1":
            default:
                miCafe = new SimpleCoffee("Café Simple", 3000.0);
                break;
        }

        // 2. Agregar decoradores/toppings
        boolean agregandoToppings = true;
        while (agregandoToppings) {
            System.out.println("\n--- Café actual: " + miCafe.getDescription() + " | Precio acumulado: $"
                    + miCafe.getPrice() + " ---");
            System.out.println("¿Deseas agregar algún topping o ingrediente extra?");
            System.out.println("1. Milk / Leche (+$1,000)");
            System.out.println("2. Chocolate (+$1,500)");
            System.out.println("3. Caramel / Caramelo (+$1,200)");
            System.out.println("4. Whipped Cream / Crema Batida (+$2,000)");
            System.out.println("5. Mint / Menta (+$1,300)");
            System.out.println("6. Topping personalizado");
            System.out.println("0. Finalizar orden y pagar");
            System.out.print("Elige una opción: ");

            String opcionTopping = scanner.nextLine().trim();

            switch (opcionTopping) {
                case "1":
                    miCafe = new MilkDecorator(miCafe);
                    System.out.println("-> Se añadió Leche.");
                    break;
                case "2":
                    miCafe = new ChocolateDecorator(miCafe);
                    System.out.println("-> Se añadió Chocolate.");
                    break;
                case "3":
                    miCafe = new CaramelDecorator(miCafe);
                    System.out.println("-> Se añadió Caramelo.");
                    break;
                case "4":
                    miCafe = new WhippedCreamDecorator(miCafe);
                    System.out.println("-> Se añadió Crema Batida.");
                    break;
                case "5":
                    miCafe = new MintDecorator(miCafe);
                    System.out.println("-> Se añadió Menta.");
                    break;
                case "6":
                    System.out.print("Nombre del topping personalizado: ");
                    String customName = scanner.nextLine().trim();
                    System.out.print("Precio del topping: ");
                    double customPrice = Double.parseDouble(scanner.nextLine().trim());
                    miCafe = new CustomToppingDecorator(miCafe, customName, customPrice);
                    System.out.println("-> Se añadió " + customName + ".");
                    break;
                case "0":
                    agregandoToppings = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
                    break;
            }
        }

        // 3. Resumen final del pedido
        System.out.println("\n=================================================");
        System.out.println("             RESUMEN DE TU PEDIDO                ");
        System.out.println("=================================================");
        System.out.println("Detalle del producto: " + miCafe.getDescription());
        System.out.printf("Total a pagar: $%,.2f COP%n", miCafe.getPrice());
        System.out.println("=================================================");
        System.out.println("¡Gracias por tu compra en la Cafetería!\n");
    }
}
