package com.bucur.service;

import com.bucur.model.CustomerVisit;
import com.bucur.model.StoragePoint;
import com.bucur.model.StoreDismounted;
import com.bucur.model.Tyre;
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

    public void storeTyres(StoragePoint storagePoint, CustomerVisit customerVisit, String licensePlate, StoreDismounted yes, List<Tyre> tyres) {
        // tyre service -> check worn level
    }

    public void unstoreTyres(StoragePoint storagePoint, CustomerVisit customerVisit, List<Tyre> selectedTyres) {

    }

    public void swapStorage(StoragePoint oldStorage, List<Tyre> selectedTyres, StoragePoint newStorage) {

    }

    public Map<String, List<Tyre>> getWornTyres() {
        return hotelRepository.findWornTyres();
    }

    /*
     in order to count the number of items in a list:
     get all the tyres from the database (or a list for example)
     iterate list and for each tyre, if the element meets the desired criteria
     increment a counter
     */
    public int countWornTyres() {
        // TODO: implement this if the total number of worn tyres provides some business value
        return -1;
    }
}
