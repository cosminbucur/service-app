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

    }

    public void unstoreTyres(StoragePoint storagePoint, CustomerVisit customerVisit, List<Tyre> tyres) {

    }

    public void swapStorage(StoragePoint oldStorage, StoragePoint newStorage) {

    }

    public Map<String, List<Tyre>> getWornTyres() {
        return hotelRepository.findWornTyres();
    }

    public void notifyCustomersOnSeasonChange(List<CustomerVisit> customerVisits) {

    }
}
