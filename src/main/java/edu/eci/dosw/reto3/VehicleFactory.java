package edu.eci.dosw.reto3;

public interface VehicleFactory {
    LandVehicle createLandVehicle(String model);
    WaterVehicule createWaterVehicle(String model);
    AirVehicule createAirVehicle(String model);
}
