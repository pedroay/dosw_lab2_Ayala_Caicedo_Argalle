package edu.eci.dosw.reto3;

public class EconomyFactory implements VehicleFactory {

    @Override
    public LandVehicle createLandVehicle(String model) {
        String normalized = (model != null) ? model.toLowerCase().trim() : "";

        switch (normalized) {
            case "bicycle":
                return new EconomyLandVehicule(
                    model, 25.0, 200.0, 
                    "Basic Foam Saddle", 
                    "Standard Brakes, Reflectors", 2
                );
            case "motorcycle":
                return new EconomyLandVehicule(
                    model, 110.0, 3500.0, 
                    "Standard Vinyl Seat", 
                    "Helmet Included, Analog Gauges", 2
                );
            case "car":
            default:
                return new EconomyLandVehicule(
                    model, 150.0, 15000.0, 
                    "Standard Cloth Seats", 
                    "FM Radio, Manual Windows, Airbags", 4
                );
        }
    }

    @Override
    public WaterVehicule createWaterVehicle(String model) {
        String normalized = (model != null) ? model.toLowerCase().trim() : "";

        switch (normalized) {
            case "jet ski":
            case "jetski":
                return new EconomyWaterVehicule(
                    model, 55.0, 5500.0, 
                    "Standard Vinyl Seating", 
                    "Life Vest, Emergency Stop Lanyard", false
                );
            case "sailboat":
                return new EconomyWaterVehicule(
                    model, 20.0, 18000.0, 
                    "Basic Compact Cabin", 
                    "Manual Sails, Life Vests, Compass", true
                );
            case "motorboat":
            default:
                return new EconomyWaterVehicule(
                    model, 40.0, 10000.0, 
                    "Basic Plastic Benches", 
                    "Manual Oars, Life Vests, Basic Anchor", true
                );
        }
    }

    @Override
    public AirVehicule createAirVehicle(String model) {
        String normalized = (model != null) ? model.toLowerCase().trim() : "";

        switch (normalized) {
            case "helicopter":
                return new EconomyAirVehicule(
                    model, 190.0, 250000.0, 
                    "Standard Utility Seating", 
                    "Basic VHF Radio, Parachutes", 2500.0
                );
            case "light aircraft":
                return new EconomyAirVehicule(
                    model, 210.0, 85000.0, 
                    "Standard 2-Seat Cabin", 
                    "Analog Altimeter, Basic Transponder", 3000.0
                );
            case "airplane":
            default:
                return new EconomyAirVehicule(
                    model, 550.0, 1200000.0, 
                    "Standard Economy Cabin", 
                    "Standard Navigation, Oxygen Masks", 9000.0
                );
        }
    }
}