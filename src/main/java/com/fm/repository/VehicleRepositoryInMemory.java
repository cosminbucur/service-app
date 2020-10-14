package com.fm.repository;

import com.fm.dto.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleRepositoryInMemory implements VehicleRepository {
    private List<Vehicle> vehicles = new ArrayList<>();

    public Vehicle findByLicensePlate(String licensePlate) {
        return vehicles.stream().filter(vehicle -> vehicle.licensePlate.equals(licensePlate))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("vehicle with license plate " + licensePlate + " not found"));
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        return vehicle;
    }
}
