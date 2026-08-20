package edu.eci.dosw.reto3;

public class UsedFactory implements VehicleFactory {

    @Override
    public LandVehicle createLandVehicle(String model) {
        String normalized = (model != null) ? model.toLowerCase().trim() : "";

        switch (normalized) {
            case "bicycle":
                return new UsedLandVehicule(
                    model, 18.0, 80.0, 
                    "Slightly Worn Saddle", 
                    "Scratched Frame, Mechanical Brakes", 2
                );
            case "motorcycle":
                return new UsedLandVehicule(
                    model, 90.0, 1800.0, 
                    "Worn Leather Seat", 
                    "Pre-owned Helmet, Analog Gauges", 2
                );
            case "car":
            default:
                return new UsedLandVehicule(
                    model, 120.0, 6000.0, 
                    "Used Fabric Seats with Minor Wear", 
                    "AM/FM Radio, Spare Tire, Jack", 4
                );
        }
    }

    @Override
    public WaterVehicule createWaterVehicle(String model) {
        String normalized = (model != null) ? model.toLowerCase().trim() : "";

        switch (normalized) {
            case "jet ski":
            case "jetski":
                return new UsedWaterVehicule(
                    model, 40.0, 2500.0, 
                    "Weathered Seating", 
                    "Used Life Vest, Safety Lanyard", false
                );
            case "sailboat":
                return new UsedWaterVehicule(
                    model, 15.0, 8000.0, 
                    "Vintage Compact Cabin", 
                    "Patched Sails, Life Vests, Magnetic Compass", true
                );
            case "motorboat":
            default:
                return new UsedWaterVehicule(
                    model, 30.0, 4000.0, 
                    "Slightly Faded Plastic Benches", 
                    "Manual Oars, Weathered Life Vests, Basic Anchor", true
                );
        }
    }

    @Override
    public AirVehicule createAirVehicle(String model) {
        String normalized = (model != null) ? model.toLowerCase().trim() : "";

        switch (normalized) {
            case "helicopter":
                return new UsedAirVehicule(
                    model, 160.0, 120000.0, 
                    "Aged Utility Seating", 
                    "Analog Flight Gauges, Maintenance Logbook", 2000.0
                );
            case "light aircraft":
                return new UsedAirVehicule(
                    model, 170.0, 40000.0, 
                    "Classic 2-Seat Cockpit", 
                    "Analog Altimeter, Refurbished Radio", 2200.0
                );
            case "airplane":
            default:
                return new UsedAirVehicule(
                    model, 450.0, 600000.0, 
                    "Functional Vintage Cabin", 
                    "Retro Avionics, Standard Safety Gear", 7500.0
                );
        }
    }
}