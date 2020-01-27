package com.bucur.repository;

import com.bucur.dto.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleRepositoryInMemory implements VehicleRepository {
    private List<Vehicle> vehicles = new ArrayList<>();

    @Override
    public void saveVehicle(Vehicle oldVehicleTyres) {

    }

    public Vehicle findByLicensePlate(String licensePlate) {
        return vehicles.stream().filter(vehicle -> vehicle.licensePlate.equals(licensePlate))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("vehicle with license plate " + licensePlate + " not found"));
    }
}
