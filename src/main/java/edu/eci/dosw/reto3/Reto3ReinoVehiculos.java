package edu.eci.dosw.reto3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Entry point for Challenge 3 — The Kingdom of Vehicles.
 *
 * Demonstrates the Abstract Factory pattern: vehicles are created through
 * factory families (Economy, Luxury, Used) without coupling to concrete classes.
 * Java Streams calculate the total purchase price.
 */
public final class Reto3ReinoVehiculos {

    private Reto3ReinoVehiculos() {
    }

    public static void run() {
        System.out.println("=== Challenge 3 — The Kingdom of Vehicles ===\n");

        VehicleFactory economyFactory = new EconomyFactory();
        VehicleFactory luxuryFactory  = new LuxuryFactory();
        VehicleFactory usedFactory    = new UsedFactory();

        // Select a variety of vehicles from different families and categories
        List<Vehicule> selection = Arrays.asList(
            economyFactory.createLandVehicle("car"),
            economyFactory.createWaterVehicle("motorboat"),
            luxuryFactory.createLandVehicle("motorcycle"),
            luxuryFactory.createAirVehicle("helicopter"),
            usedFactory.createLandVehicle("bicycle"),
            usedFactory.createWaterVehicle("sailboat")
        );

        System.out.println("  Selected vehicles:");
        System.out.println("  --------------------------------------------------");
        selection.forEach(v -> {
            System.out.printf("  %-15s | Category: %-8s | Max speed: %6.1f km/h | Price: $%,.2f%n",
                    v.getType(), v.getCategory(), v.getMaximumSpeed(), v.getPrice());
            System.out.printf("  %15s   Equipment: %s%n", "", v.getEquipment());
            System.out.printf("  %15s   Comfort:   %s%n%n", "", v.getComfort());
        });

        // Java Streams: total price of all selected vehicles
        double total = selection.stream()
                .collect(Collectors.summingDouble(Vehicule::getPrice));

        System.out.println("  --------------------------------------------------");
        System.out.printf("  Total purchase price: $%,.2f%n", total);
        System.out.println("================================================\n");
    }
}
