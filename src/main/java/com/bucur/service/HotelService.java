package com.bucur.service;

import com.bucur.model.CustomerVisit;
import com.bucur.model.StoragePoint;
import com.bucur.model.StoreDismounted;
import com.bucur.model.Tyre;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotelService {

    private static Map<StoragePoint, List<Tyre>> database = new HashMap<>();

    public StoragePoint getStoredTyres(String licensePlate) {
        return null;
    }

    public void storeTyres(StoragePoint storagePoint, CustomerVisit customerVisit, String licensePlate, StoreDismounted yes, List<Tyre> tyres) {
        // tyre service -> check worn level
    }

    public void unstoreTyres(StoragePoint storagePoint, CustomerVisit customerVisit, List<Tyre> selectedTyres) {

    }

    public void swapStorage(StoragePoint oldStorage, List<Tyre> selectedTyres, StoragePoint newStorage) {

    }

    public Map<String, List<Tyre>> getWornTyres() {
        return null;
    }
}
