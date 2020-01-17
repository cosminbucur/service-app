package com.bucur.repository;

import com.bucur.model.StoragePoint;
import com.bucur.model.Tyre;
import com.bucur.model.Wear;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HotelRepositoryInMemory implements HotelRepository {

    private List<StoragePoint> storagePoints = new ArrayList<>();

    public HotelRepositoryInMemory() {
        // uncomment this if you want to setup an in memory database with some initial data
//        setup();
    }

    @Override
    public StoragePoint findStoragePoint(String licensePlate) {
        return storagePoints.stream()
            .filter(storagePoint -> storagePoint.licensePlate.equals(licensePlate))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("no tyres found for license plate " + licensePlate));
    }

    @Override
    public Map<String, List<Tyre>> findWornTyres() {
        Map<String, List<Tyre>> result = new HashMap<>();

        for (StoragePoint storagePoint : storagePoints) {
            if (!storagePoint.getTyres().isEmpty()) {
                List<Tyre> wornTyres = getWornTyresFromStoragePoint(storagePoint.getTyres());
                result.put(storagePoint.licensePlate, wornTyres);
            }
        }

        return result;
    }

    private List<Tyre> getWornTyresFromStoragePoint(List<Tyre> tyres) {
        return tyres.stream()
            .filter(tyre -> tyre.wear.equals(Wear.WARNING) || tyre.wear.equals(Wear.DANGER))
            .collect(Collectors.toList());
    }

    @Override
    public void setStoragePoints(List<StoragePoint> storagePoints) {
        this.storagePoints = storagePoints;
    }

    private void setup() {
        StoragePoint storagePoint1 = new StoragePoint();
        StoragePoint storagePoint2 = new StoragePoint();

        storagePoint1.licensePlate = "B22ABC";
        storagePoint2.licensePlate = "B33DEF";
    }
}
