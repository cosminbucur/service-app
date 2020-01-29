package com.bucur.service;

import com.bucur.dto.CustomerVisit;
import com.bucur.dto.StoragePoint;
import com.bucur.dto.Tyre;
import com.bucur.repository.HotelRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public StoragePoint findStoragePoint(String licensePlate) {
        return hotelRepository.findStoragePoint(licensePlate);
    }

    public void storeTyres(StoragePoint storagePoint, CustomerVisit customerVisit, List<Tyre> tyres) {
        storagePoint.setLicensePlate(customerVisit.getLicensePlates());
        storagePoint.setTyres(tyres);
        hotelRepository.setStoragePoints(Collections.singletonList(storagePoint));
    }

    public void unstoreTyres(StoragePoint storagePoint, CustomerVisit customerVisit, List<Tyre> tyres) {

        if (!storagePoint.getTyres().isEmpty()) {
            storagePoint.deleteTyres(tyres);
            hotelRepository.setStoragePoints(Collections.singletonList(storagePoint));
        } else throw new RuntimeException("no tyres stored for license plate" + storagePoint.licensePlate);
    }

    public void swapStorage(StoragePoint oldStorage, StoragePoint newStorage) {

    }

    public Map<String, List<Tyre>> getWornTyres() {
        return hotelRepository.findWornTyres();
    }

    public void notifyCustomersOnSeasonChange(List<CustomerVisit> customerVisits) {

    }
}
