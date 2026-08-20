package edu.eci.dosw.reto3;

public class LuxuryFactory implements VehicleFactory {

    @Override
    public LandVehicle createLandVehicle(String model) {
        String normalized = (model != null) ? model.toLowerCase().trim() : "";

        switch (normalized) {
            case "bicycle":
                return new LuxuryLandVehicule(
                    model, 60.0, 8000.0, 
                    "Ergonomic Carbon Saddle", 
                    "Carbon Fiber Frame, Electronic Shifting", 2
                );
            case "motorcycle":
                return new LuxuryLandVehicule(
                    model, 240.0, 35000.0, 
                    "Heated Grips & Custom Leather Seat", 
                    "Cornering ABS, Cruise Control, Bluetooth", 2
                );
            case "car":
            default:
                return new LuxuryLandVehicule(
                    model, 280.0, 85000.0, 
                    "VIP Leather Seats & Climate Control", 
                    "Autopilot, Premium Sound, Massage Seats", 4
                );
        }
    }

    @Override
    public WaterVehicule createWaterVehicle(String model) {
        String normalized = (model != null) ? model.toLowerCase().trim() : "";

        switch (normalized) {
            case "jet ski":
            case "jetski":
                return new LuxuryWaterVehicule(
                    model, 110.0, 25000.0, 
                    "Ergonomic Heated Seats", 
                    "Bluetooth Audio System, Reverse Steering", false
                );
            case "sailboat":
                return new LuxuryWaterVehicule(
                    model, 45.0, 120000.0, 
                    "Air-Conditioned Cabin Suite", 
                    "Automated Winches, Radar, Kitchenette", true
                );
            case "motorboat":
            default:
                return new LuxuryWaterVehicule(
                    model, 95.0, 150000.0, 
                    "VIP Suite & Leather Loungers", 
                    "Sonar, Automatic Navigation, Mini Bar", true
                );
        }
    }

    @Override
    public AirVehicule createAirVehicle(String model) {
        String normalized = (model != null) ? model.toLowerCase().trim() : "";

        switch (normalized) {
            case "helicopter":
                return new LuxuryAirVehicule(
                    model, 300.0, 3500000.0, 
                    "VIP Leather Interior & Noise Cancelling", 
                    "Dual Turbines, FLIR Camera, Glass Cockpit", 6000.0
                );
            case "light aircraft":
                return new LuxuryAirVehicule(
                    model, 350.0, 800000.0, 
                    "Custom Leather Cockpit", 
                    "Garmin Avionics, Airframe Parachute System", 5000.0
                );
            case "airplane":
            default:
                return new LuxuryAirVehicule(
                    model, 900.0, 12000000.0, 
                    "First-Class Private Suite", 
                    "Pressurized Cabin, Satellite Wi-Fi, Jet Engine", 13000.0
                );
        }
    }
}