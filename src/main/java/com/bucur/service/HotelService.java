package com.bucur.service;

import com.bucur.dto.CustomerVisit;
import com.bucur.dto.StoragePoint;
import com.bucur.dto.Tyre;
import com.bucur.repository.HotelRepository;

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
        hotelRepository.save(storagePoint);
    }

    public void unstoreTyres(StoragePoint storagePoint, CustomerVisit customerVisit, List<Tyre> tyres) {
        // get storage point from db (by license plate)
        // remove tyres
        // save

        if (!storagePoint.getTyres().isEmpty()) {
            storagePoint.removeTyres(tyres);
            hotelRepository.save(storagePoint);
        } else throw new RuntimeException("no tyres stored for license plate" + storagePoint.licensePlate);
    }

    public void swapStorage(StoragePoint oldStorage, StoragePoint newStorage) {
        newStorage.setTyres(oldStorage.getTyres());
        oldStorage.removeTyres(oldStorage.getTyres());
    }

    public Map<String, List<Tyre>> getWornTyres() {
        return hotelRepository.findWornTyres();
    }

    public void notifyCustomersOnSeasonChange(List<CustomerVisit> customerVisits) {

    }
}
