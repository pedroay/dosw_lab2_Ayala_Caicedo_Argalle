package test.java.edu.eci.dosw.reto3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Reto3Test {

    // -----------------------------------------------------------------
    // EconomyFactory — Land
    // -----------------------------------------------------------------

    @Test
    @DisplayName("EconomyFactory creates a car with category 'Economy'")
    void testEconomyCarCategory() {
        VehicleFactory factory = new EconomyFactory();
        LandVehicle car = factory.createLandVehicle("car");
        assertEquals("Economy", car.getCategory());
    }

    @Test
    @DisplayName("EconomyFactory car has correct price (15000)")
    void testEconomyCarPrice() {
        VehicleFactory factory = new EconomyFactory();
        LandVehicle car = factory.createLandVehicle("car");
        assertEquals(15000.0, car.getPrice(), 0.01);
    }

    @Test
    @DisplayName("EconomyFactory bicycle has 2 wheels")
    void testEconomyBicycleWheels() {
        VehicleFactory factory = new EconomyFactory();
        LandVehicle bicycle = factory.createLandVehicle("bicycle");
        assertEquals(2, bicycle.getWheelCount());
    }

    // -----------------------------------------------------------------
    // LuxuryFactory — Land
    // -----------------------------------------------------------------

    @Test
    @DisplayName("LuxuryFactory creates a car with category 'Luxury'")
    void testLuxuryCarCategory() {
        VehicleFactory factory = new LuxuryFactory();
        LandVehicle car = factory.createLandVehicle("car");
        assertEquals("Luxury", car.getCategory());
    }

    @Test
    @DisplayName("LuxuryFactory car price is higher than EconomyFactory car price")
    void testLuxuryCarMoreExpensiveThanEconomy() {
        LandVehicle economyCar = new EconomyFactory().createLandVehicle("car");
        LandVehicle luxuryCar = new LuxuryFactory().createLandVehicle("car");
        assertTrue(luxuryCar.getPrice() > economyCar.getPrice());
    }

    @Test
    @DisplayName("LuxuryFactory motorcycle has 2 wheels")
    void testLuxuryMotorcycleWheels() {
        VehicleFactory factory = new LuxuryFactory();
        LandVehicle motorcycle = factory.createLandVehicle("motorcycle");
        assertEquals(2, motorcycle.getWheelCount());
    }

    // -----------------------------------------------------------------
    // EconomyFactory — Water
    // -----------------------------------------------------------------

    @Test
    @DisplayName("EconomyFactory creates a motorboat with category 'Economy'")
    void testEconomyMotorboatCategory() {
        VehicleFactory factory = new EconomyFactory();
        WaterVehicule boat = factory.createWaterVehicle("motorboat");
        assertEquals("Economy", boat.getCategory());
    }

    @Test
    @DisplayName("EconomyFactory motorboat price is 10000")
    void testEconomyMotorboatPrice() {
        VehicleFactory factory = new EconomyFactory();
        WaterVehicule boat = factory.createWaterVehicle("motorboat");
        assertEquals(10000.0, boat.getPrice(), 0.01);
    }

    // -----------------------------------------------------------------
    // LuxuryFactory — Air
    // -----------------------------------------------------------------

    @Test
    @DisplayName("LuxuryFactory creates an airplane with category 'Luxury'")
    void testLuxuryAirplaneCategory() {
        VehicleFactory factory = new LuxuryFactory();
        AirVehicule plane = factory.createAirVehicle("airplane");
        assertEquals("Luxury", plane.getCategory());
    }

    @Test
    @DisplayName("LuxuryFactory airplane price exceeds EconomyFactory airplane price")
    void testLuxuryAirplaneMoreExpensive() {
        AirVehicule economyPlane = new EconomyFactory().createAirVehicle("airplane");
        AirVehicule luxuryPlane = new LuxuryFactory().createAirVehicle("airplane");
        assertTrue(luxuryPlane.getPrice() > economyPlane.getPrice());
    }

    // -----------------------------------------------------------------
    // Polymorphism — Vehicule interface
    // -----------------------------------------------------------------

    @Test
    @DisplayName("All vehicle types expose getType() via the Vehicule interface")
    void testPolymorphicGetType() {
        VehicleFactory factory = new EconomyFactory();
        Vehicule land = factory.createLandVehicle("car");
        Vehicule water = factory.createWaterVehicle("motorboat");
        Vehicule air = factory.createAirVehicle("airplane");

        assertNotNull(land.getType());
        assertNotNull(water.getType());
        assertNotNull(air.getType());
    }

    @Test
    @DisplayName("UsedFactory creates vehicles with category 'Used'")
    void testUsedFactoryCategory() {
        VehicleFactory factory = new UsedFactory();
        LandVehicle car = factory.createLandVehicle("car");
        assertEquals("Used", car.getCategory());
    }
}