package com.bucur.repository;

import com.bucur.model.StoragePoint;

import java.util.ArrayList;
import java.util.List;

public class HotelRepositoryInMemory implements HotelRepository {

    private List<StoragePoint> storagePoints = new ArrayList<>();

    public HotelRepositoryInMemory() {
//        setup();
    }

    private void setup() {
        StoragePoint storagePoint1 = new StoragePoint();
        StoragePoint storagePoint2 = new StoragePoint();

        storagePoint1.licensePlate = "A";
        storagePoint2.licensePlate = "B";
    }

    @Override
    public StoragePoint getStoragePoint(String licensePlate) {
        return storagePoints.stream()
                .filter(storagePoint -> storagePoint.licensePlate.equals(licensePlate))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("no tyres found for license plate " + licensePlate));
    }

    public void setStoragePoints(List<StoragePoint> storagePoints) {
        this.storagePoints = storagePoints;
    }
}
