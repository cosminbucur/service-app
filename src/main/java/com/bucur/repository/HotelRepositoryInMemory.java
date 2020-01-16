package com.bucur.repository;

import com.bucur.model.StoragePoint;

import java.util.ArrayList;
import java.util.List;

public class HotelRepositoryInMemory implements HotelRepository {

    private static List<StoragePoint> storagePoints = new ArrayList<>();

    @Override
    public StoragePoint getStoragePoint(String licensePlate) {
        return storagePoints.stream()
            .filter(storagePoint -> storagePoint.licensePlate.equals(licensePlate))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("no tyres found for license plate " + licensePlate));
    }
}
