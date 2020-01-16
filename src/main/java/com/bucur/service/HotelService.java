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

    /*
     in order to count the number of items in a list:
     get all the tyres from the database (or a list for example)
     iterate list and for each tyre, if the element meets the desired criteria
     increment a counter
     */
    public int countWornTyres() {
//        Tyre tyre1 = new Tyre(1);
//        Tyre tyre2 = new Tyre(2);
//        Tyre tyre3 = new Tyre(6);
//
//        int wornTyresCount = 0;
//
//        if (tyre1.wear < 3) {
//            wornTyresCount++;
//        }
//        if (tyre2.wear < 3) {
//            wornTyresCount++;
//        }
//        if (tyre3.wear < 3) {
//            wornTyresCount++;
//        }
//
//        return wornTyresCount;

        return -1;
    }
}
