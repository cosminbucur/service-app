package com.bucur.repository;

import com.bucur.model.StoragePoint;
import com.bucur.model.Tyre;

import java.util.ArrayList;
import java.util.List;

public class HotelRepositoryInMemory implements HotelRepository {

    private List<StoragePoint> storagePoints = new ArrayList<>();

    private List<Tyre> tyres = new ArrayList<>();

    public void setStoragePoints(List<StoragePoint> storagePoints) {
        this.storagePoints = storagePoints;
    }

    public void setTyres(List<Tyre> tyres) {
        this.tyres = tyres;
    }

    //    public HotelRepositoryInMemory() {
////        setup();
//    }

    //    private void setup() {
//        StoragePoint storagePoint1 = new StoragePoint();
//        StoragePoint storagePoint2 = new StoragePoint();
//
//        storagePoint1.licensePlate = "A";
//        storagePoint2.licensePlate = "B";
//    }


    @Override
    public StoragePoint getStoragePoint(String licensePlate) {
        return storagePoints.stream()
                .filter(storagePoint -> storagePoint.licensePlate.equals(licensePlate))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("no tyres found for license plate " + licensePlate));
    }

    public int countWornTyres(String hotelRepository) {
        int count = (int) tyres.stream().filter(tyre -> tyre.wear <= 3).count();
        if (count > 0) {
            return count;
        } else throw new RuntimeException("no used tyre for hotel repository " + hotelRepository);
    }
}
